package akari.parser;

import akari.command.DeadlineCommand;
import akari.task.Deadline;
import akari.ui.AkariException;

import java.util.ArrayList;

public class DeadlineParser extends Parser {
    protected static final String COMMAND_WORD = "deadline";
    protected static final String COMMAND_ICON = "D";
    private static final String BY_SEPARATOR = "/by ";
    private static final String ERROR_MESSAGE = MISSING_ARG + COMMAND_WORD + " <description> " + BY_SEPARATOR + "<date>";

    @Override
    protected DeadlineCommand parseAndCreateCommand() throws AkariException {
        String[] descriptionAndArgument = splitIntoTwoParts(commandDescription, BY_SEPARATOR);
        String description = descriptionAndArgument[0];
        String by = descriptionAndArgument[1];
        if (description.isEmpty() || by.isEmpty()) {
            throw new AkariException(ERROR_MESSAGE);
        }
        return new DeadlineCommand(description, parseDateTime(by));
    }

    protected Deadline parseAndCreateTask(ArrayList<String> taskArguments) throws AkariException {
        if (taskArguments.size() != 4) {
            return null;
        }
        return new Deadline(taskArguments.get(2), parseDateTime(taskArguments.get(3)));
    }
}
