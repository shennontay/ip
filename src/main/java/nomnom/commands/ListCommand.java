package nomnom.commands;

import nomnom.data.Storage;
import nomnom.data.TaskList;
import nomnom.ui.Ui;

public class ListCommand extends Command{

    @Override
    public boolean execute(TaskList tasks, Storage storage, Ui ui){
        ui.printListMessage();
        for (int i = 0; i < tasks.size(); i++) {
            ui.showTask(tasks.get(i));
        }
        ui.printLineBlank();
        return false;
    }
}
