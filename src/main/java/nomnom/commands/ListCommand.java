package nomnom.commands;

import nomnom.data.TaskList;
import nomnom.storage.Storage;
import nomnom.ui.Ui;

public class ListCommand extends Command{
    @Override
    public boolean execute(TaskList tasks, Storage storage, Ui ui){
        if (tasks.size() == 0) {
            ui.printMessage("no tasks found");
        } else {
            ui.printListMessage();
            for (int i = 0; i < tasks.size(); i++) {
                ui.printMessageSameLine((i+1) + ".");
                ui.printTask(tasks.get(i));
            }
        }
        ui.printLineBlank();
        return false;
    }
}
