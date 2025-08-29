public class TaskManager {
    private static final int MAX_TASKS = 100;
    private static final Task[] taskList = new Task[MAX_TASKS];
    private static int taskCount = 0;

    public static void addTask(String message) {
        taskList[taskCount] = new Task(message);
        taskCount++;
        UI.printMessageWithBorder("added: " + message);
    }

    public static void printTaskList() {
        StringBuilder message = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            message.append(String.format("\n%d.%s", i + 1, taskList[i].getPrintTaskMessage()));
        }
        UI.printMessageWithBorder(message.toString());
    }

    public static void markTask(String description, boolean setMark) {
        int taskIndex = parseTaskIndex(description);
        if (taskIndex >= 0 && taskIndex < taskCount) {
            markTaskInTaskList(taskIndex, setMark);
        } else {
            taskNotInList();
        }
    }

    public static Integer parseTaskIndex(String description) {
        try {
            return Integer.parseInt(description) - 1;
        } catch (NumberFormatException e) {
            for (int i = 0; i < taskCount; i++) {
                if (taskList[i].description.equals(description)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static void markTaskInTaskList(int taskIndex, boolean setMark) {
        taskList[taskIndex].setDone(setMark);
        String message = setMark ? "Nice! I've marked this task as done:" : "OK, I've marked this task as not done yet:";
        message +=  "\n  " + taskList[taskIndex].getPrintTaskMessage();
        UI.printMessageWithBorder(message);
    }

    public static void taskNotInList() {
        ExpressionHandler.setExpression(Expression.SAD);
        UI.printMessageWithBorder("Task is not in the list");
    }
}
