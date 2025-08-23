import java.util.Scanner;

public class Akari {

    public static void printMessageWithBorder(String message) {
        String messageWithBorder =
            "o(〃＾▽＾〃)o 🕬 ︗︗︗︗︗︗︗︗︗︗︗︗︗︗︗︗︗︗︗︗︗︗︗︗︗︗︗︗︗︗︗\n" +
            "   " + message +"\n" +
            "︘︘︘︘︘︘︘︘︘︘︘︘︘︘︘︘︘︘︘︘︘︘︘︘︘︘︘︘︘︘︘︘︘︘︘︘︘︘︘︘";
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
            "ヾ(￣▽￣) Bye~Bye~. Hope to see you again soon!";

        printMessageWithBorder(greetMessage);
        readAndProcessUserCommands();
        printMessageWithBorder(exitMessage);
    }
}
