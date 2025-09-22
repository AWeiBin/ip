package akari.task;

import akari.parser.Parser;
import akari.storage.Serialiser;
import akari.ui.AkariException;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList = new ArrayList<>();

    public TaskList() {
    }

    public TaskList(ArrayList<String> rawTaskList) throws AkariException {
        if (rawTaskList == null) {
            throw new AkariException();
        }
        ArrayList<ArrayList<String>> deserialiseTaskList = Serialiser.deserialiseList(rawTaskList);
        for (ArrayList<String> taskArguments : deserialiseTaskList) {
            Task task = new Parser().parseAddTask(taskArguments);
            task.setDone(taskArguments.get(1).equals("1"));
            add(task);
        }
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public void remove(int taskIndex) {
        taskList.remove(taskIndex);
    }

    public int getTaskCount() {
        return taskList.size();
    }

    public Task getTask(int taskIndex) {
        return taskList.get(taskIndex);
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int getValidatedTaskIndex(String description) throws AkariException {
        int taskIndex = parseTaskIndex(description);
        if (taskIndex < 0 || taskIndex >= getTaskCount()) {
            taskIndex = findTaskViaDescription(description);
        }
        if (taskIndex < 0) {
            throw new AkariException("There is no task in the list");
        }
        return taskIndex;
    }

    private int parseTaskIndex(String description) {
        try {
            return Integer.parseInt(description) - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private int findTaskViaDescription(String description) {
        for (int i = 0; i < getTaskCount(); i++) {
            if (taskList.get(i).description.equals(description)) {
                return i;
            }
        }
        return -1;
    }
}
