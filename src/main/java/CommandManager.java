public class CommandManager {
    private final TaskManager taskManager = new TaskManager();

    public void processUserCommands(String userCommand) {
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
        default:
            ExpressionHandler.setExpression(Expression.ECHO);
            taskManager.addTask(userCommand);
        }
    }

    private void addTodoTask(String commandDescription) {
        if (!commandDescription.isEmpty()) {
            taskManager.addTodo(commandDescription);
            return;
        }
        printMissingArgumentsInTask("todo <description>");
    }

    private void addDeadlineTask(String commandDescription) {
        String[] descriptionAndArgument = Parser.parseDeadlineArgument(commandDescription);
        if (descriptionAndArgument.length == 2) {
            taskManager.addDeadline(descriptionAndArgument[0], descriptionAndArgument[1]);
            return;
        }
        printMissingArgumentsInTask("deadline <description> /by <date>");
    }

    public void addEventTask(String commandDescription) {
        String[] descriptionAndArgument = Parser.parseEventArgument(commandDescription);
        if (descriptionAndArgument != null) {
            taskManager.addEvent(descriptionAndArgument[0], descriptionAndArgument[1], descriptionAndArgument[2]);
            return;
        }
        ExpressionHandler.setExpression(Expression.SAD);
        printMissingArgumentsInTask("event <description> /from <date> /to <date>");
    }

    private static void printMissingArgumentsInTask(String message) {
        ExpressionHandler.setExpression(Expression.SAD);
        UI.printMessageWithBorder("Arguments missing\n" + message);
    }
}
