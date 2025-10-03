package nomnom.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import nomnom.data.Task;
import nomnom.data.TaskList;
import nomnom.exceptions.NomnomException;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves tasks into the file.
     */
    public void save(TaskList tasks) throws NomnomException {
        try (FileWriter fw = new FileWriter(filePath)) {
            for (Task task : tasks.getList()) {
                fw.write(task.toSaveFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new NomnomException("error saving to file: " + filePath);
        }
    }

    /**
     * Loads tasks from the file, or create new file if there is no existing one.
     */
    public List<Task> load() throws NomnomException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        try {
            if (!file.exists()) {
                // create parent directories if missing
                File parent = file.getParentFile();
                if (parent != null && !parent.exists() && !parent.mkdirs()) {
                    throw new NomnomException("failed to create directory: " + parent.getAbsolutePath());
                }

                // create new file
                if (!file.createNewFile()) {
                    throw new NomnomException("failed to create file: " + filePath);
                }
                return tasks; // return empty list since it's a new file
            }

            // read tasks if file exists
            try (Scanner sc = new Scanner(file)) {
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    tasks.add(Task.fromSavedFormat(line));
                }
            }
        } catch (IOException e) {
            throw new NomnomException("error loading from file: " + filePath);
        }
        return tasks;
    }
}

