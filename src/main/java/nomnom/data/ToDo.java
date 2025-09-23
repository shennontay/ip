package nomnom.data;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
        this.taskTypeLetter = "T";
    }

    @Override
    public void printTask() {
        System.out.println('[' + getTaskTypeLetter() + "] " + '[' + getStatusIcon() + "] " + getDescription());
    }
}