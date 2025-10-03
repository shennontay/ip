package nomnom.commands;

import nomnom.data.Task;
import nomnom.data.TaskList;
import nomnom.storage.Storage;
import nomnom.ui.Ui;

/**
 * Marks a task as done in the task list.
 * Format: mark <task number>
 */
public class MarkCommand extends Command{
    /** The task number provided by the user as a string. */
    private final String taskNumString;

    /**
     * Creates a new MarkCommand.
     *
     * @param taskNumString the task number as entered by the user
     */
    public MarkCommand(String taskNumString) {
        this.taskNumString = taskNumString;
    }

    /**
     * Marks the specified task as done.
     * If the task number is invalid, shows an error instead.
     *
     * @param tasks   the current task list
     * @param storage the storage to save tasks
     * @param ui      the user interface
     * @return false (does not exit the program)
     */
    @Override
    public boolean execute(TaskList tasks, Storage storage, Ui ui) {
        //check if task number is valid
        int taskNum = tasks.getTaskNumber(taskNumString);
        if (taskNum == -1) {
            ui.printError("\nplease feed nomnom a valid task number!");
            return false;
        }

        //toggle if unmarked
        int taskIndex = taskNum - 1;
        Task theTask = tasks.get(taskIndex);
        if (!theTask.getStatus()) {
            theTask.toggleStatus();
        }

        ui.printMarkedMessage();
        ui.printTask(tasks.get(taskIndex));
        ui.printLineBlank();
        return false;
    }
}