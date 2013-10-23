import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Level {

	private int levelNumber;
	private Field field;
	private int turnNumber;
	private Queue<Zombie>[] zombieQueue;
	private int[] numZombieInRow;
	private int numTurns;
	private ZombieFactory zFact;
	private ArrayList<java.util.Map.Entry<Integer, Zombie>>[] zombieList;

	/*
	 * instantiate a new field with field row and column
	 * 
	 * @param levelNumber level number
	 */
	public Level(int levelNumber) {
		String fileName = "level" + levelNumber;
		this.levelNumber = levelNumber;
		createZombieList();
		String[] fieldRows = this.loadLevel(fileName, levelNumber);
		field = new Field(fieldRows);
		turnNumber = 0;
		zFact = new ZombieFactory();
	}

	public void createZombieList() {

		zombieList = new ArrayList[Field.DEFAULT_MAX_ROW];
		for (int i = 0; i < Field.DEFAULT_MAX_ROW; i++) {
			zombieList[i] = new ArrayList<java.util.Map.Entry<Integer, Zombie>>();
		}

	}

	/*
	 * create an array of Queue for each row to store the Zombies
	 */
	/*
	 * public void createZombieQueue() { zombieQueue = new
	 * Queue[Field.DEFAULT_MAX_ROW]; for (int i = 0; i < Field.DEFAULT_MAX_ROW;
	 * i++) { zombieQueue[i] = new LinkedList<Zombie>(); } }
	 */

	/*
	 * Create Zombies in the specified row#
	 * 
	 * @param row row number of the field
	 * 
	 * @param turn the turn in which the Zombie enters the field
	 * 
	 * @param type the type of the zombie
	 */
	public void spawnZombie(int row, int turn, String type) {
		Zombie.Type zombieType = Zombie.Type.valueOf(type);
		Zombie z = ZombieFactory.makeZombie(zombieType);
		zombieList[row].add(new java.util.AbstractMap.SimpleEntry<>(turn, z));
	}

	/*
	 * Read File to get the information about this level
	 * 
	 * @param fileName the name of the file which contains level information
	 * 
	 * @param levelNumber level number
	 */
	public String[] loadLevel(String fileName, int levelNumber) {
		// This will reference one line at a time
		String line = null;
		String readLevel = "Level" + levelNumber;
		String[] fieldRows = new String[Field.DEFAULT_MAX_ROW];
		numZombieInRow = new int[Field.DEFAULT_MAX_ROW];

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				if (line.equals(readLevel)) {
					// total turns allowed for the user to play
					numTurns = Integer.parseInt(bufferedReader.readLine());
					System.out.println(numTurns);

					for (int i = 0; i < Field.DEFAULT_MAX_ROW; i++) {
						String[] rowContents = bufferedReader.readLine().split(
								" ");
						// read the terrian type for each row
						fieldRows[i] = rowContents[0];
						// read number of Zombies enetering a specic row
						numZombieInRow[i] = Integer.parseInt(rowContents[1]);
						while (numZombieInRow[i] > 0) {
							// read the turn number in which the Zombie enters
							// the field and the type
							String[] rowContents1 = bufferedReader.readLine()
									.split(" ");
							int turn = Integer.parseInt(rowContents1[0]);
							String type = rowContents1[1];
							spawnZombie(i, turn, type);
							numZombieInRow[i]--;

						}
					}
				}
			}
			// close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		}
		return fieldRows;
	}

	/*
	 * @return level Number
	 */
	public int getLevelNumber() {
		return levelNumber;
	}

	/*
	 * @return field of this level
	 */
	public Field getField() {
		return field;
	}

	/*
	 * @return turn number of the player
	 */
	public int getTurnNumber() {
		return turnNumber;
	}

	/*
	 * increment the turn number for ever user input
	 */
	public void incrementTurn() {
		if (turnNumber < numTurns) {
			turnNumber++;
			bringNewZombiesIn();
		}
	}

	/*
	 * bring the zombie into the firld when it is their turn
	 */
	public void bringNewZombiesIn() {
		for (int i = 0; i < Field.DEFAULT_MAX_ROW; i++) {
			if (zombieList[i].get(0).getKey() == turnNumber) {
				Zombie z = zombieList[i].remove(0).getValue();
				z.setSquare(this.field.getStrip()[i].getSquares()[Field.DEFAULT_MAX_POSN - 1]);
				this.field.getStrip()[i].getSquares()[Field.DEFAULT_MAX_POSN - 1]
						.add(z);
			}

		}
	}
}
