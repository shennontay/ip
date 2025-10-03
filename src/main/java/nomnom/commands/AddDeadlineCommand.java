package nomnom.commands;

import nomnom.data.Deadline;
import nomnom.data.Storage;
import nomnom.data.Task;
import nomnom.data.TaskList;
import nomnom.ui.Ui;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
            ui.showError("Invalid deadline format: " + description + " Use: deadline <task> /by yyyy-MM-dd");
            return false;
        }

        try {
            LocalDate by = LocalDate.parse(deadlineParts[1]);
            Task newTask = new Deadline(deadlineParts[0], by);
            tasks.add(newTask);
            ui.printTaskAddedMessage();
            ui.showTask(newTask);
            ui.printLineBlank();
        } catch (DateTimeParseException e) {
            ui.showError("invalid deadline format, use: deadline <task> /by yyyy-MM-dd");
        }
        return false;
    }
}
