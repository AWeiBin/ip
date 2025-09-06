package akari.ui;

import akari.command.CommandManager;
import akari.expression.Expression;
import akari.expression.ExpressionHandler;

public class Akari {

    protected static String chatbotName = "Akari";
    private static Boolean exitChat = false;

    public static void setExitChat() {
        exitChat = true;
    }

    public static String getChatbotName() {
        return chatbotName;
    }

    private static void readAndProcessUserCommands(CommandManager commander) {
        String message = UI.readUserCommand();
        try {
            commander.processUserCommands(message);
        } catch (AkariException e) {
            ExpressionHandler.setExpression(Expression.SAD);
            UI.printMessageWithBorder(e.getMessage());
        }
    }

    public static void main(String[] args) {
        UI.printGreetingMessage();
        CommandManager commander = new CommandManager();
        while (!exitChat) {
            readAndProcessUserCommands(commander);
        }
        UI.printExitMessage();
    }
}
