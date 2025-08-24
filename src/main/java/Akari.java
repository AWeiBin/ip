import java.util.Scanner;

public class Akari {
    private static String chatbotExpression;
    private static Task[] taskList = new Task[100];
    private static int taskCount = 0;

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
            String userCommand = in.nextLine().trim();
            switch (userCommand) {
            case "bye":
                endChat = true;
                break;
            case "list":
                chatbotExpression = "(￣y▽￣)╭ Ohohoho.....";
                printTaskList();
                break;
            default:
                String[] commandParts = userCommand.split(" ", 2);
                switch (commandParts[0]) {
                case "mark":
                    chatbotExpression = "໒(◔ᴗ◔)७✎▤";
                    markTask(commandParts[1], true);
                    break;
                case "unmark":
                    chatbotExpression = "(╯°□°）╯︵ ┻━┻";
                    markTask(commandParts[1], false);
                    break;
                default:
                    chatbotExpression = "o(〃＾▽＾〃)o 🕬";
                    addTask(userCommand);
                }
            }
        }
    }

    public static void addTask(String message) {
        taskList[taskCount] = new Task(message);
        taskCount++;
        printMessageWithBorder("added: " + message);
    }

    public static void printTaskList() {
        StringBuilder message = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskCount; i++) {
            message.append(String.format("%d.%s", i + 1, taskList[i].getPrintTaskMessage()));
        }
        printMessageWithBorder(message.toString().trim());
    }

    public static void markTask(String description, boolean setMark) {
        for (int i = 0; i < taskCount; i++) {
            if (taskList[i].description.equals(description)) {
                taskList[i].setDone(setMark);
                String message = setMark ? "Nice! I've marked this task as done:\n" : "OK, I've marked this task as not done yet:\n";
                message +=  "  " + taskList[i].getPrintTaskMessage();
                printMessageWithBorder(message);
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
