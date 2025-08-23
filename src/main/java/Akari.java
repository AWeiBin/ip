import java.util.Scanner;

public class Akari {
    static String chatbotExpression = "o(〃＾▽＾〃)o 🕬";

    public static void printMessageWithBorder(String message) {
        String indentedMessage = message.replaceAll("\\n", "\n    ");
        String messageWithBorder =
            chatbotExpression + "︗".repeat(60-chatbotExpression.length()) + "\n" +
            "    " + indentedMessage +"\n" +
            "︘".repeat(60-3);

        System.out.println(messageWithBorder);
    }

    public static void readAndProcessUserCommands() {
        Scanner in = new Scanner(System.in);
        while (true) {
            String userCommand = in.nextLine();
            switch (userCommand) {
                case "bye":
                    return;
                default:
                    chatbotExpression = "o(〃＾▽＾〃)o 🕬";
                    printMessageWithBorder(userCommand);
            }
        }
    }

    public static void main(String[] args) {
        String chatbotName = "Akari";

        String greetMessage =
            "Hello! I'm " + chatbotName + "\n" +
            "What can I do for you?";
        String exitMessage =
            "Hope to see you again soon!";

        chatbotExpression = "o(*￣▽￣*)ブ ";
        printMessageWithBorder(greetMessage);
        readAndProcessUserCommands();
        chatbotExpression = "ヾ(￣▽￣) Bye~Bye~";
        printMessageWithBorder(exitMessage);
    }
}
