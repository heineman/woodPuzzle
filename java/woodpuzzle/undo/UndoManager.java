package woodpuzzle.undo;

import java.util.*;

public class UndoManager {
	static UndoManager instance = null;

	/** Not visible outside of this package. */
	UndoManager() {

	}

	/** Undo stack. Commands are pushed onto here as they are undone. */
	Stack<ICommand> undoStack = new Stack<ICommand>();

	public static UndoManager instance() {
		if (instance == null) {
			instance = new UndoManager();
		}

		return instance;
	}

	/**
	 * Record the command which can be undone in the future. 
	 *
	 * @param move
	 */
	public void recordCommand(ICommand com) {
		undoStack.add(com);
	}

	/**
	 * Prepare for undo by getting last command.
	 */
	public ICommand removeLastMove() {
		if (undoStack.isEmpty()) { return null; }
		return undoStack.pop();
	}

	/** Reset all entries in undo stack. */
	public void clearState() {
		undoStack.clear();
	}
}
