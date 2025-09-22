package akari.parser;

import akari.command.EventCommand;
import akari.task.Event;
import akari.ui.AkariException;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class EventParser extends Parser {
    protected static final String COMMAND_WORD = "event";
    protected static final String COMMAND_ICON = "E";
    private static final String FROM_SEPARATOR = "/from ";
    private static final String TO_SEPARATOR = "/to ";
    private static final String ERROR_MESSAGE = MISSING_ARG + COMMAND_WORD + " <description> " + FROM_SEPARATOR + "<date startTime> " + TO_SEPARATOR + "<endTime>";
    private static final String DATE_ERROR_MESSAGE = "From date time is before To date time";

    @Override
    protected EventCommand parseAndCreateCommand() throws AkariException {
        boolean isInOrder = determineIsFromBeforeTo();
        String[] descriptionAndArgument = splitIntoTwoParts(commandDescription, isInOrder ? FROM_SEPARATOR : TO_SEPARATOR);
        String[] arguments = splitIntoTwoParts(descriptionAndArgument[1], isInOrder ? TO_SEPARATOR : FROM_SEPARATOR);
        String description = descriptionAndArgument[0];
        String from = isInOrder ? arguments[0] : arguments[1];
        String to = isInOrder ? arguments[1] : arguments[0];
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new AkariException(ERROR_MESSAGE);
        }
        LocalDateTime fromDate = parseDateTime(from);
        LocalDateTime toDate = parseDateTime(to);
        if (fromDate.isAfter(toDate)) {
            throw new AkariException(DATE_ERROR_MESSAGE);
        }
        return new EventCommand(description, parseDateTime(from), parseDateTime(to));
    }

    private boolean determineIsFromBeforeTo() throws AkariException {
        int fromIndex = commandDescription.indexOf(FROM_SEPARATOR);
        int toIndex = commandDescription.indexOf(TO_SEPARATOR);
        if (fromIndex == -1 || toIndex == -1) {
            throw new AkariException(ERROR_MESSAGE);
        }
        return fromIndex < toIndex;
    }

    protected Event parseAndCreateTask(ArrayList<String> taskArguments) throws AkariException {
        if (taskArguments.size() != 5) {
            return null;
        }
        return new Event(taskArguments.get(2), parseDateTime(taskArguments.get(3)), parseDateTime(taskArguments.get(4)));
    }
}
