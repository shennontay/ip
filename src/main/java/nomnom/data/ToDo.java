package nomnom.data;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
        this.taskTypeLetter = "T";
    }

    public ToDo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
        this.taskTypeLetter = "T";
    }

    @Override
    public String toString() {
        return ("[" + getTaskTypeLetter() + "] " + "[" + getStatusIcon() + "] " + getDescription());
    }

    @Override
    public String toSaveFormat() {
        return taskTypeLetter + " | " + (isDone ? "1" : "0") + " | " + description;
    }
}
