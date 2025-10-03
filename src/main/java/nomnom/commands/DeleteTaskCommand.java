package nomnom.commands;

import nomnom.data.TaskList;
import nomnom.storage.Storage;
import nomnom.ui.Ui;

public class DeleteTaskCommand extends Command{
    private final String taskNumString;

    public DeleteTaskCommand(String taskNumString) {
        this.taskNumString = taskNumString;
    }

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

