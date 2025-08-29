public class Akari {

    protected static String chatbotName = "Akari";
    private static Boolean exitChat = false;

    public static void setExitChat() {
        exitChat = true;
    }

    private static void printGreetingMessage() {
        String greetMessage =
                "Hello! I'm " + chatbotName + "\n" +
                        "What can I do for you?";
        ExpressionHandler.setExpression(Expression.GREET);
        UI.printMessageWithBorder(greetMessage);
    }

    private static void printExitMessage() {
        String exitMessage =
                "Hope to see you again soon!";
        ExpressionHandler.setExpression(Expression.BYE);
        UI.printMessageWithBorder(exitMessage);
    }

    private static void readAndProcessUserCommands() {
        String message = UI.readUserCommand();
        Parser.processUserCommands(message);
    }

    public static void main(String[] args) {
        printGreetingMessage();
        while (!exitChat) {
            readAndProcessUserCommands();
        }
        printExitMessage();
    }
}
