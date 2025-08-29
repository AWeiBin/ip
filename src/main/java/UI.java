import java.util.Scanner;

public class UI {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Integer BORDER_LENGTH = 60;

    public static void printMessageWithBorder(String message) {
        String indentedMessage = message.replaceAll("\\n", "\n    ");
        String messageWithBorder =
                ExpressionHandler.getExpression() + "︗".repeat(BORDER_LENGTH) + "\n" +
                        "    " + indentedMessage + "\n" +
                        "︘".repeat(BORDER_LENGTH);

        System.out.println(messageWithBorder);
    }

    public static String readUserCommand() {
        return SCANNER.nextLine().trim();
    }
}
