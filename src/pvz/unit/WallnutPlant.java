package pvz.unit;

import pvz.level.Square;

/**
 * This class does nothing other than stop
 * zombies from moving forward until
 * its HP is depleted.
 *
 * @author Arzaan Irani
 * 100826631
 */
public class WallnutPlant
	extends DefensivePlant {
	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The max HP of the plant
	 */
	public static final int MAX_HP = 25;


	/**
	 * Public constructor of square
	 * @param square
	 */
	public WallnutPlant(Square square) {
		super(MAX_HP, square);
	}
}
