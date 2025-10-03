package nomnom.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import nomnom.data.Deadline;
import nomnom.data.Task;
import nomnom.data.TaskList;
import nomnom.storage.Storage;
import nomnom.ui.Ui;

/**
 * Adds a deadline task to the task list.
 * Format: deadline <description> /by yyyy-mm-dd
 */
public class AddDeadlineCommand extends Command{
    /** User input containing the task description and deadline date. */
    private final String description;

    /**
     * Creates new AddDeadlineCommand.
     * @param description that the user input after "deadline"
     */
    public AddDeadlineCommand(String description) {
        this.description = description;
    }

    /**
     * Parses the input, creates a Deadline task, and adds it to the list.
     * Shows an error if the input format is wrong.
     *
     * @param tasks   the current task list
     * @param storage the storage to save tasks
     * @param ui      the user interface
     * @return false (does not exit the program)
     */
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
