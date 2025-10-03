package nomnom.commands;

import nomnom.data.TaskList;
import nomnom.storage.Storage;
import nomnom.ui.Ui;

public class DisplayHelpMenuCommand extends Command{
    @Override
    public boolean execute(TaskList tasks, Storage storage, Ui ui) {
        ui.printHelpMenu();
        ui.printLineBlank();
        return false;
    }
}
