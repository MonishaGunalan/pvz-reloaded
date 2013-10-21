import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Level {

	private int levelNumber;
	private Field field;
	private int turnNumber;
	private Queue<Zombie>[] zombieQueue;
	private int[] numZombieInRow;
	private int numTurns;

	// instantiate a new field with field row and column
	public Level(String fileName, int levelNumber) {
		this.levelNumber = levelNumber;
		createZombieQueue();
		String[] fieldRows = this.loadLevel(fileName, levelNumber);
		field = new Field(fieldRows);
		turnNumber = 0;

	}

	// create an array of Queue for each row to store the Zombies
	public void createZombieQueue() {
		zombieQueue = new Queue[Field.DEFAULT_MAX_ROW];
		for (int i = 0; i < Field.DEFAULT_MAX_ROW; i++) {
			zombieQueue[i] = new LinkedList<Zombie>();
		}
	}

	// Create Zombies in the specified row#
	public void spawnZombie(int row, int turn) {
		zombieQueue[row].add(new Zombie(turn, row, "normal"));
	}

	// Read File to get the information about this level
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
							// the field
							int turn = Integer.parseInt(bufferedReader
									.readLine());
							spawnZombie(i, turn);
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

	public int getLevelNumber() {
		return levelNumber;
	}

	public Field getField() {
		return field;
	}

	public int getTurnNumber() {
		return turnNumber;
	}

	public void incrementTurn() {
		if (turnNumber < numTurns) {
			turnNumber++;
			bringNewZombiesIn();
		}
	}

	public void bringNewZombiesIn() {
		for (int i = 0; i < Field.DEFAULT_MAX_ROW; i++) {
			if (zombieQueue[i].peek().getTurn() == turnNumber) {
				Zombie z = zombieQueue[i].remove();
				z.setPosition(i, Field.DEFAULT_MAX_POSN);
				this.field.getStrip()[i].getSquare()[Field.DEFAULT_MAX_POSN]
						.addZombie(z);

			}
		}
	}

	// *****Has to be removed - Just for testing
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Level("level.txt", 2);
	}
}
