/*
 * @author Monisha Gunalan
 * 100871444
 */
import java.util.ArrayList;
import java.util.List;

public class Field {

	public static final int DEFAULT_MAX_ROW = 5;
	public static final int DEFAULT_MAX_POSN = 5;
	public static final int SUN_GENERATION_PERIOD=3;
	public static final int SUN_GENERATION_VALUE=25;

	private final Level level;
	private final SunGenerator sunGenerator;

	private int totalSun;

	public enum Direction {
		LEFT, RIGHT;
	}

	private Strip[] strips;
	/*
	 * @param TerrianType[]  the terrian type for each row
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
		sunGenerator = new SunGenerator(SUN_GENERATION_PERIOD, SUN_GENERATION_VALUE, this);

		// Initialize totalSun
		totalSun = 0;
	}

	public Level getLevel() {
		return this.level;
	}


	/*
	 * @return Strip[] returns the array of strips in the field
	 */
	public Strip[] getStrip() {
		return strips;
	}

	/*
	 * get the total sun generated
	 * @return sun points
	 */
	public int getTotalSun() {
		return this.totalSun;
	}


	/*
	 * @return returns the string representation of the field
	 */
	public String toString() {
		String s = "";
		for (int i = 0; i < Field.DEFAULT_MAX_ROW; i++) {
			s += strips[i].toString();
		}
		s += "\n";
		return s;
	}


	public void addSun(int amt) {
		this.totalSun += amt;
	}

	public void useSun(int amt) {
		System.out.println("before sun:" + getTotalSun());
		this.totalSun -= amt;
		System.out.println("after sun:" + getTotalSun());
	}

}
