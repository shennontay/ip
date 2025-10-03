package nomnom.commands;

import nomnom.data.Event;
import nomnom.data.TaskList;
import nomnom.storage.Storage;
import nomnom.ui.Ui;

/**
 * Adds an event task to the task list.
 * Format: event <description> /from <start> /to <end>
 */
public class AddEventCommand extends Command{
    /** User input containing the event description, start, and end time. */
    private final String description;

    /**
     * Creates a new AddEventCommand.
     *
     * @param description the user input after "event"
     */
    public AddEventCommand(String description) {
        this.description = description;
    }

    /**
     * Parses the input, creates an Event task, and adds it to the list.
     * Shows an error if the input format is wrong.
     *
     * @param tasks   the current task list
     * @param storage the storage to save tasks
     * @param ui      the user interface
     * @return false (does not exit the program)
     */
    @Override
    public boolean execute(TaskList tasks, Storage storage, Ui ui) {

        String[] eventParts = description.split("\\s" + "*/from\\s" + "*|\\s*/to\\s*");

        if (eventParts.length != 3 || eventParts[0].isEmpty() || eventParts[1].isEmpty() || eventParts[2].isEmpty()) {
            ui.printError("\nnomnom doesn't recognise this event format: " + description + "\ntry this: event <description> /from <start> /to <end>");
            return false;
        }

        Event newTask = new Event(eventParts[0], eventParts[1], eventParts[2]);
        tasks.add(newTask);

        ui.printTaskAddedMessage();
        ui.printTask(newTask);
        ui.printLineBlank();
        return false;
    }
}
