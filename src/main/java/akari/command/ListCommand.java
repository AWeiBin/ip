package akari.command;

import akari.expression.Expression;
import akari.expression.ExpressionHandler;
import akari.storage.Storage;
import akari.task.TaskList;
import akari.ui.AkariException;
import akari.ui.Ui;

public class ListCommand extends Command {
    public ListCommand() {
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AkariException {
        if (taskList.getTaskCount() <= 0) {
            throw new AkariException("Nothing in the list");
        }
    }

    @Override
    public void showResult(TaskList taskList, Ui ui, Storage storage) {
        ExpressionHandler.setExpression(Expression.LAUGH);
        StringBuilder message = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getTaskCount(); i++) {
            message.append(String.format("\n%d.%s", i + 1, taskList.getTask(i).toString()));
        }
        ui.printMessageWithBorder(message.toString());
    }
}
