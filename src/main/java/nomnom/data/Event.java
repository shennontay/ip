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

    @Override
    public void printTask() {
        System.out.println('[' + getTaskTypeLetter() + "] " + '[' + getStatusIcon() + "] " + getDescription() + " (from: " + from + " to: " + to + ")");
    }
}