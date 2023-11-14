package command;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Stack;

public class CommandRegistry {

	private Stack<Command> undoStack = new Stack<>();
	private Stack<Command> redoStack = new Stack<>();

	private ObservableList<Command> commandStack = FXCollections
			.observableArrayList();

	public void executeCommand(Command command) {
		command.execute();
		commandStack.add(command);
	}

	public void redo() {
		if(redoStack.isEmpty()) {
			return;
		}
		Command command = redoStack.pop();
		command.redo();
		commandStack.add(command);
	}

	public void undo() {
		if(undoStack.isEmpty()) {
			return;
		}
		Command command = undoStack.pop();
		command.undo();
		commandStack.remove(command);
		
	}

	public ObservableList<Command> getCommandStack() {
		return commandStack;
	}
}
