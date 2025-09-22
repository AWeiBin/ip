package akari.parser;

import akari.command.DeleteCommand;
import akari.ui.AkariException;

public class DeleteParser extends Parser {

    protected static final String COMMAND_WORD = "delete";
    private static final String ERROR_MESSAGE = MISSING_ARG + COMMAND_WORD + " <description or index>";

    @Override
    protected DeleteCommand parseAndCreateCommand() throws AkariException {
        if (commandDescription.isEmpty()) {
            throw new AkariException(ERROR_MESSAGE);
        }
        return new DeleteCommand(commandDescription);
    }
}
