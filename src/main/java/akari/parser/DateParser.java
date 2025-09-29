package akari.parser;

import akari.command.DateCommand;
import akari.ui.AkariException;

/**
 * Parse arguments to satisfy DateCommand
 *
 * @throws AkariException if commands does not have the expected arguments
 */
public class DateParser extends Parser {

    protected static final String COMMAND_WORD = "date";
    protected static final String ERROR_MESSAGE = "Here's the right format: " + COMMAND_WORD + " <yyyy-mm-dd>";

    @Override
    protected DateCommand parseAndCreateCommand() throws AkariException {
        if (commandDescription.isEmpty()) {
            throw new AkariException(ERROR_MESSAGE);
        }
        return new DateCommand(parseDate(commandDescription));
    }
}
