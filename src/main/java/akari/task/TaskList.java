package akari.task;

import akari.parser.Parser;
import akari.storage.Serialiser;
import akari.ui.AkariException;

import java.util.ArrayList;

/**
 * Represents the entire TaskList. Contains the data of the TaskList.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Creates an empty TaskList and add task based on storage data
     *
     * @param rawTaskList task list from storage file
     */
    public TaskList(ArrayList<String> rawTaskList) throws AkariException {
        taskList = new ArrayList<>();
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

    /**
     * Get task based on task index
     *
     * @param taskIndex input task index
     * @return the task
     */
    public Task getTask(int taskIndex) {
        return taskList.get(taskIndex);
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Get task index based on input description
     * If parse input description into integer failed, compare input description with task description
     *
     * @param description input description
     * @return the task index
     * @throws AkariException if task is not in the list
     */
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
