package nomnom.commands;

import nomnom.data.Event;
import nomnom.data.TaskList;
import nomnom.storage.Storage;
import nomnom.ui.Ui;

public class AddEventCommand extends Command{
    private final String description;

    public AddEventCommand(String description) {
        this.description = description;
    }

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
