package nomnom.commands;

import nomnom.data.Storage;
import nomnom.data.Task;
import nomnom.data.TaskList;
import nomnom.ui.Ui;

public class UnmarkCommand extends Command{

    private final String taskNumString;

    public UnmarkCommand(String taskNumString) {
        this.taskNumString = taskNumString;
    }

    @Override
    public boolean execute(TaskList tasks, Storage storage, Ui ui) {

        //check if task number is valid
        int taskNum = tasks.getTaskNumber(taskNumString);
        if (taskNum == -1) {
            ui.showError("please feed nomnom a valid task number!");
            return false;
        }

        //toggle if marked
        int taskIndex = taskNum - 1;
        Task theTask = tasks.get(taskIndex);
        if (theTask.getStatus()) {
            theTask.toggleStatus();
        }

        ui.printUnmarkedMessage();
        ui.showTask(tasks.get(taskIndex));
        ui.printLineBlank();
        return false;
    }

}