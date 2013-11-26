package pvz.unit;
/**
 * @author Tianming Zhuang
 * 100875151
 *
 * Sunflower plant when added to to a Square generates a certain
 * amount of sunlight points for the Field on which the Square belongs
 * to ever {@link SunflowerPlant#GEN_TRIGGER} turns. The amount generated each
 * time is specified by {@link SunflowerPlant#GEN_AMT}
 */
import java.util.Observable;

import pvz.level.Square;
public class SunflowerPlant
	extends GeneratorPlant {	
	/**
	 * Max HP of a sunflower plant
	 */
	public static final int MAX_HP = 3;
	/**
	 * Time between sun generation
	 */
	public static final int GEN_TRIGGER = 3;
	/**
	 * Amount of sun generated
	 */
	public static final int GEN_AMT = 25;

	/**
	 * Constructor. Creates a SunGenerator which produces {@link SunflowerPlant#GEN_AMT} sun
	 * for the field it is on every {@link SunflowerPlant#GEN_TRIGGER} turns.
	 *
	 * @param square Square unit is on
	 */
	public SunflowerPlant(Square square) {
		super(MAX_HP, square);
		super.sunGenerator = new SunGenerator(GEN_TRIGGER, GEN_AMT, square.getStrip().getField());
	}

	/**
	* Should be called each turn by Level. 
	* Sunflower doesn't do have any action to do each turn. (Covered by its
	* SunGenerator.)
	*
	* @param o Object being observed
	* @param arg Info about the update
	*/
	public void update(Observable o, Object arg) {
		return;
	}
}
