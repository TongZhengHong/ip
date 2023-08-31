package dawson.commands;

import dawson.Dawson;
import dawson.TaskList;

public class Unmark extends Command {

    private String indexString;
    private TaskList taskList;

    public Unmark(String index, TaskList taskList) {
        this.indexString = index;
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        // Convert index into integer, ensure it is valid integer
        int index;
        try {
            index = Integer.parseInt(indexString);
        } catch (NumberFormatException e) {
            Dawson.printText("Invalid index! Unable to parse into integer");
            return;
        }

        // Check if index exists in tasklist
        index--; // Convert to 0-base indexing
        if (!taskList.isIndexValid(index)) {
            Dawson.printText(indexString + " index out of range of task list!");
            return;
        }

        // Execute the mark as done
        String updatedTask = taskList.unmarkIndex(index);
        String doneText = "Ok, I've marked this task as not done yet: " + System.lineSeparator();
        doneText += "\t " + updatedTask;
        Dawson.printText(doneText);
    }
    
}
