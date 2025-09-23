package nomnom.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks.getList()) {
            fw.write(task.toSaveFormat() + System.lineSeparator());
        }
        fw.close();
    }

    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs(); // create data folder if missing
            file.createNewFile();
            return tasks;
        }
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            tasks.add(Task.fromSavedFormat(line));
        }
        return tasks;
    }
}

