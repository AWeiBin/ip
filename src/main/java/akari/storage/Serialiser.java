package akari.storage;

import java.util.ArrayList;

public class Serialiser {
    private static final String DELIMITER = "#";
    private static final String EXTRA_DELIMITER = "|";

    public static String serialiseMessage(String message) {
        return message.length() + DELIMITER + message + EXTRA_DELIMITER;
    }

    public static ArrayList<ArrayList<String>> deserialiseList(ArrayList<String> serialisedList) {
        ArrayList<ArrayList<String>> deserialisedList = new ArrayList<>();
        for (String serialisedMessage : serialisedList) {
            ArrayList<String> message = deserialiseMessage(serialisedMessage);
            deserialisedList.add(message);
        }
        return deserialisedList;
    }

    private static ArrayList<String> deserialiseMessage(String serialisedMessage) {
        ArrayList<String> message = new ArrayList<>();
        int currentIndex = 0;
        int serialisedTaskLength = serialisedMessage.length();

        while (currentIndex < serialisedTaskLength) {
            int delimiterIndex = serialisedMessage.indexOf(DELIMITER, currentIndex);
            boolean isDelimiterMissing = delimiterIndex == -1;
            if (isDelimiterMissing) {
                break;
            }

            int argumentLength = parseArgumentLength(serialisedMessage, currentIndex, delimiterIndex);
            boolean isArgumentLengthCorrupted = argumentLength == -1;
            if (isArgumentLengthCorrupted) {
                break;
            }

            currentIndex = delimiterIndex + 1;
            int nextIndex = currentIndex + argumentLength;
            if (nextIndex > serialisedTaskLength) {
                break;
            }

            String argument = serialisedMessage.substring(currentIndex, nextIndex);
            message.add(argument);
            currentIndex = nextIndex + 1;
        }

        return message;
    }

    private static int parseArgumentLength(String serialisedTask, int start, int end) {
        try {
            return Integer.parseInt(serialisedTask.substring(start, end));
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
