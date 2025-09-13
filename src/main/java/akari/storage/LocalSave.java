package akari.storage;

import akari.ui.AkariException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LocalSave {

    private static final String FILEPATH = "./data/akari.txt";

    public static void ensureFileDirectoryExist() {
        new File(FILEPATH).getParentFile().mkdirs();
    }

    public static ArrayList<String> readTaskListFromFile() {
        try {
            ensureFileDirectoryExist();
            return readFromFile();
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    private static ArrayList<String> readFromFile() throws FileNotFoundException {
        File file = new File(FILEPATH);
        Scanner s = new Scanner(file);
        ArrayList<String> rawTaskList = new ArrayList<>();
        while (s.hasNext()) {
            rawTaskList.add(s.nextLine());
        }
        return rawTaskList;
    }

    public static void saveTaskListToFile(String textToAdd) throws AkariException {
        try {
            ensureFileDirectoryExist();
            writeToFile(textToAdd);
        } catch (IOException e) {
            throw new AkariException("I failed to save your tasks file");
        }
    }

    private static void writeToFile(String textToAdd) throws IOException {
        FileWriter fileWriter = new FileWriter(FILEPATH);
        fileWriter.write(textToAdd);
        fileWriter.close();
    }
}
