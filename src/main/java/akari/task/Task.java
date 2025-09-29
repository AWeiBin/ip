package akari.task;

import akari.storage.Serialiser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task in the TaskList, to be overridden by subclass.
 * Guarantees: details are present and not null, field values are validated.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    private String getStatusIconSerialised() {
        return (isDone ? "1" : "0");
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    public String toStringSerialised() {
        return Serialiser.serialiseMessage(getStatusIconSerialised()) + Serialiser.serialiseMessage(description);
    }

    protected String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DATETIME_FORMAT);
    }

    public boolean isDescription(String target) {
        return description.equals(target);
    }
}
