import java.util.Scanner;

public class nomnom {

    public static final String line = "_".repeat(50);

    public static void printLine() {
        System.out.println(line);
    }
    
    public static void printLineBlank() {
        System.out.println(line + "\n");
    }


    public static void main(String[] args) {
        System.out.println("_".repeat(50));

        String asciiArt = """
                 ,--,--,  ,---. ,--,--,--.,--,--,  ,---. ,--,--,--.
                 |      \\| .-. ||        ||      \\| .-. ||        |
                 |  ||  |' '-' '|  |  |  ||  ||  |' '-' '|  |  |  |
                 `--''--' `---' `--`--`--'`--''--' `---' `--`--`--'
                """;
        System.out.println(asciiArt);

        System.out.println("Hello! I'm nomnom.\nWhat can I do for you?");
        printLineBlank();

        Scanner scanner = new Scanner(System.in);
        String[] commands = new String[100];
        int commandIndex = 0;

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("list")) {
                printLineBlank();
                for (int i = 0; i < commandIndex; i++) {
                    System.out.println(i + 1 + ". " + commands[i]);
                }
                printLineBlank();
            } else if (input.equals("bye")) {
                break;
            } else {
                commands[commandIndex] = input;
                printLineBlank();
                System.out.println("added: " + input);
                printLineBlank();
                commandIndex++;
            }
        }

        printLineBlank();
        System.out.println("Bye. Hope to see you again soon!");
        printLineBlank();
    }
}