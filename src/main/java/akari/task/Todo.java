package akari.task;

import akari.storage.Serialiser;

public class Todo extends Task{

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
