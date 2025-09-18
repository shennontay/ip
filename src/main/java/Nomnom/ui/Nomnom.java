package nomnom.ui;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import nomnom.data.*;
import nomnom.exceptions.*;

public class Nomnom {

    private static final String LINE = "_".repeat(50);
    private static final String ASCII_NOMNOM = """
             ,--,--,  ,---. ,--,--,--.,--,--,  ,---. ,--,--,--.
             |      \\| .-. ||        ||      \\| .-. ||        |
             |  ||  |' '-' '|  |  |  ||  ||  |' '-' '|  |  |  |
             `--''--' `---' `--`--`--'`--''--' `---' `--`--`--'
            """;

    private static final List<Task> tasks = new ArrayList<>();
    public static final String INVALID_TASK_NUMBER_MSG = "whoopsies, please enter a valid task number";

    public static void main(String[] args) {
        printIntro();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit && scanner.hasNextLine()) {
            String input = scanner.nextLine();
            input = input.toLowerCase().trim();
            String[] words = input.split(" ", 2); // split into command + rest

            printLineBlank();

            try {
                switch (words[0]) {
                case "":
                    throw new EmptyInputException("whoopsies, you entered nothing!");
                case "list":
                    handleList();
                    break;
                case "bye":
                    exit = true;
                    break;
                case "mark", "unmark":
                    if (isValidMarkUnmark(words)) {
                        handleMarkUnmark(words);
                    }
                    break;
                case "todo":
                    addToDo(words.length > 1 ? words[1] : "");
                    break;
                case "deadline":
                    addDeadline(words.length > 1 ? words[1] : "");
                    break;
                case "event":
                    addEvent(words.length > 1 ? words[1] : "");
                    break;
                default:
                    throw new InvalidFormatException("nomnom doesn't understand that command. try: todo, deadline, event, list, mark, unmark, bye");
                }
            } catch (EmptyInputException | InvalidFormatException e) {
                System.out.println(e.getMessage());
                printLineBlank();
            }

        }
        printOutro();
    }

    private static void addEvent(String input) {
        String[] eventParts = input.split("\\s*/from\\s*|\\s*/to\\s*");
        if (eventParts.length != 3) {
            askForCorrectInput("event");
            return;
        }
        Event newEvent = new Event(eventParts[0], eventParts[1], eventParts[2]);
        tasks.add(newEvent);
        tasks.get(tasks.size() - 1).printTask();
    }

    private static void askForCorrectInput(String taskType) {
        System.out.println("nomnom can't read this unless you write it in the right format. ");
        switch(taskType) {
        case "todo", "none":
            System.out.println("e.g. todo borrow book");
            break;
        case "deadline":
            System.out.println("e.g. deadline return book /by Sunday");
            break;
        case "event":
            System.out.println("e.g. event project meeting /from Mon 2pm /to 4pm");
        }
        System.out.println("Try again? :P");
        printLineBlank();
    }


    private static void addDeadline(String input) {
        String[] deadlineParts = input.split("\\s*/by\\s*");
        if (deadlineParts.length != 2) {
            askForCorrectInput("deadline");
            return;
        }
        Deadline newDeadline = new Deadline(deadlineParts[0], deadlineParts[1]);
        tasks.add(newDeadline);
        tasks.get(tasks.size() - 1).printTask();
    }


    private static void addToDo(String input) {
        try {
            if (input == null || input.trim().isEmpty()) {
                throw new EmptyInputException("whoopsies, you didn't enter anything :(");
            }
        } catch (EmptyInputException e) {
            System.out.println(e.getMessage());
            printLineBlank();
        }
        Task newTask = new ToDo(input);
        tasks.add(newTask);
        tasks.get(tasks.size() - 1).printTask();
    }

    private static void handleList() {
        System.out.println("here are the tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i).printTask());
        }
        printLineBlank();
    }


    private static boolean isValidMarkUnmark(String[] parts) {
        try {
            if (parts.length != 2) {
                throw new InvalidTaskNumberException("enter a valid task number please!");
            }
        } catch (InvalidTaskNumberException e) {
            System.out.println(e.getMessage());
            printLineBlank();
        }
        int taskNum;
        try {
            taskNum = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            System.out.println(INVALID_TASK_NUMBER_MSG);
            printLineBlank();
            return false;
        }

        if (taskNum > Task.totalTasks || taskNum <= 0) {
            System.out.println(INVALID_TASK_NUMBER_MSG);
            printLineBlank();
            return false;
        }
        return true;
    }

    public static void handleMarkUnmark(String[] parts) {
        int taskIndex = Integer.parseInt(parts[1]) - 1;
        Task theTask = tasks.get(taskIndex);
        if (parts[0].equals("mark")) {
            handleMark(theTask);
        } else if (parts[0].equals("unmark")) {
            handleUnmark(theTask);
        }
        System.out.println(theTask.printTask());
        printLineBlank();
    }

    private static void handleUnmark(Task theTask) {
        if (!theTask.getStatus()) {
            System.out.println("ooh, it's already been marked as not done yet");
        } else {
            theTask.toggleStatus();
            System.out.println("delicious! i've marked this task as not done yet:");
        }
    }

    private static void handleMark(Task theTask) {
        if (theTask.getStatus()) {
            System.out.println("ooh, it's already been marked as done!");

        } else {
            theTask.toggleStatus();
            System.out.println("delicious! i've marked this task as done:");
        }
    }

    private static void printIntro() {
        printLineBlank();
        System.out.println(ASCII_NOMNOM);
        System.out.println("hello! I'm nomnom.\nwhat can I do for you?");
        printLineBlank();
    }

    public static void printOutro() {
        System.out.println("bye :)) hope to see you again soon!");
        printLineBlank();
    }

    public static void printLineBlank() {
        System.out.println(LINE);
        System.out.println();
    }

}

