import java.util.Scanner;

public class nomnom {
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
        System.out.println("_".repeat(50) + '\n');

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println("_".repeat(50) + '\n');
            System.out.println(input);
            System.out.println("_".repeat(50) + '\n');
        }

        System.out.println("_".repeat(50) + '\n');
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("_".repeat(50) + '\n');
    }
}
