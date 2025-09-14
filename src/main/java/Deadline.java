public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.taskTypeLetter = "D";
    }

    @Override
    public String printTask() {
        return ('[' + getTaskTypeLetter() + "] " + '[' + getStatusIcon() + "] " + getDescription() + " (by: " + by + ")");
    }
}
