package akari.command;

import akari.expression.Expression;
import akari.expression.ExpressionHandler;
import akari.storage.Storage;
import akari.task.Task;
import akari.task.TaskList;
import akari.ui.AkariException;
import akari.ui.Ui;

public class MarkCommand extends Command {
    public String description;
    private Task task;

    public MarkCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AkariException {
        int taskIndex = taskList.getValidatedTaskIndex(description);
        task = taskList.getTask(taskIndex);
        task.setDone(true);
        storage.saveTaskListToFile(getSerialisedTaskList(taskList));
    }

    @Override
    public void showResult(TaskList taskList, Ui ui, Storage storage) {
        ExpressionHandler.setExpression(Expression.FOCUS);
        ui.printMessageWithBorder("Nice! I've marked this task as done:\n    " + task.toString());
    }
}
