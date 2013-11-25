package pvz;
/**
 * @author Arzaan Irani
 * 100826631
 */
public class WallnutPlant
	extends DefensivePlant {
	/**
	 * The max HP of the plant
	 */
	public static final int MAX_HP = 70;
	/**
	 * The cooldown time between trigger
	 */
	public static final int PLANT_TRIGGER = 3;

	/**
	 * Public constructor of square
	 * @param square
	 */
	public WallnutPlant(Square square) {
		super(MAX_HP, square);
	}
}
