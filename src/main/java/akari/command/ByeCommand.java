package akari.command;

import akari.Akari;
import akari.storage.Storage;
import akari.task.TaskList;
import akari.ui.AkariException;
import akari.ui.Ui;

public class ByeCommand extends Command {
    public ByeCommand() {
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AkariException {
        Akari.setExitChat();
    }

    @Override
    public void showResult(TaskList taskList, Ui ui, Storage storage) {
        ui.printExitMessage();
    }
}
