/*
 * @author Tianming Zhuang
 * 100875151
 */
public class PeaBullet
	extends Bullet {
	// Constants
	private static final int MOVE_TRIGGER = 2;
	private static final int DMG = 1;

	public PeaBullet() {
		super(DMG, MOVE_TRIGGER);
		// Instantiate cooldowns
		moveCD = new Cooldown(MOVE_TRIGGER);
		moveCD.trigger();
		// Build set of all cooldowns
		cooldowns.add(moveCD);
		// Damage bullet does
	}

	public Bullet.Type getType() {
		return Bullet.Type.PEA;
	}
}
