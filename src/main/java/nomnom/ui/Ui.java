package nomnom.ui;

import java.util.Scanner;

import nomnom.data.Task;

/**
 * Handles interactions with the user.
 * Displays messages, errors, and prompts, and reads user input.
 */
public class Ui {
    /** ASCII art logo for Nomnom. */
    private final String ASCII_NOMNOM = """
             ,--,--,  ,---. ,--,--,--.,--,--,  ,---. ,--,--,--.
             |      \\| .-. ||        ||      \\| .-. ||        |
             |  ||  |' '-' '|  |  |  ||  ||  |' '-' '|  |  |  |
             `--''--' `---' `--`--`--'`--''--' `---' `--`--`--'
            """;
    /** A horizontal line used as a divider. */
    private final String LINE = "_".repeat(50);

    /** Scanner for reading user input. */
    private final Scanner scanner;

    /**
     * Creates a new Ui with an input scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints an error message with a standard prefix.
     *
     * @param message the error details
     */
    public void printError(String message) {
        System.out.println(":0 something went wrong :0" + message);
        printLineBlank();
    }

    public void printTask(Task task) {
        System.out.println(task.toString());
    }

    /** Prints a horizontal divider line followed by a blank line. */
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

    /** Prints a message shown when a task is marked done. */
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
        System.out.println("bye :) come visit nomnom again");
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

    /**
     * Reads the next command entered by the user.
     *
     * @return the trimmed, lowercased input string
     */
    public String readCommand() {
        return scanner.nextLine().toLowerCase().trim();
    }

    /**
     * Prints a generic message.
     *
     * @param message the message to print
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints a message without adding a newline.
     *
     * @param message the message to print
     */
    public void printMessageSameLine(String message) {
        System.out.print(message);
    }
}
