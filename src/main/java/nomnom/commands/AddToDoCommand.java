package nomnom.commands;

import nomnom.data.Task;
import nomnom.data.TaskList;
import nomnom.data.ToDo;
import nomnom.storage.Storage;
import nomnom.ui.Ui;

/**
 * Adds a todo task to the task list.
 * Format: todo <description>
 */
public class AddToDoCommand extends Command {
    /** User input containing the todo description. */
    private final String description;

    /**
     * Creates a new AddToDoCommand.
     *
     * @param description the user input after "todo"
     */
    public AddToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Creates a ToDo task and adds it to the list.
     * Shows an error if the description is empty.
     *
     * @param tasks   the current task list
     * @param storage the storage to save tasks
     * @param ui      the user interface
     * @return false (does not exit the program)
     */
    @Override
    public boolean execute(TaskList tasks, Storage storage, Ui ui) {
        if (description.isEmpty() || description.trim().isEmpty()) {
            ui.printError("\nnomnom doesn't recognise this todo format: " + description + "\ntry this: todo <description>");
            return false;
        }

        Task newTask = new ToDo(description);
        tasks.add(newTask);

        ui.printTaskAddedMessage();
        ui.printTask(newTask);
        ui.printLineBlank();
        return false;
    }
}
