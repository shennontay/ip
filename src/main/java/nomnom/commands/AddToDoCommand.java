package nomnom.commands;

import nomnom.data.Storage;
import nomnom.data.Task;
import nomnom.data.TaskList;
import nomnom.data.ToDo;
import nomnom.ui.Ui;

public class AddToDoCommand extends Command {

    private final String description;

    public AddToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public boolean execute(TaskList tasks, Storage storage, Ui ui) {
        if (description.isEmpty() || description.trim().isEmpty()) {
            ui.showError("Invalid todo format: " + description);
            return false;
        }
        Task newTask = new ToDo(description);
        tasks.add(newTask);
        ui.printTaskAddedMessage();
        ui.showTask(newTask);
        ui.printLineBlank();
        return false;
    }



}
