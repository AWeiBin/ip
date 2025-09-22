package akari.command;

import akari.expression.Expression;
import akari.expression.ExpressionHandler;
import akari.storage.Storage;
import akari.task.Event;
import akari.task.Task;
import akari.task.TaskList;
import akari.ui.AkariException;
import akari.ui.Ui;

public class EventCommand extends Command {
    public String description;
    public String from;
    public String to;
    private Task task;

    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AkariException {
        task = new Event(description, from, to);
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
