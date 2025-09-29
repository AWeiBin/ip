package akari.parser;

import akari.command.UnmarkCommand;
import akari.ui.AkariException;

/**
 * Parse arguments to satisfy UnmarkCommand
 *
 * @throws AkariException if commands does not have the expected arguments
 */
public class UnmarkParser extends Parser {

    protected static final String COMMAND_WORD = "unmark";
    private static final String ERROR_MESSAGE = MISSING_ARG + COMMAND_WORD + " <description>";

    @Override
    protected UnmarkCommand parseAndCreateCommand() throws AkariException {
        if (commandDescription.isEmpty()) {
            throw new AkariException(ERROR_MESSAGE);
        }
        return new UnmarkCommand(commandDescription);
    }
}
