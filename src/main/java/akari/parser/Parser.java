package akari.parser;

import akari.command.Command;
import akari.task.Task;
import akari.ui.AkariException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Parser {

    protected static final String EXTRA_ARG = "Ahhh! Too many arguments\nHere's the right format: ";
    protected static final String MISSING_ARG = "Ahhh! I can't work with missing arguments\nHere's the right format: ";
    protected static final String DATETIME_ERROR_MESSAGE = "Here's the right datetime format: <yyyy-mm-dd>T<hh:mm>";

    protected static String commandDescription;

    public Command parseCommand(String userCommand) throws AkariException {
        Parser commandParser = selectParser(userCommand);
        return commandParser.parseAndCreateCommand();
    }

    private Parser selectParser(String userCommand) throws AkariException {
        String[] commandWordAndDescription = splitIntoTwoParts(userCommand, " ");
        String commandWord = commandWordAndDescription[0];
        commandDescription = commandWordAndDescription[1];
        switch (commandWord) {
        case ByeParser.COMMAND_WORD:
            return new ByeParser();
        case ListParser.COMMAND_WORD:
            return new ListParser();
        case MarkParser.COMMAND_WORD:
            return new MarkParser();
        case UnmarkParser.COMMAND_WORD:
            return new UnmarkParser();
        case TodoParser.COMMAND_WORD:
            return new TodoParser();
        case DeadlineParser.COMMAND_WORD:
            return new DeadlineParser();
        case EventParser.COMMAND_WORD:
            return new EventParser();
        case DeleteParser.COMMAND_WORD:
            return new DeleteParser();
        case DateParser.COMMAND_WORD:
            return new DateParser();
        default:
            throw new AkariException("The command you have entered is unavailable. Please try again later.");
        }
    }

    public Task parseAddTask(ArrayList<String> taskArguments) throws AkariException {
        Parser taskParser = selectAddParser(taskArguments.get(0));
        return taskParser.parseAndCreateTask(taskArguments);
    }

    private Parser selectAddParser(String commandIcon) throws AkariException {
        switch (commandIcon) {
        case TodoParser.COMMAND_ICON:
            return new TodoParser();
        case DeadlineParser.COMMAND_ICON:
            return new DeadlineParser();
        case EventParser.COMMAND_ICON:
            return new EventParser();
        default:
            throw new AkariException();
        }
    }

    protected String[] splitIntoTwoParts(String userCommand, String regex) {
        String[] split = userCommand.split(regex, 2);
        String[] trimSplit = trimAllStringInList(split);
        return trimSplit.length == 2 ? trimSplit : new String[]{trimSplit[0], ""};
    }

    private String[] trimAllStringInList(String[] rawStringList) {
        String[] trimmedString = new String[rawStringList.length];
        for (int i = 0; i < rawStringList.length; i++) {
            trimmedString[i] = rawStringList[i].trim();
        }
        return trimmedString;
    }

    protected Command parseAndCreateCommand() throws AkariException {
        throw new AkariException("This method is to be implemented by child classes");
    }

    protected Task parseAndCreateTask(ArrayList<String> taskArguments) throws AkariException {
        throw new AkariException("This method is to be implemented by child classes");
    }

    protected LocalDateTime parseDateTime(String dateTime) throws AkariException {
        try {
            return LocalDateTime.parse(dateTime);
        } catch (DateTimeParseException e) {
            throw new AkariException(DATETIME_ERROR_MESSAGE);
        }
    }

    protected LocalDate parseDate(String date) throws AkariException {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException  e) {
            throw new AkariException(DateParser.ERROR_MESSAGE);
        }
    }
}
