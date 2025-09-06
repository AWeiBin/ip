package akari.task;

import akari.ui.AkariException;
import akari.ui.UI;

public class TaskManager {
    private static final int MAX_TASKS = 100;
    private final Task[] taskList = new Task[MAX_TASKS];
    private int taskCount = 0;

    public void addTodo(String message) {
        taskList[taskCount] = new Todo(message);
        taskCount++;
        printAddedTaskMessage();
    }

    public void addDeadline(String description, String by) {
        taskList[taskCount] = new Deadline(description, by);
        taskCount++;
        printAddedTaskMessage();
    }

    public void addEvent(String message, String from, String to) {
        taskList[taskCount] = new Event(message, from, to);
        taskCount++;
        printAddedTaskMessage();
    }

    public void printAddedTaskMessage() {
        String message = "Got it. I've added this task:\n" +
                "    " + taskList[taskCount - 1].toString() + "\n" +
                "Now you have " + taskCount + " in the list";
        UI.printMessageWithBorder(message);
    }

    public void printTaskList() {
        StringBuilder message = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            message.append(String.format("\n%d.%s", i + 1, taskList[i].toString()));
        }
        UI.printMessageWithBorder(message.toString());
    }

    public void markTask(String description, boolean setMark) throws AkariException {
        if (description.isEmpty()) {
            throw new AkariException("Hey!! Give me the task number or description of the task you want to mark.");
        }
        int taskIndex = parseTaskIndex(description);
        if (taskIndex < 0 || taskIndex >= taskCount) {
            throw new AkariException("The task is not in the list");
        }
        markTaskInTaskList(taskIndex, setMark);
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
            if (taskList[i].description.equals(description)) {
                return i;
            }
        }
        return -1;
    }

    private void markTaskInTaskList(int taskIndex, boolean setMark) {
        taskList[taskIndex].setDone(setMark);
        String message = setMark ? "Nice! I've marked this task as done:" : "OK, I've marked this task as not done yet:";
        message += "\n    " + taskList[taskIndex].toString();
        UI.printMessageWithBorder(message);
    }
}
