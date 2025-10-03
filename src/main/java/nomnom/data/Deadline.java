package nomnom.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
        this.taskTypeLetter = "D";
    }

    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description);
        this.by = by;
        this.isDone = isDone;
        this.taskTypeLetter = "D";
    }

    @Override
    public String toString() {
        return ("[" + getTaskTypeLetter() + "] " + "[" + getStatusIcon() + "] " + getDescription() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")");
    }

    @Override
    public String toSaveFormat() {
        return taskTypeLetter + " | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}

