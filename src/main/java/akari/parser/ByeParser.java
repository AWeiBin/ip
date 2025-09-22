package akari.parser;

import akari.command.ByeCommand;
import akari.ui.AkariException;

public class ByeParser extends Parser {

    protected static final String COMMAND_WORD = "bye";
    private static final String ERROR_MESSAGE = EXTRA_ARG + COMMAND_WORD;

    @Override
    protected ByeCommand parseAndCreateCommand() throws AkariException {
        if (!commandDescription.isEmpty()) {
            throw new AkariException(ERROR_MESSAGE);
        }
        return new ByeCommand();
    }
}
