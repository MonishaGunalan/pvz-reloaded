package pvz;

/**
 * The level keeps track of
 * it keeps track of the current score, sun points and level
 *
 * @author Monisha Gunalan
 * 100871444
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.io.Serializable;

public class Level extends Observable implements Observer, Serializable {
	/**
	 * Serialization UID Do not change unless serialization with previous
	 * versions become incompatible
	 */
	static final long serialVersionUID = 4414314726650789404L;

	/**
	 * The current level number
	 */
	private int levelNumber;
	/**
	 * The field being played on
	 */
	private Field field;
	/**
	 * The current turn number
	 */
	private int turnNumber;
	/**
	 * The total Number of Zombies in the level
	 */
	private int totalZombies;
	/**
	 * The list of zombies to be brought into the level
	 */
	private ZombieRow[] zombieList;
	private ArrayList<java.util.Map.Entry<Integer, Zombie>>[] zombieList;
	/**
	 * Win if all zombies are dead. Lose
	 * conditions are checked first.
	 */
	private boolean hasWon;
	/**
	 * Lose if a zombie reaches are end of a strip. Lose
	 * conditions are checked first.
	 */
	private boolean hasLost;

	/**
	 * Constructor instantiate a new field with field row and column
	 * 
	 * @param levelNumber
	 *            level number
	 */
	public Level(int levelNumber) {
		String fileName = "rsrc/level" + levelNumber + ".txt";
		this.levelNumber = levelNumber;
		createZombieList();
		String[] fieldRows = this.loadLevel(fileName, levelNumber);
		field = new Field(fieldRows, this);
		turnNumber = 0;
		totalZombies = 0;
		// Win/lose
		hasWon = false;
		hasLost = false;
	}

	/**
	 * create an array of ArrayList for each row to store the Zombies
	 */
	public void createZombieList() {

		zombieList = new ZombieRow[Field.DEFAULT_MAX_ROW];
		for (int i = 0; i < Field.DEFAULT_MAX_ROW; i++) {
			zombieList[i] = new ZombieRow();
		}

	}

	/**
	 * Create Zombies in the specified row#
	 * 
	 * @param row
	 *            row number of the field
	 * 
	 * @param turn
	 *            the turn in which the Zombie enters the field
	 * 
	 * @param type
	 *            the type of the zombie
	 */
	public void spawnZombie(int row, int turn, String type) {
		if (row < 0 || row >= Field.DEFAULT_MAX_ROW || turn < 0 || type == null) {
			throw new IllegalArgumentException(
					"Wrong paremeters passed for spawning zombie");
		}
		Zombie.Type zombieType = Zombie.Type.valueOf(type.toUpperCase());
		Zombie z = ZombieFactory.makeZombie(zombieType);
		ZombieTurn zt = new ZombieTurn(z, turn);
		zombieList[row].add(zt);
	}

	/**
	 * Read File to get the information about this level
	 * 
	 * @param fileName
	 *            the name of the file which contains level information
	 * 
	 * @param levelNumber
	 *            level number
	 */
	public String[] loadLevel(String fileName, int levelNumber) {
		// This will reference one line at a time
		String line = null;
		String readLevel = "Level" + levelNumber;
		String[] fieldRows = new String[Field.DEFAULT_MAX_ROW];
		int[] numZombieInRow = new int[Field.DEFAULT_MAX_ROW];

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				if (line.equals(readLevel)) {

					for (int i = 0; i < Field.DEFAULT_MAX_ROW; i++) {
						String[] rowContents = bufferedReader.readLine().split(
								" ");
						if (rowContents.length <= 1) {
							// Something must be wrong with the level config
							// file
							System.out.println("Invalid level " + levelNumber
									+ " config file");
							System.exit(-1);
						}
						if (Strip.Terrain.MUD != null) {
							// read the terrian type for each row
							fieldRows[i] = rowContents[0];
							// read number of Zombies enetering a specic row
							numZombieInRow[i] = Integer
									.parseInt(rowContents[1]);
							totalZombies += Integer.parseInt(rowContents[1]);
							System.out
									.println("totalZombies = " + totalZombies);

							while (numZombieInRow[i] > 0) {
								// read the turn number in which the Zombie
								// enters
								// the field and the type
								String[] rowContents1 = bufferedReader
										.readLine().split(" ");
								int turn = Integer.parseInt(rowContents1[0]);
								String type = rowContents1[1];
								spawnZombie(i, turn, type);
								numZombieInRow[i]--;

							}
						}
					}
				}
			}
			// close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
			System.exit(-1);
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			System.exit(-1);
		}
		return fieldRows;
	}

	/**
	 * @return level Number
	 */
	public int getLevelNumber() {
		return levelNumber;
	}

	/**
	 * @return field of this level
	 */
	public Field getField() {
		return field;
	}

	/**
	 * @return turn number of the player
	 */
	public int getTurnNumber() {
		return turnNumber;
	}

	/**
	 * increment the turn number for ever user input
	 */
	public void incrementTurn() {
		// Increment the turn
		turnNumber++;
		// Bring in new zombies and notify all observers that the turn has
		// incremented
		bringNewZombiesIn();
		setChanged();
		notifyObservers();
	}

	/**
	 * bring the zombie into the field when it is their turn
	 */
	public void bringNewZombiesIn() {
		for (int i = 0; i < Field.DEFAULT_MAX_ROW; i++) {
			if (!zombieList[i].isEmpty()) {
				Zombie z = zombieList[i].getZombie(turnNumber);
				addObserver(z);
				Square lastSquareInStrip = this.field.getStrip()[i]
						.getSquares()[Field.DEFAULT_MAX_POSN - 1];
				System.out.println("Putting a zombie on "
						+ lastSquareInStrip.getLoc());
				z.setSquare(lastSquareInStrip);
				lastSquareInStrip.evaluateZombie(z);
			}
		}
	}

	/**
	 * Update method from zombie if it has reached the end
	 */
	public void update(Observable o, Object arg) {
		if (arg instanceof Zombie) {
			// Zombie has reached end strip moving left. Handle here
			//System.out.println("Zombie ate your brains!");
			hasLost = true;
		} else if (arg instanceof String) {
			String s = (String)arg;
			switch (s) {
				case "zombie died":
					totalZombies--;
				case default:
					break;
			}
		}
	}

	/**
	 * Gets a square based on the row and col position
	 * 
	 * @param row
	 *            The row to get the square from
	 * @param col
	 *            The col to get the square from
	 * @return The square
	 */
	public Square getSquare(int row, int col) {
		return field.getStrip()[row].getSquare(col);
	}

	/**
	 * @return totalZombies The total number of Zombies
	 */
	public int getTotalZombies() {
		return totalZombies;
	}

}
