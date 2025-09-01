public class Task {
    protected String description;
    protected boolean isDone;
    public static int totalTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        totalTasks++;

    }

    public boolean getStatus() {
        return this.isDone;
    }

    public void toggleStatus() {
        this.isDone = !this.isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public String printTask() {
        return ('[' + getStatusIcon() + "] " + getDescription());
    }
}