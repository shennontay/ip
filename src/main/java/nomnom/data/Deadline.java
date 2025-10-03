package nomnom.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task that has a description and a due date.
 * Example: deadline return book /by 2019-12-02
 */
public class Deadline extends Task {
    /** The date by which the task should be completed. */
    private LocalDate by;

    /**
     * Creates a new Deadline task that is not marked as done.
     *
     * @param description the description of the task
     * @param by the due date of the task
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
        this.taskTypeLetter = "D";
    }

    /**
     * Creates a new Deadline task with a given done status.
     *
     * @param description the description of the task
     * @param by the due date of the task
     * @param isDone true if the task is already done
     */
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description);
        this.by = by;
        this.isDone = isDone;
        this.taskTypeLetter = "D";
    }

    /**
     * Returns the string representation of the deadline task for display.
     *
     * @return the formatted string for this task
     */
    @Override
    public String toString() {
        return "[" + getTaskTypeLetter() + "] "
                + "[" + getStatusIcon() + "] "
                + getDescription()
                + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Returns the string representation of the deadline task
     * in the format used for saving to storage.
     *
     * @return the save format string
     */
    @Override
    public String toSaveFormat() {
        return taskTypeLetter + " | "
                + (isDone ? "1" : "0")
                + " | " + description
                + " | " + by;
    }
}
