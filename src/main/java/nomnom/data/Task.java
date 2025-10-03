package nomnom.data;

import java.time.LocalDate;

/**
 * Represents a general task with a description and status (done or not done).
 * Subclasses include ToDo, Deadline, and Event.
 */
public abstract class Task {
    /** The description of the task. */
    protected String description;

    /** True if the task is done, false otherwise. */
    protected boolean isDone;

    /** A letter representing the task type (e.g., T, D, E). */
    protected String taskTypeLetter;

    /**
     * Creates a new Task with the given description.
     * By default, the task is not done.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task for display.
     *
     * @return the formatted string for this task
     */
    public abstract String toString();

    /**
     * Returns the string format used for saving this task to storage.
     *
     * @return the save format string
     */
    public abstract String toSaveFormat();

    /**
     * Reconstructs a Task object from its saved format.
     *
     * @param line the line read from the save file
     * @return the reconstructed Task
     */
    public static Task fromSavedFormat(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
        case "T":
            return new ToDo(description, isDone);
        case "D":
            LocalDate by = LocalDate.parse(parts[3].trim());
            return new Deadline(description, by, isDone);
        case "E":
            return new Event(description, parts[3], parts[4], isDone);
        default:
            throw new IllegalArgumentException("Unknown task type: " + type);
        }
    }

    /**
     * Returns the status of the task.
     *
     * @return true if done, false otherwise
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /** Toggles the task status between done and not done. */
    public void toggleStatus() {
        this.isDone = !this.isDone;
    }

    /**
     * Returns "X" if the task is done, or " " otherwise.
     *
     * @return the status icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the letter representing the task type.
     *
     * @return the task type letter
     */
    public String getTaskTypeLetter() {
        return taskTypeLetter;
    }

    /**
     * Returns the description of the task.
     *
     * @return the task description
     */
    public String getDescription() {
        return description;
    }
}
