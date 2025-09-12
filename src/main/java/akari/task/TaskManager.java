package akari.task;

import akari.ui.AkariException;
import akari.ui.UI;

import java.util.ArrayList;

public class TaskManager {
    private final ArrayList<Task> taskList = new ArrayList<>();
    private int taskCount = 0;

    public void addTodo(String message) {
        taskList.add(new Todo(message));
        printAddedTaskMessage(true, taskCount);
        taskCount++;
    }

    public void addDeadline(String description, String by) {
        taskList.add(new Deadline(description, by));
        printAddedTaskMessage(true, taskCount);
        taskCount++;
    }

    public void addEvent(String message, String from, String to) {
        taskList.add(new Event(message, from, to));
        printAddedTaskMessage(true, taskCount);
        taskCount++;
    }

    public void deleteTask(String description) throws AkariException {
        int taskIndex = getValidatedTaskIndex(description);
        printAddedTaskMessage(false, taskIndex);
        taskList.remove(taskIndex);
        taskCount--;
    }

    public void printAddedTaskMessage(boolean isAddTask, int taskIndex) {
        String message = "Got it. I've " + (isAddTask ? "added" : "removed") + " this task:\n" +
                "    " + taskList.get(taskIndex).toString() + "\n" +
                "Now you have " + (taskCount + (isAddTask ? 1 : -1)) + " in the list";
        UI.printMessageWithBorder(message);
    }

    public void printTaskList() {
        StringBuilder message = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            message.append(String.format("\n%d.%s", i + 1, taskList.get(i).toString()));
        }
        UI.printMessageWithBorder(message.toString());
    }

    public void markTask(String description, boolean setMark) throws AkariException {
        int taskIndex = getValidatedTaskIndex(description);
        markTaskInTaskList(taskIndex, setMark);
    }

    private int getValidatedTaskIndex(String description) throws AkariException {
        if (description.isEmpty()) {
            throw new AkariException("Hey!! Give me the task number or description of the task.");
        }
        int taskIndex = parseTaskIndex(description);
        if (taskIndex < 0 || taskIndex >= taskCount) {
            throw new AkariException("The task is not in the list");
        }
        return taskIndex;
    }

    private Integer parseTaskIndex(String description) {
        try {
            int taskIndex = Integer.parseInt(description) - 1;
            if (taskIndex > taskCount - 1) {
                return findTaskViaDescription(description);
            }
            return taskIndex;
        } catch (NumberFormatException e) {
            return findTaskViaDescription(description);
        }
    }

    private Integer findTaskViaDescription(String description) {
        for (int i = 0; i < taskCount; i++) {
            if (taskList.get(i).description.equals(description)) {
                return i;
            }
        }
        return -1;
    }

    private void markTaskInTaskList(int taskIndex, boolean setMark) {
        taskList.get(taskIndex).setDone(setMark);
        String message = setMark ? "Nice! I've marked this task as done:" : "OK, I've marked this task as not done yet:";
        message += "\n    " + taskList.get(taskIndex).toString();
        UI.printMessageWithBorder(message);
    }
}
