public class Parser {

    public static void processUserCommands(String userCommand) {
        switch (userCommand) {
        case "bye":
            ExpressionHandler.setExpression(Expression.BYE);
            Akari.setExitChat();
            break;
        case "list":
            ExpressionHandler.setExpression(Expression.LAUGH);
            TaskManager.printTaskList();
            break;
        default:
            String[] commandParts = userCommand.split(" ", 2);
            switch (commandParts[0]) {
            case "mark":
                ExpressionHandler.setExpression(Expression.FOCUS);
                TaskManager.markTask(commandParts[1].trim(), true);
                break;
            case "unmark":
                ExpressionHandler.setExpression(Expression.ANGRY);
                TaskManager.markTask(commandParts[1].trim(), false);
                break;
            default:
                ExpressionHandler.setExpression(Expression.ECHO);
                TaskManager.addTask(userCommand.trim());
            }
        }
    }
}
