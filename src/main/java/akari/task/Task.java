package akari.task;

import akari.storage.Serialiser;

public abstract class Task {
    protected String description;
    protected boolean isDone;

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
}
