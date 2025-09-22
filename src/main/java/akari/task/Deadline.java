package akari.task;

import akari.storage.Serialiser;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

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
