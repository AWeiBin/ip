package akari.task;

import akari.storage.Serialiser;

public class Deadline extends Task{

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toStringSerialised() {
        return Serialiser.serialiseMessage("D") + super.toStringSerialised() + Serialiser.serialiseMessage(by);
    }
}
