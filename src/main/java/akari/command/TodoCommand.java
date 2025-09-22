package akari.command;

import akari.expression.Expression;
import akari.expression.ExpressionHandler;
import akari.storage.Storage;
import akari.task.Task;
import akari.task.TaskList;
import akari.task.Todo;
import akari.ui.AkariException;
import akari.ui.Ui;

public class TodoCommand extends Command {
    public String description;
    private Task task;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AkariException {
        task = new Todo(description);
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
