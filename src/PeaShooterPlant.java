/*
 * @author Tianming Zhuang
 * 100875151
 */
public class PeaShooterPlant
	extends OffensivePlant {
	public static final int MAX_HP = 3;
	public static final int PLANT_TRIGGER = 3;

	public PeaShooterPlant(Square square) {
		super(MAX_HP, square);
		cooldowns.add(shootCD);
	}
}
