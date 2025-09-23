package nomnom.data;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    public static int totalTasks = 0;
    protected String taskTypeLetter;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        totalTasks++;
    }

    public abstract void printTask();

    public abstract String toSaveFormat();

    public static Task fromSavedFormat(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
        case "T":
            return new ToDo(description, isDone);
        case "D":
            return new Deadline(description, parts[3], isDone);
        case "E":
            return new Event(description, parts[3], parts[4], isDone);
        default:
            throw new IllegalArgumentException("Unknown task type: " + type);
        }
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public void toggleStatus() {
        this.isDone = !this.isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark
        // done task with X
    }

    public String getTaskTypeLetter() {
        return taskTypeLetter;
    }

    public String getDescription() {
        return description;
    }
}