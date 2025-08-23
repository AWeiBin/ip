import java.util.Scanner;

public class Akari {
    static String chatbotExpression = "o(〃＾▽＾〃)o 🕬";
    static String[] taskList = new String[100];

    public static void printMessageWithBorder(String message) {
        String indentedMessage = message.replaceAll("\\n", "\n    ");
        String messageWithBorder =
                chatbotExpression + "︗".repeat(60 - chatbotExpression.length()) + "\n" +
                        "    " + indentedMessage + "\n" +
                        "︘".repeat(60 - 3);

        System.out.println(messageWithBorder);
    }

    public static void readAndProcessUserCommands() {
        boolean endChat = false;
        Scanner in = new Scanner(System.in);
        while (!endChat) {
            String userCommand = in.nextLine();
            switch (userCommand) {
            case "bye":
                endChat = true;
                break;
            case "list":
                printTaskList();
                break;
            default:
                chatbotExpression = "o(〃＾▽＾〃)o 🕬";
                addTask(userCommand);
            }
        }
    }

    public static void addTask(String message) {
        for (int i = 0; i < taskList.length; i++) {
            if (taskList[i] == null) {
                taskList[i] = message;
                break;
            }
        }
        printMessageWithBorder("added: " + message);
    }

    public static void printTaskList() {
        String message = "";
        for (int i = 0; i < taskList.length; i++) {
            if (taskList[i] == null) {
                break;
            } else {
                message += i + 1 + "." + taskList[i] + "\n";
            }
        }
        printMessageWithBorder(message.trim());
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
