package nomnom.ui;

import java.util.Scanner;

import nomnom.data.Task;

public class Ui {
    private final String ASCII_NOMNOM = """
             ,--,--,  ,---. ,--,--,--.,--,--,  ,---. ,--,--,--.
             |      \\| .-. ||        ||      \\| .-. ||        |
             |  ||  |' '-' '|  |  |  ||  ||  |' '-' '|  |  |  |
             `--''--' `---' `--`--`--'`--''--' `---' `--`--`--'
            """;
    private final String LINE = "_".repeat(50);
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void printError(String message) {
        System.out.println(":0 something went wrong :0" + message);
        printLineBlank();
    }

    public void printTask(Task task) {
        System.out.println(task.toString());
    }

    public void printLineBlank() {
        System.out.println(LINE);
        System.out.println();
    }

    public void printTaskAddedMessage() {
        System.out.println("okay, added this task: ");
    }

    public void printListMessage() {
        System.out.println("here are the tasks in your list: ");
    }

    public void printMarkedMessage() {
        System.out.println("yay good job, i marked this task: ");
    }

    public void printUnmarkedMessage() {
        System.out.println("okay, unmarked this task: ");
    }

    public void printDeletedMessage() {
        System.out.println("okay, nomnom ate this task up: ");
    }

    public void printClearedMessage() {
        System.out.println("okay, nomnom ate all your tasks!");
    }

    public void printIntro() {
        printLineBlank();
        System.out.println(ASCII_NOMNOM);
        System.out.println("hello! i'm nomnom \nwhat can I do for you?\n(enter \"help\" to see commands and their formats)");
        printLineBlank();
    }

    public void printOutro() {
        System.out.println("bye :') come visit nomnom again");
        printLineBlank();
    }

    public void printHelpMenu() {
        System.out.println("here are the commands you can use:");
        System.out.println("  todo <description>");
        System.out.println("  deadline <description> /by yyyy-mm-dd");
        System.out.println("  event <description> /from <start> /to <end>");
        System.out.println("  list");
        System.out.println("  mark <task number>");
        System.out.println("  unmark <task number>");
        System.out.println("  delete <task number>");
        System.out.println("  clear");
        System.out.println("  find <keyword>");
        System.out.println("  bye");
    }

    public String readCommand() {
        return scanner.nextLine().toLowerCase().trim();
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printMessageSameLine(String message) {
        System.out.print(message);
    }
}
