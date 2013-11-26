package pvz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Deque;
import java.util.ArrayDeque;

/**
 * The GameModel is the model for the Plant vs Zombie game
 * it is also the entry point for playing the game on text based
 *
 * @author Christopher Nguyen
 * @version 1.0
 * @since 1.7
 */
public class GameModel extends Observable {
	/**
	 * The Player playing the game
	 */
	Player player;
	/**
	 * The level that is loaded
	 */
	Level level;
	/**
	 * History stack for undos
	 */
	private Deque<Level> undoStack;
	/**
	 * History stack for redos
	 */
	private Deque<Level> redoStack;

	/**
	 * Message to print when player wins
	 */
	public static final String WIN_MSG = "You have bested the zombie apocalypse. Congratulations survivor!";
	/**
	 * Message to print when beats the level
	 */
	public static final String LOSE_MSG = "Oh no! The zombies ate your brains!";
	/**
	 * Message to print when player loses
	 */
	public static final String NEXT_LEVEL_MSG = "You beat the level! But more zombies are coming...";
	/**
	 * Number of implemented levels
	 */
	public static final int MAX_LEVEL = 5;

	/**
	 * The public constructor for Game Model
	 * If player data exists it is loaded and level and player is initialized
	 * 
	 */
	public GameModel(){
		level = new Level(1);
		player = new Player(this);	

		// Initialize undo and redo stacks
		undoStack = new ArrayDeque<Level>();
		redoStack = new ArrayDeque<Level>();
	}	

	/**
	 * Play the game
	 */
	public void play(){
		player.play();
	}

	/**
	 * Starts a new level for the current game
	 * level for the specified level number.
	 * @param levelNum The number of the level to load
	 */
	public void loadLevel(int levelNum) {
		// Clear history for new level
		redoStack.clear();
		undoStack.clear();
		this.level = new Level(levelNum);
		setChanged();
		this.notifyObservers(Player.PlayStatus.NORMAL);
	}

	/**
	 * Load the next level, if it exists
	 */
	public void loadNextLevel() {
		this.loadLevel(this.level.getLevelNumber() + 1);
	}

	/**
	 * Play the game with a given a command
	 * @param command
	 * @return
	 */
	public void play(PlayerCommand command){
		Player.PlayStatus result = player.play(command);

		setChanged();
		this.notifyObservers(result);
	}
	/**
	 * Get the player
	 * @return the player
	 */
	public Player getPlayer(){
		return player;
	}

	/**
	 * Get the level
	 * @return return the level
	 */
	public Level getLevel(){
		return level;
	}

	/**
	 * Set the level
	 */
	//public void setLevel(Level level){
	//this.level = level;
	//}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GameModel().play();
	}

	/**
	 * Current level is pushed onto the undow stack,
	 * and is then set to the first level on the 
	 * redo stack.
	 * @return True if successful, false otherwise
	 */
	public boolean redo() {
		//System.out.println("Calling redo!");
		//System.out.println("Current turn: " + level.getTurnNumber());
		// If there's something on the undo stack
		if (!redoStack.isEmpty()) {
			// Make deep copy of current level and push onto 
			// undo stack
			Level savedLevel = (Level)DeepCopy.copy(this.getLevel());
			undoStack.addFirst(savedLevel);

			// Pop from undo stack and set to current level
			this.level = redoStack.removeFirst();
			//System.out.println("Redo turn: " + level.getTurnNumber());
			//printStackSizes();
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
		//System.out.println("Calling undo!");
		//System.out.println("Current turn: " + level.getTurnNumber());
		// If there's something on the undo stack
		if (!undoStack.isEmpty()) {
			// Make deep copy of current level and push onto 
			// redo stack
			Level savedLevel = (Level)DeepCopy.copy(this.getLevel());
			redoStack.addFirst(savedLevel);

			// Pop from undo stack and set to current level
			this.level = undoStack.removeFirst();
			//System.out.println("Undo turn: " + level.getTurnNumber());
			//printStackSizes();
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
	public boolean writeHistory() throws java.io.NotSerializableException {
		if (this.level != null)  {
			// We are moving forward in time, and therefore
			// we should overwrite the redoStack
			redoStack.clear();

			// Make a deep copy of the level
			Level savedLevel = (Level)DeepCopy.copy(level);

			if (savedLevel == null) {
				throw new java.io.NotSerializableException();
			}
			// Push deep copy onto redo stack
			undoStack.addFirst(savedLevel);
			//System.out.println("Writing to history!");

		}

		return false;
	}

	//@Override
	//public void update(Observable o, Object args) {
		//this.writeHistory();
	//}

	//private void printStackSizes() {
		//System.out.println("Undo stack size: " + undoStack.size());
		//System.out.println("Redo stack size: " + redoStack.size());
	//}
}

