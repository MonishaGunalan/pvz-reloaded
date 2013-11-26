package pvz.unit;

import pvz.level.Square;

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
	 * Public constructor of square
	 * @param square
	 */
	public WallnutPlant(Square square) {
		super(MAX_HP, square);
	}
}
