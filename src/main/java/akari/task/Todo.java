package akari.task;

import akari.storage.Serialiser;

/**
 * Represents a Todo Task in the TaskList.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Todo extends Task {

    protected String by;

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStringSerialised() {
        return Serialiser.serialiseMessage("T") + super.toStringSerialised();
    }
}
