package pvz.unit;


/**
 * @author Tianming Zhuang
 * 100875151
 */
public class PeaBullet
	extends Bullet {
	// Constants
	/**
	 * The trigger time for move
	 */
	private static final int MOVE_TRIGGER = 0;
	/**
	 * The damage that the bullet does
	 */
	private static final int DMG = 1;

	/**
	 * Constructor for the peabullet
	 */
	public PeaBullet() {
		super(DMG, MOVE_TRIGGER);
		// Instantiate cooldowns
		moveCD = new Cooldown(MOVE_TRIGGER);
		moveCD.trigger();
		// Build set of all cooldowns
		cooldowns.add(moveCD);
		// Damage bullet does
	}

	/**
	 * Return the type of the bullet
	 */
	public Bullet.Type getType() {
		return Bullet.Type.PEA;
	}
}
