public class Parser {

    private final static String[] argumentSeparatorList = {"/by", "/from", "to"};

    public static void processUserCommands(String rawUserInput) {
        String userCommand = rawUserInput.trim();
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
            String[] commandWordAndDescription = splitCommandWordAndDescription(userCommand);
            String commandWord = commandWordAndDescription[0];
            String commandDescription = commandWordAndDescription[1];
            switch (commandWord) {
            case "mark":
                ExpressionHandler.setExpression(Expression.FOCUS);
                TaskManager.markTask(commandDescription, true);
                break;
            case "unmark":
                ExpressionHandler.setExpression(Expression.ANGRY);
                TaskManager.markTask(commandDescription, false);
                break;
            case "todo":
                ExpressionHandler.setExpression(Expression.ECHO);
                TaskManager.addTodo(commandDescription);
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
                ExpressionHandler.setExpression(Expression.ECHO);
                TaskManager.addTask(userCommand);
            }
        }
    }

    private static String[] splitCommandWordAndDescription(String userCommand) {
        String[] split = userCommand.split(" ", 2);
        String[] trimSplit = trimStringInList(split);
        return trimSplit.length == 2 ? trimSplit : new String[] { trimSplit[0] , "" };
    }

    public static String[] trimStringInList(String[] rawStringList) {
        String[] trimmedString = new String[rawStringList.length];
        for (int i = 0; i < rawStringList.length; i++) {
            trimmedString[i] = rawStringList[i].trim();
        }
        return trimmedString;
    }

    public static void addDeadlineTask(String commandDescription) {
        String[] descriptionAndArgument = parseDeadlineArgument(commandDescription);
        if (descriptionAndArgument.length == 2) {
            TaskManager.addDeadline(descriptionAndArgument[0], descriptionAndArgument[1]);
        } else {
            ExpressionHandler.setExpression(Expression.SAD);
            printMissingArgumentMessage();
        }
    }

    public static String[] parseDeadlineArgument(String commandDescription) {
        String[] descriptionAndArgument = commandDescription.split("/by", 2);
        return trimStringInList(descriptionAndArgument);
    }

    public static void addEventTask(String commandDescription) {
        String[] descriptionAndArgument = parseEventArgument(commandDescription);
        if (descriptionAndArgument != null) {
            TaskManager.addEvent(descriptionAndArgument[0], descriptionAndArgument[1], descriptionAndArgument[2]);
        } else {
            ExpressionHandler.setExpression(Expression.SAD);
            printMissingArgumentMessage();
        }
    }

    public static String[] parseEventArgument(String commandDescription) {
        int fromIndex = commandDescription.indexOf("/from");
        int toIndex = commandDescription.indexOf("/to");
        String description = "";
        String from = "";
        String to = "";
        if (fromIndex == -1 || toIndex == -1) {
            return null;
        }
        String[] descriptionAndArgument = commandDescription.split("/from", 2);
        if (fromIndex < toIndex) {
            description = descriptionAndArgument[0];
            String[] arguments = descriptionAndArgument[1].split("/to", 2);
            from = arguments[0];
            to = arguments[1];
        } else {
            from =  descriptionAndArgument[1];
            String[] arguments = descriptionAndArgument[0].split("/to", 2);
            description = arguments[0];
            to = arguments[1];
        }
        return trimStringInList(new String[] {description, from, to});
    }

    public static void printMissingArgumentMessage() {
        UI.printMessageWithBorder("Arguments missing");
    }
}
