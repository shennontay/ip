package nomnom.commands;

import nomnom.data.Task;
import nomnom.data.TaskList;
import nomnom.storage.Storage;
import nomnom.ui.Ui;

public class FindCommand extends Command{
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

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
