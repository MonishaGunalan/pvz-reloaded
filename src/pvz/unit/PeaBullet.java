package pvz.unit;


/**
 * Concrete class of {@link Bullet} which does a specific
 * amount of damage and causes no secondary effects on the 
 * {@link Zombie} it hits.
 *
 * The speed at which the bullet moves is dicated by
 * {@link MOVE_TRIGGER} and how much damage each bullet does
 * is set in {@link DMG}.
 *
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
