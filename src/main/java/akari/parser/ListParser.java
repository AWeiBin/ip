package akari.parser;

import akari.command.ListCommand;
import akari.ui.AkariException;

/**
 * Parse arguments to satisfy ListCommand
 *
 * @throws AkariException if commands does not have the expected arguments
 */
public class ListParser extends Parser {

    protected static final String COMMAND_WORD = "list";
    private static final String ERROR_MESSAGE = EXTRA_ARG + COMMAND_WORD;

    @Override
    protected ListCommand parseAndCreateCommand() throws AkariException {
        if (!commandDescription.isEmpty()) {
            throw new AkariException(ERROR_MESSAGE);
        }
        return new ListCommand();
    }
}
