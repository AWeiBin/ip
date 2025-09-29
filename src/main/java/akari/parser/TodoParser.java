package akari.parser;

import akari.command.TodoCommand;
import akari.task.Todo;
import akari.ui.AkariException;

import java.util.ArrayList;

/**
 * Parse arguments to satisfy TdoCommand
 *
 * @throws AkariException if commands does not have the expected arguments
 */
public class TodoParser extends Parser {

    protected static final String COMMAND_WORD = "todo";
    protected static final String COMMAND_ICON = "T";
    private static final String ERROR_MESSAGE = MISSING_ARG + COMMAND_WORD + " <description>";

    @Override
    protected TodoCommand parseAndCreateCommand() throws AkariException {
        if (commandDescription.isEmpty()) {
            throw new AkariException(ERROR_MESSAGE);
        }
        return new TodoCommand(commandDescription);
    }

    protected Todo parseAndCreateTask(ArrayList<String> taskArguments) {
        if (taskArguments.size() != 3) {
            return null;
        }
        return new Todo(taskArguments.get(2));
    }
}
