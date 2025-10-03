package nomnom.commands;

import nomnom.data.TaskList;
import nomnom.storage.Storage;
import nomnom.ui.Ui;

/**
 * Deletes a task from the task list.
 * Format: delete <task number>
 */
public class DeleteTaskCommand extends Command{
    /** The task number provided by the user as a string. */
    private final String taskNumString;

    /**
     * Creates a new DeleteTaskCommand.
     *
     * @param taskNumString the task number as entered by the user
     */
    public DeleteTaskCommand(String taskNumString) {
        this.taskNumString = taskNumString;
    }

    /**
     * Deletes the specified task from the list.
     * If the task number is invalid, shows an error instead.
     *
     * @param tasks   the current task list
     * @param storage the storage to save tasks
     * @param ui      the user interface
     * @return false (does not exit the program)
     */
    @Override
    public boolean execute(TaskList tasks, Storage storage, Ui ui) {
        // check if task number is valid
        int taskNum = tasks.getTaskNumber(taskNumString);
        if (taskNum == -1) {
            ui.printError("\nplease feed nomnom a valid task number!");
            return false;
        }

        int taskIndex = taskNum - 1;
        ui.printDeletedMessage();
        ui.printTask(tasks.get(taskIndex));
        ui.printLineBlank();

        // remove the task
        tasks.remove(taskIndex);
        return false;
    }
}

