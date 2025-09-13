package akari.command;

import akari.ui.Akari;
import akari.ui.AkariException;
import akari.expression.Expression;
import akari.expression.ExpressionHandler;
import akari.task.TaskManager;

public class CommandManager {
    private final TaskManager taskManager = new TaskManager();

    private static final String MISSING_ARG = "Ahhh! I can't work with missing arguments\nHere's the right format: ";

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
            return;
        case "unmark":
            ExpressionHandler.setExpression(Expression.ANGRY);
            taskManager.markTask(commandDescription, false);
            return;
        case "todo":
            ExpressionHandler.setExpression(Expression.ECHO);
            addTodoTask(commandDescription);
            return;
        case "deadline":
            ExpressionHandler.setExpression(Expression.ECHO);
            addDeadlineTask(commandDescription);
            return;
        case "event":
            ExpressionHandler.setExpression(Expression.ECHO);
            addEventTask(commandDescription);
            return;
        case "delete":
            ExpressionHandler.setExpression(Expression.PROUD);
            taskManager.deleteTask(commandDescription);
            return;
        default:
            throw new AkariException("The command you have entered is unavailable. Please try again later.");
        }
    }

    private void addTodoTask(String commandDescription) throws AkariException {
        if (commandDescription.isEmpty()) {
            throw new AkariException(MISSING_ARG + "todo <description>");
        }
        taskManager.addTodo(commandDescription);
    }

    private void addDeadlineTask(String commandDescription) throws AkariException {
        String[] descriptionAndArgument = Parser.parseDeadlineArgument(commandDescription);
        if (descriptionAndArgument.length > 2 || checkAllStringEmpty(descriptionAndArgument)) {
            throw new AkariException(MISSING_ARG + "deadline <description> /by <date>");
        }
        taskManager.addDeadline(descriptionAndArgument[0], descriptionAndArgument[1]);
    }

    public void addEventTask(String commandDescription) throws AkariException {
        String[] descriptionAndArgument = Parser.parseEventArgument(commandDescription);
        if (descriptionAndArgument == null || checkAllStringEmpty(descriptionAndArgument)) {
            throw new AkariException(MISSING_ARG + "event <description> /from <date> /to <date>");
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
