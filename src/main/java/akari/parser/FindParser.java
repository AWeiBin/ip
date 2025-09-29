package akari.parser;

import akari.command.FindCommand;
import akari.ui.AkariException;

/**
 * Parse arguments to satisfy FindCommand
 *
 * @throws AkariException if commands does not have the expected arguments
 */
public class FindParser extends Parser {

    protected static final String COMMAND_WORD = "find";
    private static final String ERROR_MESSAGE = MISSING_ARG + COMMAND_WORD + " <description>";

    @Override
    protected FindCommand parseAndCreateCommand() throws AkariException {
        if (commandDescription.isEmpty()) {
            throw new AkariException(ERROR_MESSAGE);
        }
        return new FindCommand(commandDescription);
    }
}
