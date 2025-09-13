package akari.command;

import akari.ui.Akari;
import akari.ui.AkariException;
import akari.expression.Expression;
import akari.expression.ExpressionHandler;
import akari.task.TaskManager;
import akari.storage.LocalSave;

import java.util.ArrayList;

public class CommandManager {
    private final TaskManager taskManager = new TaskManager();

    private static final String MISSING_ARG = "Ahhh! I can't work with missing arguments\nHere's the right format: ";

    public CommandManager() {
        ArrayList<String> savedTaskList = LocalSave.readTaskListFromFile();
        if (savedTaskList == null) {
            return;
        }
        taskManager.loadTaskList(savedTaskList);
        taskManager.printTaskList();
    }

    public void processUserCommands(String userCommand) throws AkariException {
        switch (userCommand) {
        case "bye":
            ExpressionHandler.setExpression(Expression.BYE);
            Akari.setExitChat();
            return;
        case "list":
            ExpressionHandler.setExpression(Expression.LAUGH);
            taskManager.printTaskList();
            return;
        }
        String[] commandWordAndDescription = Parser.splitCommandWordAndDescription(userCommand);
        String commandWord = commandWordAndDescription[0];
        String commandDescription = commandWordAndDescription[1];
        switch (commandWord) {
        case "mark":
            ExpressionHandler.setExpression(Expression.FOCUS);
            taskManager.markTask(commandDescription, true);
            break;
        case "unmark":
            ExpressionHandler.setExpression(Expression.ANGRY);
            taskManager.markTask(commandDescription, false);
            break;
        case "todo":
            ExpressionHandler.setExpression(Expression.ECHO);
            addTodoTask(commandDescription);
            break;
        case "deadline":
            ExpressionHandler.setExpression(Expression.ECHO);
            addDeadlineTask(commandDescription);
            break;
        case "event":
            ExpressionHandler.setExpression(Expression.ECHO);
            addEventTask(commandDescription);
            break;
        default:
            throw new AkariException("The command you have entered is unavailable. Please try again later.");
        }
        LocalSave.saveTaskListToFile(taskManager.getSerialisedTaskList());
    }

    private void addTodoTask(String commandDescription) throws AkariException {
        if (commandDescription.isEmpty()) {
            throw new AkariException(MISSING_ARG + "todo <description>");
        }
        taskManager.addTodo(commandDescription);
    }

    private void addDeadlineTask(String commandDescription) throws AkariException {
        String[] descriptionAndArgument = Parser.parseDeadlineArgument(commandDescription);
        if (descriptionAndArgument.length != 2 || checkAllStringEmpty(descriptionAndArgument)) {
            throw new AkariException(MISSING_ARG + "deadline <description> /by <date>");
        }
        taskManager.addDeadline(descriptionAndArgument[0], descriptionAndArgument[1]);
    }

    public void addEventTask(String commandDescription) throws AkariException {
        String[] descriptionAndArgument = Parser.parseEventArgument(commandDescription);
        if (descriptionAndArgument == null || checkAllStringEmpty(descriptionAndArgument)) {
            throw new AkariException(MISSING_ARG + "event <description> /from <date startTime> /to <endTime>");
        }
        taskManager.addEvent(descriptionAndArgument[0], descriptionAndArgument[1], descriptionAndArgument[2]);
    }

    private static boolean checkAllStringEmpty(String[] stringArray) {
        for (String string : stringArray) {
            if (string == null || string.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
