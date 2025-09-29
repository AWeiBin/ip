package akari.storage;

import akari.ui.AkariException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Create the folders to the file
     */
    public void ensureFileDirectoryExist() {
        new File(filePath).getParentFile().mkdirs();
    }

    /**
     * Read the file in filePath into a list
     * @return the file string in list else null if FileNotFoundException
     */
    public ArrayList<String> load() {
        try {
            ensureFileDirectoryExist();
            return readFromFile();
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    private ArrayList<String> readFromFile() throws FileNotFoundException {
        File file = new File(filePath);
        Scanner s = new Scanner(file);
        ArrayList<String> rawTaskList = new ArrayList<>();
        while (s.hasNext()) {
            rawTaskList.add(s.nextLine());
        }
        return rawTaskList;
    }

    /**
     * Save the TaskList into the file in filePath
     *
     * @param textToAdd text to be added into the file
     * @throws AkariException unable to write to the file
     */
    public void saveTaskListToFile(String textToAdd) throws AkariException {
        try {
            ensureFileDirectoryExist();
            writeToFile(textToAdd);
        } catch (IOException e) {
            throw new AkariException("I failed to save your tasks file");
        }
    }

    private void writeToFile(String textToAdd) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(textToAdd);
        fileWriter.close();
    }
}
