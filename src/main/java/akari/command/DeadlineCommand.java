package akari.command;

import akari.expression.Expression;
import akari.expression.ExpressionHandler;
import akari.storage.Storage;
import akari.task.Deadline;
import akari.task.Task;
import akari.task.TaskList;
import akari.ui.AkariException;
import akari.ui.Ui;

import java.time.LocalDateTime;

/**
 * Add Deadline task into TaskList.
 */
public class DeadlineCommand extends Command {
    public String description;
    public LocalDateTime by;
    private Task task;

    public DeadlineCommand(String description, LocalDateTime by) throws AkariException {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AkariException {
        task = new Deadline(description, by);
        taskList.add(task);
        storage.saveTaskListToFile(getSerialisedTaskList(taskList));
    }

    @Override
    public void showResult(TaskList taskList, Ui ui, Storage storage) {
        ExpressionHandler.setExpression(Expression.ECHO);
        String message = getAddedTaskMessage(task, taskList.getTaskCount());
        ui.printMessageWithBorder(message);
    }
}
