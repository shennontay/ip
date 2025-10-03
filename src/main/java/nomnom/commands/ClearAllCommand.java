package nomnom.commands;

import nomnom.data.Storage;
import nomnom.data.TaskList;
import nomnom.exceptions.NomnomException;
import nomnom.ui.Ui;

public class ClearAllCommand extends Command{
    @Override
    public boolean execute(TaskList tasks, Storage storage, Ui ui) {
        tasks.clear();
        ui.printClearedMessage();
        ui.printLineBlank();

        try {
            storage.save(tasks);
        } catch (NomnomException e) {
            ui.showError(e.getMessage());
        }

        return false;
    }
}
