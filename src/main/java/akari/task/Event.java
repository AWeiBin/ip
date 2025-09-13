package akari.task;

import akari.storage.Serialiser;

public class Event extends Task{

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toStringSerialised() {
        return Serialiser.serialiseMessage("E") + super.toStringSerialised() + Serialiser.serialiseMessage(from)  + Serialiser.serialiseMessage(to);
    }
}
