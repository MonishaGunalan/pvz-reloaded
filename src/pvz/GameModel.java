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
public class GameModel 
	extends Observable {
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
	 * The location where the player data will be stored (Not needed)
	 */
	public static final String playerDataFileLocation = "../rsrc/PlayerData.txt";


	/**
	 * The public constructor for Game Model
	 * If player data exists it is loaded and level and player is initialized
	 * 
	 */
	public GameModel(){

		File f = new File(playerDataFileLocation);

	//if the file exists then read the file and initialize based on the file
		if (f.exists()){
			try {
				BufferedReader reader = new BufferedReader(new FileReader(f));
				int levelNum = Integer.parseInt(reader.readLine());
				int score =  Integer.parseInt(reader.readLine());
				level = new Level(levelNum);
				player = new Player(this);
				player.setScore(score);
			} catch (NumberFormatException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch(IOException e){
				e.printStackTrace();
			}
		} else {
			//If no file exists initialize with level 1
			level = new Level(1);
			player = new Player(this);	
		}
		System.out.println("initializing deques...");

		// Initialize undo and redo stacks
		undoStack = new ArrayDeque<Level>();
		redoStack = new ArrayDeque<Level>();
		// Register this to level so it knows when turn increments
		// for history writing
		//level.addObserver(this);
	}	

	/**
	 * Play the game
	 */
	public void play(){
		player.play();
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
		System.out.println("Calling redo!");
		System.out.println("Current turn: " + level.getTurnNumber());
		// If there's something on the undo stack
		if (!redoStack.isEmpty()) {
			// Make deep copy of current level and push onto 
			// undo stack
			Level savedLevel = (Level)DeepCopy.copy(this.getLevel());
			undoStack.addFirst(savedLevel);

			// Pop from undo stack and set to current level
			this.level = redoStack.removeFirst();
			System.out.println("Redo turn: " + level.getTurnNumber());

			printStackSizes();
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
		System.out.println("Calling undo!");
		System.out.println("Current turn: " + level.getTurnNumber());
		// If there's something on the undo stack
		if (!undoStack.isEmpty()) {
			// Make deep copy of current level and push onto 
			// redo stack
			Level savedLevel = (Level)DeepCopy.copy(this.getLevel());
			redoStack.addFirst(savedLevel);

			// Pop from undo stack and set to current level
			this.level = undoStack.removeFirst();
			System.out.println("Undo turn: " + level.getTurnNumber());

			printStackSizes();
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
	public boolean writeHistory() {
		if (this.level != null)  {
			if (redoStack == null) {
				System.out.println("redostack level is null =(");
			}
			// We are moving forward in time, and therefore
			// we should overwrite the redoStack
			redoStack.clear();

			// Make a deep copy of the level
			Level savedLevel = (Level)DeepCopy.copy(level);

			// Push deep copy onto redo stack
			undoStack.addFirst(savedLevel);
			System.out.println("Writing to history!");
			return true;
		}

		return false;
	}

	//@Override
	//public void update(Observable o, Object args) {
		//this.writeHistory();
	//}

	private void printStackSizes() {
		System.out.println("Undo stack size: " + undoStack.size());
		System.out.println("Redo stack size: " + redoStack.size());
	}
}

