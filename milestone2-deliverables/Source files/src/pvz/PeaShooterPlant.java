package pvz;
/**
 * @author Tianming Zhuang
 * 100875151
 */
public class PeaShooterPlant
	extends OffensivePlant {
	/**
	 * The mas HP of the plant
	 */
	public static final int MAX_HP = 3;
	/**
	 * The cooldown time between trigger
	 */
	public static final int PLANT_TRIGGER = 3;

	/**
	 * Public constructor of square
	 * @param square
	 */
	public PeaShooterPlant(Square square) {
		super(MAX_HP, square);
		cooldowns.add(shootCD);
	}
}
