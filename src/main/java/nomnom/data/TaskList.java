package nomnom.data;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void remove(int index) {
        tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    public List<Task> getList() {
        return tasks;
    }

    public boolean isValidIndex(int index) {
        if (index <= 0 || index > size()) {
            return false;
        }
        return true;
    }

    public int getTaskNumber(String argument) {
        try {
            int index = Integer.parseInt(argument);
            if (!isValidIndex(index)) {
                return -1;
            }
            return index;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void clear() {
        tasks.clear();
    }
}
