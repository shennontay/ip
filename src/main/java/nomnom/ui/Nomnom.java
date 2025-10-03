package nomnom.ui;

import nomnom.commands.Command;
import nomnom.data.TaskList;
import nomnom.exceptions.NomnomException;
import nomnom.parser.Parser;
import nomnom.storage.Storage;

/**
 * The main Nomnom chatbot application.
 * Handles initialization, the main program loop, and exit.
 */
public class Nomnom {

    /** The task list managed by the chatbot. */
    private final TaskList tasks;

    /** The storage handler for reading/writing tasks to file. */
    private final Storage storage;

    /** The user interface for interacting with the user. */
    private final Ui ui;

    /**
     * Creates a new Nomnom instance.
     * Loads existing tasks from the given file, or creates a new list if loading fails.
     *
     * @param filePath the path to the save file
     */
    public Nomnom(String filePath) {
        TaskList tempTasks;
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            tempTasks = new TaskList(storage.load()); // load tasks from file
        } catch (NomnomException e) {
            ui.printError(e.getMessage());
            tempTasks = new TaskList();
        }
        tasks = tempTasks;
    }

    /**
     * Runs the main program loop.
     * Repeatedly reads user input, parses it into a Command, executes it,
     * and continues until an exit command is given.
     */
    public void run() {
        ui.printIntro();

        boolean exit = false;
        while (!exit) {
            String fullCommand = ui.readCommand();
            ui.printLineBlank();
            Command command = Parser.parse(fullCommand);
            if (command.execute(tasks, storage, ui)) {
                exit = true;
            }
        }

        ui.printOutro();
    }

    /**
     * Entry point for the application.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        new Nomnom("data/nomnom.txt").run();
    }
}
