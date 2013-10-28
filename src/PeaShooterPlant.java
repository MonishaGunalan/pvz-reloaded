/*
 * @author Tianming Zhuang
 * 100875151
 */
public class PeaShooterPlant
	extends OffensivePlant {
	private static final int MAX_HP = 3;
	private static final int COST = 100;

	public PeaShooterPlant() {
		super(MAX_HP, COST);
		cooldowns.add(shootCD);
	}

}
