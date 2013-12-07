package pvz.level;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Observable;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Timer;
import java.util.TimerTask;

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
	private String playerName;
	public final static String PLAYERS_FILENAME = "names.txt";

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
	public static final int MIN_LEVEL = 1;
	public static final int MAX_LEVEL = 5;

	public static final String DEFAULT_PLAYER_NAME = "ADMIN";
	/**
	 * The task done at the timer
	 */
	private TimerTask task;
	/**
	 * The timer
	 */
	Timer t;
	/**
	 * whether or not the game is being played in real time
	 */
	boolean realTime;
	/**
	 * Default constructor
	 */
	public GameModel() {
		this(false, true);
	}

	/**
	 * The public constructor for Game Model
	 * If player data exists it is loaded and level and player is initialized
	 * 
	 */
	public GameModel(boolean realTime, boolean startNew, String playerName){
		this.playerName = playerName;
		// Loads the game depending on the playername 

		if (startNew) {
			this.level = new Level(1);
		} else {
			this.load();
		}
		player = new Player(this);	

		// Initialize undo and redo stacks
		undoStack = new ArrayDeque<Level>();
		redoStack = new ArrayDeque<Level>();
		this.realTime = realTime;
		if (realTime){
			startTimer();
		}
	}	

	public GameModel(boolean realTime, boolean startNew) {
		this(realTime, startNew, DEFAULT_PLAYER_NAME);
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
		// Handle invalid level
		if (levelNum < MIN_LEVEL || levelNum > MAX_LEVEL) {
			throw new IllegalArgumentException("No such level to be loaded.");
		}
		// Clear history for new level
		redoStack.clear();
		undoStack.clear();
		this.level = new Level(levelNum);
		setChanged();
		this.notifyObservers(Player.PlayStatus.NORMAL);
		if (realTime){
			stopTimer();
			startTimer();
		}
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
		if (result == Player.PlayStatus.GAMEOVER || Player.PlayStatus.VICTORY== result){
			stopTimer();
		}
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
		//	stopTimer();
			// Make deep copy of current level and push onto 
			// redo stack
			Level savedLevel = (Level)DeepCopy.copy(this.getLevel());
			redoStack.addFirst(savedLevel);

			// Pop from undo stack and set to current level
			this.level = undoStack.removeFirst();
			//System.out.println("Undo turn: " + level.getTurnNumber());
			//printStackSizes();
	//		startTimer();
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
	
	/**
	 * Starts the timer if is running in real time
	 */
	public void startTimer(){
		if (!realTime){
			return;
		}
		task = new TimerTask(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				GameModel.this.play(new PlayerCommand(PlayerCommand.CommandType.DO_NOTHING));
			}
			
		};
		t = new Timer();
		t.scheduleAtFixedRate(task, 2000, 2000);
		
	}
	
	/**
	 * Stops the timer if is running in real time
	 */
	public void stopTimer(){
		
		if (!realTime){
			return;
		}
		t.cancel();
	}
	
	public boolean isRealTime(){
		return realTime;
	}

	//@Override
	//public void update(Observable o, Object args) {
		//this.writeHistory();
	//}

	//private void printStackSizes() {
		//System.out.println("Undo stack size: " + undoStack.size());
		//System.out.println("Redo stack size: " + redoStack.size());
	//}
	/**
	 * Saves current level to default file
	 */
	public void save() {
		try {
			// Create file if doesnt exit
			File f = new File(playerName + ".ser");
			try {
				f.createNewFile();
			} catch(Exception e) {
			}
			//Open file to write to
			FileOutputStream fos = new FileOutputStream(playerName + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fos);
			// Get a copy of current level
			Level savedLevel = (Level)DeepCopy.copy(this.getLevel());
			// Write to file
			out.writeObject(savedLevel);
			out.close();
		} catch(IOException e) {
			e.printStackTrace();
		} 
	}


	/**
	 * Loads the default file to be played
	 */
	public void load() {
		Level savedLevel = null;

		try {
			// Get file to open
			FileInputStream fos = new FileInputStream(playerName + ".ser");
			ObjectInputStream in = new ObjectInputStream(fos);
			// Read the level
			savedLevel = (Level)in.readObject();
			// Set current level
			this.level = savedLevel;
			in.close();
		} catch(Exception e) {
			this.loadNew();
			//e.printStackTrace();
		}
		// If we couldnt open the file, start from level 1
		
	}

	public void loadNew() {
		this.level = new Level(1);
	}

	/**
	 * Returns if there is any undo left
	 * @return true if the undo stack is not empty
	 */
	public boolean hasUndo(){
		return !undoStack.isEmpty();
	}
	
	/**
	 * Returns if there is any undo left
	 * @return true if the redo stack is not empty
	 */
	public boolean hasRedo(){
		return !redoStack.isEmpty();
	}
}

