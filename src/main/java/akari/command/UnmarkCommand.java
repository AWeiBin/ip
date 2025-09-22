package akari.command;

import akari.expression.Expression;
import akari.expression.ExpressionHandler;
import akari.storage.Storage;
import akari.task.Task;
import akari.task.TaskList;
import akari.ui.AkariException;
import akari.ui.Ui;

public class UnmarkCommand extends Command {
    public String description;
    private Task task;

    public UnmarkCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AkariException {
        int taskIndex = taskList.getValidatedTaskIndex(description);
        task = taskList.getTask(taskIndex);
        task.setDone(false);
        storage.saveTaskListToFile(getSerialisedTaskList(taskList));
    }

    @Override
    public void showResult(TaskList taskList, Ui ui, Storage storage) {
        ExpressionHandler.setExpression(Expression.ANGRY);
        ui.printMessageWithBorder("OK, I've marked this task as not done yet:\n    " + task.toString());
    }
}
