package pvz.unit;

import pvz.level.Square;

/**
 * This class contains the data for making
 * a standard peashooter class. HP can is controlled
 * by {@link MAX_HP}. Attack speed is dictated by
 * {@link OffensivePlant#DEFAULT_SHOOT_TRIGGER}.
 *
 * @author Tianming Zhuang
 * 100875151
 */
public class PeaShooterPlant
	extends OffensivePlant {
	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The mas HP of the plant
	 */
	public static final int MAX_HP = 3;

	/**
	 * Public constructor of square
	 * @param square
	 */
	public PeaShooterPlant(Square square) {
		super(MAX_HP, square);
		cooldowns.add(shootCD);
	}
}
