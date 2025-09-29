package akari;

import akari.command.Command;
import akari.expression.Expression;
import akari.expression.ExpressionHandler;
import akari.parser.Parser;
import akari.storage.Storage;
import akari.task.TaskList;
import akari.ui.AkariException;
import akari.ui.Ui;


/**
 * Entry point of the Akari chatbot.
 * Initializes the application and starts the interaction with the user.
 */
public class Akari {

    private static final String FILE_PATH = "data/tasks.txt";
    private static Boolean hasExitChat = false;
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Sets up the required objects, and load storage data into task list.
     */
    public Akari(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (AkariException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Akari(FILE_PATH).run();
    }

    /**
     * Set exit flag to terminate the program
     */
    public static void setExitChat() {
        hasExitChat = true;
    }

    /**
     * Runs the program until termination.
     */
    public void run() {
        ui.printGreetingMessage();
        while (!hasExitChat) {
            try {
                String fullCommand = ui.readUserCommand();
                Command c = new Parser().parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                c.showResult(tasks, ui, storage);
            } catch (AkariException e) {
                ExpressionHandler.setExpression(Expression.SAD);
                ui.printMessageWithBorder(e.getMessage());
            }
        }
    }
}
