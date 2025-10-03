package nomnom.commands;

import nomnom.data.Deadline;
import nomnom.data.Storage;
import nomnom.data.Task;
import nomnom.data.TaskList;
import nomnom.ui.Ui;

public class AddDeadlineCommand extends Command{
    private final String description;

    public AddDeadlineCommand(String description) {
        this.description = description;
    }

    @Override
    public boolean execute(TaskList tasks, Storage storage, Ui ui) {
        String[] deadlineParts = description.split(
                "\\s*/by\\s*");
        if (deadlineParts.length != 2 || deadlineParts[0].isEmpty() || deadlineParts[1].isEmpty()) {
            ui.showError("Invalid deadline format: " + description);
            return false;
        }
        Task newTask = new Deadline(deadlineParts[0], deadlineParts[1]);
        tasks.add(newTask);
        ui.printTaskAddedMessage();
        ui.showTask(newTask);
        ui.printLineBlank();
        return false;
    }
}
