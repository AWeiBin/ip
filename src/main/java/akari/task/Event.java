package akari.task;

import akari.storage.Serialiser;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public boolean isDuring(LocalDate target) {
        return (target.isAfter(from.toLocalDate()) && target.isBefore(to.toLocalDate())) ||
                (target.isEqual(from.toLocalDate()) || target.isEqual(to.toLocalDate()));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatDateTime(from) + " to: " + formatDateTime(to) + ")";
    }

    @Override
    public String toStringSerialised() {
        return Serialiser.serialiseMessage("E") + super.toStringSerialised() + Serialiser.serialiseMessage(from.toString()) + Serialiser.serialiseMessage(to.toString());
    }
}
