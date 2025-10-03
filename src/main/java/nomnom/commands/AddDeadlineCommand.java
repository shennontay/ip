package nomnom.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import nomnom.data.Deadline;
import nomnom.data.Task;
import nomnom.data.TaskList;
import nomnom.storage.Storage;
import nomnom.ui.Ui;

public class AddDeadlineCommand extends Command{
    private final String description;

    public AddDeadlineCommand(String description) {
        this.description = description;
    }

    @Override
    public boolean execute(TaskList tasks, Storage storage, Ui ui) {
        String[] deadlineParts = description.split("\\s*/by\\s*");

        if (deadlineParts.length != 2 || deadlineParts[0].isEmpty() || deadlineParts[1].isEmpty()) {
            ui.printError("\nnomnom doesn't recognise this deadline format: " + description + "\ntry this: deadline <task> /by yyyy-mm-dd");
            return false;
        }

        try {
            LocalDate by = LocalDate.parse(deadlineParts[1]);
            Task newTask = new Deadline(deadlineParts[0], by);
            tasks.add(newTask);
            ui.printTaskAddedMessage();
            ui.printTask(newTask);
            ui.printLineBlank();
        } catch (DateTimeParseException e) {
            ui.printError("\nnomnom doesn't recognise this deadline format: " + description + "\ntry this: deadline <task> /by yyyy-mm-dd");
        }
        return false;
    }
}
