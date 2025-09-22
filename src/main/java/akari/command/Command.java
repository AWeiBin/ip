package akari.command;

import akari.storage.Storage;
import akari.task.Task;
import akari.task.TaskList;
import akari.ui.AkariException;
import akari.ui.Ui;

public abstract class Command {
    public Command() {
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws AkariException {
        throw new AkariException("This method is to be implemented by child classes");
    }

    public void showResult(TaskList taskList, Ui ui, Storage storage) throws AkariException {
        throw new AkariException("This method is to be implemented by child classes");
    }

    public String getAddedTaskMessage(Task task, int taskCount) {
        return "Got it. I've added this task:\n" +
                "    " + task.toString() + "\n" +
                "Now you have " + taskCount + " in the list";
    }

    public String getSerialisedTaskList(TaskList taskList) {
        StringBuilder message = new StringBuilder();
        for (Task task : taskList.getTaskList()) {
            message.append(String.format(task.toStringSerialised() + "\n"));
        }
        return message.toString();
    }
}
