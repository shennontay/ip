package nomnom.commands;

import nomnom.data.Storage;
import nomnom.data.TaskList;
import nomnom.ui.Ui;

public abstract class Command {
    public abstract boolean execute(TaskList tasks, Storage storage, Ui ui);
}

