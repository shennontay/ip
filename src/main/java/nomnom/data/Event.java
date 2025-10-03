package nomnom.data;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.taskTypeLetter = "E";
    }

    public Event(String description, String from, String to, boolean isDone) {
        super(description);
        this.from = from;
        this.to = to;
        this.isDone = isDone;
        this.taskTypeLetter = "E";
    }

    @Override
    public String toString() {
        return ("[" + getTaskTypeLetter() + "] " + "[" + getStatusIcon() + "] " + getDescription() + " (from: " + from + " to: " + to + ")");
    }

    @Override
    public String toSaveFormat() {
        return taskTypeLetter + " | " + (isDone ? "1" : "0") + " | "
                + description + " | " + from + " | " + to;
    }
}
