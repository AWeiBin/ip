package akari.ui;

import akari.expression.Expression;
import akari.expression.ExpressionHandler;

import java.util.Scanner;

public class Ui {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Integer BORDER_LENGTH = 60;
    private static final String TOP_BORDER = "︗".repeat(BORDER_LENGTH);
    private static final String BOTTOM_BORDER = "︘".repeat(BORDER_LENGTH);
    private static final String GREETING = "Hello! I'm Akari\nWhat can I do for you?";
    private static final String BYE = "Hope to see you again soon!";
    private static final String LOADING_ERROR = "Unable to load local saved file";

    public void printMessageWithBorder(String message) {
        String indentedMessage = message.replaceAll("\\n", "\n    ");
        System.out.println(ExpressionHandler.getExpression() + TOP_BORDER + "\n" +
                "    " + indentedMessage + "\n" +
                BOTTOM_BORDER);
    }

    public void printGreetingMessage() {
        ExpressionHandler.setExpression(Expression.GREET);
        printMessageWithBorder(GREETING);
    }

    public void printExitMessage() {
        ExpressionHandler.setExpression(Expression.BYE);
        printMessageWithBorder(BYE);
    }

    public String readUserCommand() {
        return SCANNER.nextLine().trim();
    }

    public void showLoadingError() {
        ExpressionHandler.setExpression(Expression.ANGRY);
        printMessageWithBorder(LOADING_ERROR);
    }
}
