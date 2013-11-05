/*
 * @author Tianming Zhuang
 * 100875151
 */
public class PeaShooterPlant
	extends OffensivePlant {
	private static final int MAX_HP = 3;
	private static final int PLANT_TRIGGER = 3;

	public PeaShooterPlant(Square square) {
		super(MAX_HP, square);
		cooldowns.add(shootCD);
	}

	protected static int getPlantTrigger() {
		return PLANT_TRIGGER;
	}
}
