package nomnom.commands;

import nomnom.data.TaskList;
import nomnom.exceptions.NomnomException;
import nomnom.storage.Storage;
import nomnom.ui.Ui;

/**
 * Clears all tasks from the task list.
 * Format: clear
 */
public class ClearAllCommand extends Command {
    /**
     * Removes all tasks from the list and saves the empty list to storage.
     * Shows an error if saving fails.
     *
     * @param tasks   the current task list
     * @param storage the storage to save tasks
     * @param ui      the user interface
     * @return false (does not exit the program)
     */
    @Override
    public boolean execute(TaskList tasks, Storage storage, Ui ui) {
        tasks.clear();
        ui.printClearedMessage();
        ui.printLineBlank();

        try {
            storage.save(tasks);
        } catch (NomnomException e) {
            ui.printError(e.getMessage());
        }
        return false;
    }
}
