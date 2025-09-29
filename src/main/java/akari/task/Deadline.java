package akari.task;

import akari.storage.Serialiser;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a Deadline Task in the TaskList.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Return true if the input date is the same as the Task.
     *
     * @param target date
     * @return true if input date same as Task.
     */
    public boolean isBy(LocalDate target) {
        return target.equals(by.toLocalDate());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDateTime(by) + ")";
    }

    @Override
    public String toStringSerialised() {
        return Serialiser.serialiseMessage("D") + super.toStringSerialised() + Serialiser.serialiseMessage(by.toString());
    }
}
