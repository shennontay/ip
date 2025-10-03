package nomnom.ui;

import java.util.Scanner;

import nomnom.data.Task;

public class Ui {

    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    private final String ASCII_NOMNOM = """
             ,--,--,  ,---. ,--,--,--.,--,--,  ,---. ,--,--,--.
             |      \\| .-. ||        ||      \\| .-. ||        |
             |  ||  |' '-' '|  |  |  ||  ||  |' '-' '|  |  |  |
             `--''--' `---' `--`--`--'`--''--' `---' `--`--`--'
            """;

    private final String LINE =
            "_".repeat(50);

    public void showError(String message) {
        System.out.println("something went wrong :0 " + message);
        printLineBlank();
    }
\
    public void showTask(Task task) {
        System.out.println(task.toString());
    }

    public void printLineBlank() {
        System.out.println(LINE);
        System.out.println();
    }

    public void printTaskAddedMessage() {
        System.out.println("okay, I've added this task: ");
    }

    public void printListMessage() {
        System.out.println("here are the tasks in your list: ");
    }

    public void printMarkedMessage() {
        System.out.println("okay, i've marked this task: ");
    }

    public void printUnmarkedMessage() {
        System.out.println("okay, i've unmarked this task: ");
    }

    public void printDeletedMessage() {
        System.out.println("okay, this task has been removed: ");
    }

    public void printClearedMessage() {
        System.out.println("okay, I've cleared all your tasks!");
    }

    public void printIntro() {
        printLineBlank();
        System.out.println(ASCII_NOMNOM);
        System.out.println("hello! i'm nomnom \nwhat can I do for you?");
        printLineBlank();
    }

    public void printOutro() {
        System.out.println("bye :)) hope to see you again soon!");
        printLineBlank();

    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

}
