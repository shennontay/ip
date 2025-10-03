package nomnom.commands;

import nomnom.data.Task;
import nomnom.data.TaskList;
import nomnom.data.ToDo;
import nomnom.storage.Storage;
import nomnom.ui.Ui;

public class AddToDoCommand extends Command {
    private final String description;

    public AddToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public boolean execute(TaskList tasks, Storage storage, Ui ui) {
        if (description.isEmpty() || description.trim().isEmpty()) {
            ui.printError("\nnomnom doesn't recognise this todo format: " + description + "\ntry this: todo <description>");
            return false;
        }

        Task newTask = new ToDo(description);
        tasks.add(newTask);

        ui.printTaskAddedMessage();
        ui.printTask(newTask);
        ui.printLineBlank();
        return false;
    }
}
