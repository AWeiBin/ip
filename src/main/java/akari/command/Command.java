package akari.command;

import akari.storage.Storage;
import akari.task.Task;
import akari.task.TaskList;
import akari.ui.AkariException;
import akari.ui.Ui;

/**
 * Represents an executable command.
 */
public abstract class Command {
    public Command() {
    }

    /**
     * Executes the command.
     * @param taskList perform commands on this TaskList
     * @param ui console that shows the user message
     * @param storage file path to stores data
     * @throws AkariException command execution fail
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AkariException {
        throw new AkariException("This method is to be implemented by child classes");
    }

    /**
     * Show result of the executed command.
     * @param taskList perform commands on this TaskList
     * @param ui console that shows the user message
     * @param storage file path to stores data
     * @throws AkariException command execution fail
     */
    public void showResult(TaskList taskList, Ui ui, Storage storage) throws AkariException {
        throw new AkariException("This method is to be implemented by child classes");
    }

    protected String getAddedTaskMessage(Task task, int taskCount) {
        return "Got it. I've added this task:\n" +
                "    " + task.toString() + "\n" +
                "Now you have " + taskCount + " in the list";
    }

    protected String getSerialisedTaskList(TaskList taskList) {
        StringBuilder message = new StringBuilder();
        for (Task task : taskList.getTaskList()) {
            message.append(String.format(task.toStringSerialised() + "\n"));
        }
        return message.toString();
    }
}
