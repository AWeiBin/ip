package akari.command;

import akari.expression.Expression;
import akari.expression.ExpressionHandler;
import akari.storage.Storage;
import akari.task.Task;
import akari.task.TaskList;
import akari.ui.AkariException;
import akari.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    public String description;
    private ArrayList<Task> tasks = new ArrayList<>();


    public FindCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AkariException {
        for (Task task : taskList.getTaskList()) {
            if (task.isDescription(description)) {
                tasks.add(task);
            }
        }
    }

    @Override
    public void showResult(TaskList taskList, Ui ui, Storage storage) {
        if  (tasks.isEmpty()) {
            ExpressionHandler.setExpression(Expression.SAD);
            ui.printMessageWithBorder("There are no matching tasks in your list");
            return;
        }
        ExpressionHandler.setExpression(Expression.PROUD);
        StringBuilder message = new StringBuilder("Here are the matching tasks in your list:");
        for (Task task : tasks) {
            message.append("\n").append(task.toString());
        }
        ui.printMessageWithBorder(message.toString());
    }
}
