package akari.parser;

import akari.command.MarkCommand;
import akari.ui.AkariException;

public class MarkParser extends Parser {

    protected static final String COMMAND_WORD = "mark";
    private static final String ERROR_MESSAGE = MISSING_ARG + COMMAND_WORD + " <description or index>";

    @Override
    protected MarkCommand parseAndCreateCommand() throws AkariException {
        if (commandDescription.isEmpty()) {
            throw new AkariException(ERROR_MESSAGE);
        }
        return new MarkCommand(commandDescription);
    }
}
