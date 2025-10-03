package nomnom.commands;

import nomnom.data.Storage;
import nomnom.data.TaskList;
import nomnom.exceptions.NomnomException;
import nomnom.ui.Ui;

public class ExitCommand extends Command{
    @Override
    public boolean execute(TaskList tasks, Storage storage, Ui ui){
        try {
            storage.save(tasks);
        } catch (NomnomException e) {
            ui.showError(e.getMessage());
        }
        return true;
    }
}
