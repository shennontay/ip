package nomnom.commands;

import nomnom.data.Task;
import nomnom.data.TaskList;
import nomnom.storage.Storage;
import nomnom.ui.Ui;

/**
 * Finds tasks in the task list that contain a given keyword.
 * Format: find <keyword>
 */
public class FindCommand extends Command{
    /** The keyword to search for in task descriptions. */
    private final String keyword;

    /**
     * Creates a new FindCommand.
     *
     * @param keyword the keyword entered by the user
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    /**
     * Searches the task list for tasks containing the keyword.
     * Shows the list of matching tasks, or a message if none are found.
     *
     * @param tasks   the current task list
     * @param storage the storage to save tasks
     * @param ui      the user interface
     * @return false (does not exit the program)
     */
    @Override
    public boolean execute(TaskList tasks, Storage storage, Ui ui) {
        String returnMessage = "";
        int counter = 0;
        for (Task task : tasks.getList()) {
            counter++;
            if (task.getDescription().contains(keyword)) {
                returnMessage += counter + "." + task + "\n";
            }
        }

        if (returnMessage.isEmpty()) {
            ui.printMessage("nomnom couldn't find any tasks containing: " + keyword + ":)");
        } else {
            ui.printMessage("nomnom found these tasks containing: " + keyword);
            ui.printMessage(returnMessage);
        }
        ui.printLineBlank();
        return false;
    }
}
