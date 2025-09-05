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
        commander.processUserCommands(message);
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
