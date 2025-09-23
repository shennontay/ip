package nomnom.ui;

import java.io.IOException;
import java.util.Scanner;


import nomnom.Storage;
import nomnom.data.*;
import nomnom.exceptions.*;

public class Nomnom {

    private static final String LINE =
            "_".repeat(50);
    private static final String ASCII_NOMNOM = """
             ,--,--,  ,---. ,--,--,--.,--,--,  ,---. ,--,--,--.
             |      \\| .-. ||        ||      \\| .-. ||        |
             |  ||  |' '-' '|  |  |  ||  ||  |' '-' '|  |  |  |
             `--''--' `---' `--`--`--'`--''--' `---' `--`--`--'
            """;

    private static TaskList tasks;

    public static final String INVALID_TASK_NUMBER_MSG = "whoopsies, please enter a valid task number";

    public static void main(String[] args) {
        printIntro();
        Storage storage = new Storage("data/nomnom.txt");
        try {
            tasks = new TaskList(storage.load()); // load tasks from file
        } catch (IOException e) {
            System.out.println("No save file found. Starting fresh...");
            tasks = new TaskList();
        }
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit && scanner.hasNextLine()) {
            String input = scanner.nextLine();
            input = input.toLowerCase().trim();
            String[] words = input.split(" ",
                    2);// split into command +
            // rest\

            printLineBlank();

            try {
                switch (words[0]) {
                case "":
                    throw new EmptyInputException("whoopsies, you entered nothing!");
                case "list":
                    handleList();
                    break;
                case "bye":
                    try {
                        storage.save(tasks);
                    } catch (IOException e) {
                        System.out.println("Error saving tasks: " + e.getMessage());
                    }
                    exit = true;
                    break;
                case "mark", "unmark":
                    if (hasValidTaskNumber(words)) {
                        handleMarkUnmark(words);
                    }
                    break;
                case "todo":
                    addToDo(words.length > 1 ?
                            words[1] : "");
                    break;
                case "deadline":
                    addDeadline(words.length > 1 ? words[1] : "");
                    break;
                case "event":
                    addEvent(words.length > 1 ?
                            words[1] : "");
                    break;
                case "delete":
                    if (hasValidTaskNumber(words)) {
                        deleteTask(words[1]);
                    }
                    break;
                case "clear":
                    deleteAll(storage);
                    break;
                default:
                    throw new InvalidFormatException("nomnom doesn't understand that command. try: todo, deadline, event, list, mark, unmark, bye");
                }
            } catch (EmptyInputException |
                     InvalidFormatException e) {
                System.out.println(e.getMessage());
                printLineBlank();
            }

        }
        printOutro();
    }

    private static void addEvent(String input) {
        String[] eventParts =
                input.split("\\s" + "*/from\\s" +
                        "*|\\s*/to\\s*");
        if (eventParts.length != 3 || eventParts[0].isEmpty() || eventParts[1].isEmpty() || eventParts[2].isEmpty()) {
            askForCorrectInput("event");
            return;
        }
        Event newEvent =
                new Event(eventParts[0],
                        eventParts[1],
                        eventParts[2]);
        tasks.add(newEvent);
        System.out.println("Okay, I've added this event: ");
        tasks.get(tasks.size() - 1).printTask();
        printLineBlank();
    }

    private static void askForCorrectInput(String taskType) {
        System.out.println("nomnom can't read " +
                "this unless you write it in " +
                "the right format. ");
        switch (taskType) {
        case "todo", "none":
            System.out.println("e.g. todo " +
                    "borrow book");
            break;
        case "deadline":
            System.out.println("e.g. deadline " +
                    "return book /by Sunday");
            break;
        case "event":
            System.out.println("e.g. event " +
                    "project meeting /from Mon " +
                    "2pm /to 4pm");
        }
        System.out.println("Try again? :P");
        printLineBlank();
    }


    private static void addDeadline(String input) {
        String[] deadlineParts = input.split(
                "\\s*/by\\s*");

        if (deadlineParts.length != 2 || deadlineParts[0].isEmpty() || deadlineParts[1].isEmpty()) {
            askForCorrectInput("deadline");
            return;
        }
        Deadline newDeadline =
                new Deadline(deadlineParts[0],
                        deadlineParts[1]);
        tasks.add(newDeadline);
        System.out.println("Okay, I've added this deadline: ");
        tasks.get(tasks.size() - 1).printTask();
        printLineBlank();
    }


    private static void addToDo(String input) {
        try {
            if (input == null || input.trim().isEmpty()) {
                throw new EmptyInputException(
                        "whoopsies, you didn't " +
                                "enter anything" +
                                " :(");
            }
        } catch (EmptyInputException e) {
            System.out.println(e.getMessage());
            printLineBlank();
        }
        Task newTask = new ToDo(input);
        tasks.add(newTask);
        System.out.println("Okay, I've added this todo: ");
        tasks.get(tasks.size() - 1).printTask();
        printLineBlank();
    }

    private static void handleList() {
        System.out.println("here are the tasks " +
                "in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(i + 1 + ".");
            tasks.get(i).printTask();
        }
        printLineBlank();
    }


    private static boolean hasValidTaskNumber(String[] parts) {
        try {
            if (parts.length != 2) {
                throw new InvalidTaskNumberException("enter a valid task number please!");
            }
        } catch (InvalidTaskNumberException e) {
            System.out.println(e.getMessage());
            printLineBlank();
            return false;
        } // parts has length of 2
        int taskNum;
        try {
            taskNum = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            System.out.println(INVALID_TASK_NUMBER_MSG);
            printLineBlank();
            return false;
        } // parts[1] is a number

        if (taskNum > Task.totalTasks || taskNum <= 0) {
            System.out.println(INVALID_TASK_NUMBER_MSG);
            printLineBlank();
            return false;
        } //parts[1] is a valid task number
        return true;
    }

    public static void handleMarkUnmark(String[] parts) {
        int taskIndex =
                Integer.parseInt(parts[1]) - 1;
        Task theTask = tasks.get(taskIndex);
        if (parts[0].equals("mark")) {
            handleMark(theTask);
        } else if (parts[0].equals("unmark")) {
            handleUnmark(theTask);
        }
        theTask.printTask();
        printLineBlank();
    }

    private static void handleUnmark(Task theTask) {
        if (!theTask.getStatus()) {
            System.out.println("ooh, it's " +
                    "already been marked as not" +
                    " done yet");
        } else {
            theTask.toggleStatus();
            System.out.println("delicious! i've" +
                    " marked this task as not " +
                    "done yet:");
        }
    }

    private static void handleMark(Task theTask) {
        if (theTask.getStatus()) {
            System.out.println("ooh, it's " +
                    "already been marked as " +
                    "done!");

        } else {
            theTask.toggleStatus();
            System.out.println("delicious! i've" +
                    " marked this task as done:");
        }
    }

    private static void deleteTask(String taskNumber) {
        System.out.println("Okay, this task has" +
                " been removed: ");
        int taskIndex =
                Integer.parseInt(taskNumber) - 1;
        tasks.get(taskIndex).printTask();
        printLineBlank();
        tasks.remove(taskIndex);
        Task.totalTasks--;
    }

    private static void deleteAll(Storage storage) {
        tasks = new TaskList();
        Task.totalTasks = 0;

        System.out.println("Okay, I've cleared all your tasks!");
        printLineBlank();

        try {
            storage.save(tasks); // overwrite duke.txt with nothing
        } catch (IOException e) {
            System.out.println("Error clearing tasks: " + e.getMessage());
        }
    }


    private static void printIntro() {
        printLineBlank();
        System.out.println(ASCII_NOMNOM);
        System.out.println("hello! I'm nomnom" +
                ".\nwhat can I do for you?");
        printLineBlank();
    }

    public static void printOutro() {
        System.out.println("bye :)) hope to see" +
                " you again soon!");
        printLineBlank();
    }

    public static void printLineBlank() {
        System.out.println(LINE);
        System.out.println();
    }

}

