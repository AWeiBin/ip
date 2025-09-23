package akari.parser;

import akari.command.DateCommand;
import akari.ui.AkariException;

public class DateParser extends Parser {

    protected static final String COMMAND_WORD = "date";
    protected static final String ERROR_MESSAGE = "Here's the right date format: " + COMMAND_WORD + " <yyyy-mm-dd>";

    @Override
    protected DateCommand parseAndCreateCommand() throws AkariException {
        if (commandDescription.isEmpty()) {
            throw new AkariException(ERROR_MESSAGE);
        }
        return new DateCommand(parseDate(commandDescription));
    }
}
