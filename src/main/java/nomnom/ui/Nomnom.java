package nomnom.ui;

import nomnom.commands.Command;
import nomnom.data.TaskList;
import nomnom.exceptions.NomnomException;
import nomnom.parser.Parser;
import nomnom.storage.Storage;

public class Nomnom {

    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

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

    public static void main(String[] args) {
        new Nomnom("data/nomnom.txt").run();
    }
}



