package akari.command;

import akari.expression.Expression;
import akari.expression.ExpressionHandler;
import akari.storage.Storage;
import akari.task.Task;
import akari.task.TaskList;
import akari.ui.AkariException;
import akari.ui.Ui;

/**
 * Delete task in TaskList
 */
public class DeleteCommand extends Command {
    public String description;
    private Task task;

    public DeleteCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AkariException {
        int taskIndex = taskList.getValidatedTaskIndex(description);
        task = taskList.getTask(taskIndex);
        taskList.remove(taskIndex);
        storage.saveTaskListToFile(getSerialisedTaskList(taskList));
    }

    @Override
    public void showResult(TaskList taskList, Ui ui, Storage storage) {
        ExpressionHandler.setExpression(Expression.PROUD);
        ui.printMessageWithBorder("Got it. I've removed this task:\n" +
                "    " + task.toString() + "\n" +
                "Now you have " + taskList.getTaskCount() + " in the list");
    }
}
