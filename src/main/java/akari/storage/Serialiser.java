package akari.storage;

import akari.ui.AkariException;

import java.util.ArrayList;

public class Serialiser {
    private static final String DELIMITER = "#";

    /** Extra delimiter to make it looks neater */
    private static final String EXTRA_DELIMITER = "|";

    /**
     * Serialise the message
     *
     * @param message text to be added into the file
     * @return the serialise message
     */
    public static String serialiseMessage(String message) {
        return message.length() + DELIMITER + message + EXTRA_DELIMITER;
    }

    /**
     * Deserialise the list
     *
     * @param serialisedList list of all the lines in the file
     * @return the deserialise message in arguments list
     */
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

            currentIndex = delimiterIndex + DELIMITER.length();
            int nextIndex = currentIndex + argumentLength;
            if (nextIndex > serialisedTaskLength) {
                break;
            }

            String argument = serialisedMessage.substring(currentIndex, nextIndex);
            message.add(argument);
            currentIndex = nextIndex + EXTRA_DELIMITER.length();
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
