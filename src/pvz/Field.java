package pvz;

/**
 * Field contains a list of strips which in turn contains square this class
 * creates the field for the game
 * 
 * @author Monisha Gunalan 100871444
 */
import java.io.Serializable;

public class Field
	implements Serializable {
	/**
	 * Serialization UID
	 * Do not change unless serialization with previous versions become
	 * incompatible
	 */
	static final long serialVersionUID = -2201619968380616672L;

	/**
	 * The default number of rows in the field
	 */
	public static final int DEFAULT_MAX_ROW = 5;
	/**
	 * The default number of columns in a row
	 */
	public static final int DEFAULT_MAX_POSN = 10;
	/**
	 * The number of periods before the field generates sun points
	 */
	public static final int SUN_GENERATION_PERIOD = 3;
	/**
	 * The number of sun points generated
	 */
	public static final int SUN_GENERATION_VALUE = 25;

	/**
	 * The level which the field is located
	 */
	private final Level level;
	/**
	 * The sun generator
	 */
	private final SunGenerator sunGenerator;

	/**
	 * The number of sun point
	 */
	private int totalSun;

	/**
	 * The possible direction units can move
	 *
	 */
	public enum Direction {
		LEFT, RIGHT;
	}

	/**
	 * The strips of the field
	 */
	private Strip[] strips;

	/**
	 * @param TerrianType
	 *            [] the terrian type for each row
	 */
	public Field(String[] terrainType, Level level) {
		// Create field
		strips = new Strip[DEFAULT_MAX_ROW];
		for (int i = 0; i < DEFAULT_MAX_ROW; i++) {
			strips[i] = new Strip(terrainType[i], i, this);
		}

		// Reference to current level
		this.level = level;

		// Generator for adding sun to totalSun each turn
		sunGenerator = new SunGenerator(SUN_GENERATION_PERIOD,
				SUN_GENERATION_VALUE, this);

		// Initialize totalSun
		totalSun = 100;
	}

	/**
	 * 
	 * @return return the level that corresponds to the field
	 */
	public Level getLevel() {
		return this.level;
	}

	/**
	 * @return Strip[] returns the array of strips in the field
	 */
	public Strip[] getStrip() {
		return strips;
	}

	/**
	 * get the total sun generated
	 * 
	 * @return sun points
	 */
	public int getTotalSun() {
		return this.totalSun;
	}

	/**
	 * @return returns the string representation of the field
	 */
	public String toString() {
		String s = "";
		s += "Current turn: " + level.getTurnNumber();
		s += "\n";
		for (int i = 0; i < Field.DEFAULT_MAX_ROW; i++) {
			s += strips[i].toString();
		}
		s += "\n";
		return s;
	}

	/**
	 * Add sun points to the field
	 * 
	 * @param int amount of sun points to be added
	 */
	public void addSun(int amt) {
		if (amt > 0) {
			this.totalSun += amt;
		}
	}

	/**
	 * Use sun points from the field
	 * 
	 * @param int amount of sun points to be used
	 */
	public void useSun(int amt) {
		int currentSunPoints = getTotalSun();
		System.out.println("before sun:" + getTotalSun());
		if (currentSunPoints >= amt) {
			this.totalSun -= amt;
		}
		System.out.println("after sun:" + getTotalSun());
	}

}
