package akari.storage;

import java.util.ArrayList;

public class Serialiser {
    private static final String DELIMITER = "#";

    public static String serialiseMessage(String message) {
        return message.length() + DELIMITER + message;
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
            if (delimiterIndex == -1) {
                break;
            }

            int argumentLength = parseArgumentLength(serialisedMessage, currentIndex, delimiterIndex);
            if (argumentLength == -1) {
                break;
            }

            currentIndex = delimiterIndex + 1;
            int nextIndex = currentIndex + argumentLength;
            if (nextIndex > serialisedTaskLength) {
                break;
            }

            String argument = serialisedMessage.substring(currentIndex, nextIndex);
            message.add(argument);
            currentIndex = nextIndex;
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
