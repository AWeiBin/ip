package akari.command;

public class Parser {

    private static final String BY_SEPARATOR = "/by ";
    private static final String FROM_SEPARATOR = "/from ";
    private static final String TO_SEPARATOR = "/to ";

    public static String[] splitCommandWordAndDescription(String userCommand) {
        String[] split = userCommand.split(" ", 2);
        String[] trimSplit = trimAllStringInList(split);
        return trimSplit.length == 2 ? trimSplit : new String[]{trimSplit[0], ""};
    }

    public static String[] trimAllStringInList(String[] rawStringList) {
        String[] trimmedString = new String[rawStringList.length];
        for (int i = 0; i < rawStringList.length; i++) {
            trimmedString[i] = rawStringList[i].trim();
        }
        return trimmedString;
    }

    public static String[] parseDeadlineArgument(String commandDescription) {
        String[] descriptionAndArgument = commandDescription.split(BY_SEPARATOR, 2);
        return trimAllStringInList(descriptionAndArgument);
    }

    public static String[] parseEventArgument(String commandDescription) {
        int fromIndex = commandDescription.indexOf(FROM_SEPARATOR);
        int toIndex = commandDescription.indexOf(TO_SEPARATOR);
        String description;
        String from;
        String to;
        if (fromIndex == -1 || toIndex == -1) {
            return null;
        }
        String[] descriptionAndArgument = commandDescription.split(FROM_SEPARATOR, 2);
        if (fromIndex < toIndex) {
            description = descriptionAndArgument[0];
            String[] arguments = descriptionAndArgument[1].split(TO_SEPARATOR, 2);
            from = arguments[0];
            to = arguments[1];
        } else {
            from = descriptionAndArgument[1];
            String[] arguments = descriptionAndArgument[0].split(TO_SEPARATOR, 2);
            description = arguments[0];
            to = arguments[1];
        }
        return trimAllStringInList(new String[]{description, from, to});
    }
}
