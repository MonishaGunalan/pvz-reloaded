package tmp;
import java.util.Deque;
import java.util.ArrayDeque;
import pvz.*;
import pvz.level.DeepCopy;
import pvz.level.GameModel;
import pvz.level.Level;
public class HistoryManager {
	private Deque<Level> undoStack;
	private Deque<Level> redoStack;
	private GameModel model;

	/**
	 * Constructor
	 * @param model Game whose state to modify
	 */
	public HistoryManager(GameModel model) {
		undoStack = new ArrayDeque<Level>();
		redoStack = new ArrayDeque<Level>();
		this.model = model;
	}

	/**
	 * Current level is pushed onto the undow stack,
	 * and is then set to the first level on the 
	 * redo stack.
	 * @return True if successful, false otherwise
	 */
	public boolean redo() {
		// If there's something on the undo stack
		if (!redoStack.isEmpty()) {
			// Make deep copy of current level and push onto 
			// undo stack
			Level savedLevel = (Level)DeepCopy.copy(model.getLevel());
			undoStack.addFirst(savedLevel);
			
			// Pop from undo stack and set to current level
			//model.setLevel(redoStack.removeFirst());

			return true;
		}

		// If code reaches here, redo failed;
		return false;
	}

	/**
	 * Current level is pushed onto the redo stack,
	 * and is then set to the first level on the 
	 * undo stack.
	 * @return True if successful, false otherwise
	 */
	public boolean undo() {
		// If there's something on the undo stack
		if (!undoStack.isEmpty()) {
			// Make deep copy of current level and push onto 
			// redo stack
			Level savedLevel = (Level)DeepCopy.copy(model.getLevel());
			redoStack.addFirst(savedLevel);
			
			// Pop from undo stack and set to current level
		//	model.setLevel(undoStack.removeFirst());

			return true;
		}

		// If code reaches here, undo failed;
		return false;
	}

	/**
	 * Creates a deep copy of the current level and
	 * pushes it onto the undo stack. Redos are cleared.
	 * @param level Level to be saved
	 * @return True if successful, false otherwise
	 */
	public boolean write(Level level) {
		if (level != null)  {
			// We are moving forward in time, and therefore
			// we should overwrite the redoStack
			redoStack.clear();

			// Make a deep copy of the level
			Level savedLevel = (Level)DeepCopy.copy(level);

			// Push deep copy onto redo stack
			undoStack.addFirst(savedLevel);
			return true;
		}

		return false;
	}

}
