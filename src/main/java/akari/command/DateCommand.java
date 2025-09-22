package akari.command;

import akari.expression.Expression;
import akari.expression.ExpressionHandler;
import akari.storage.Storage;
import akari.task.Deadline;
import akari.task.Event;
import akari.task.Task;
import akari.task.TaskList;
import akari.ui.AkariException;
import akari.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;

public class DateCommand extends Command {
    public LocalDate description;
    private ArrayList<Task> tasks = new ArrayList<>();

    public DateCommand(LocalDate description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AkariException {
        for (Task task : taskList.getTaskList()) {
            if ((task instanceof Deadline && ((Deadline) task).isBy(description)) ||
                    (task instanceof Event && ((Event) task).isDuring(description))) {
                tasks.add(task);
            }
        }
    }

    @Override
    public void showResult(TaskList taskList, Ui ui, Storage storage) {
        ExpressionHandler.setExpression(Expression.ANGRY);
        if  (tasks.isEmpty()) {
            ui.printMessageWithBorder("There are no tasks");
            return;
        }
        StringBuilder message = new StringBuilder("Here are the tasks for the date:");
        for (Task task : tasks) {
            message.append("\n").append(task.toString());
        }
        ui.printMessageWithBorder(message.toString());
    }
}
