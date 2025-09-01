import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Nomnom {

    private static final String LINE = "_".repeat(50);
    private static final String ASCII_NOMNOM = """
             ,--,--,  ,---. ,--,--,--.,--,--,  ,---. ,--,--,--.
             |      \\| .-. ||        ||      \\| .-. ||        |
             |  ||  |' '-' '|  |  |  ||  ||  |' '-' '|  |  |  |
             `--''--' `---' `--`--`--'`--''--' `---' `--`--`--'
            """;

    private static final List<Task> tasks = new ArrayList<>();

    public static boolean isValid = true;

    public static void main(String[] args) {
        printIntro();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            isValid = true;
            String input = scanner.nextLine();
            input = input.trim();
            input = input.toLowerCase();
            printLineBlank();
            if (input.isBlank()) {
                System.out.println("whoopsies, you entered nothing!");
                printLineBlank();
                continue;
            }
            if (input.equals("list")) {
                System.out.println("here are the tasks in your list: ");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + "." + tasks.get(i).printTask());
                }
                printLineBlank();
            } else if (input.equals("bye")) {
                break;
            } else if (isMarkUnmark(input)) {
                handleMarkUnmark(input);
            } else if (isValid) {
                Task task = new Task(input);
                tasks.add(task);
                System.out.println("added: " + input);
                printLineBlank();
            }
        }
        printOutro();
    }

    public static boolean isMarkUnmark(String input) {
        String[] parts = input.split("\\s+");
        int taskNum;
        if (parts.length != 2) {
            return false;
        }
        if (parts[0].equals("unmark") == parts[0].equals("mark")) {
            return false;
        }
        try {
            taskNum = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            System.out.println("whoopsies, please enter a valid task number");
            printLineBlank();
            isValid = false;
            return false;
        }

        if (taskNum > Task.totalTasks || taskNum <= 0) {
            System.out.println("whoopsies, please enter a valid task number");
            printLineBlank();
            isValid = false;
            return false;
        }
        return true;
    }

    public static void handleMarkUnmark(String input) {
        String[] parts = input.split("\\s+");
        int taskIndex = Integer.parseInt(parts[1]) - 1;
        Task theTask = tasks.get(taskIndex);
        if (parts[0].equals("mark")) {
            if (theTask.getStatus()) {
                System.out.println("ooh, it's already been marked as done!");

            } else {
                theTask.toggleStatus();
                System.out.println("delicious! i've marked this task as done:");
            }
        } else if (parts[0].equals("unmark")) {
            if (!theTask.getStatus()) {
                System.out.println("ooh, it's already been marked as not done yet");
            } else {
                theTask.toggleStatus();
                System.out.println("delicious! i've marked this task as not done yet:");
            }
        }
        System.out.println(theTask.printTask());
        printLineBlank();
    }

    public static void printIntro() {
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

