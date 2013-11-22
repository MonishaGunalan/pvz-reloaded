package pvz;
import java.util.Observable;

/**
 * @author Tianming Zhuang
 * 100875151
 */
public abstract class OffensivePlant
	extends Plant {
	/**
	 * Default bullet the plant shoots
	 */
	public static final Bullet.Type DEFAULT_BULLET = Bullet.Type.PEA;
	/**
	 * Default shoot trigger time
	 */
	public static final int DEFAULT_SHOOT_TRIGGER = 1;

	/**
	 *  The shoot cooldown
	 */
	protected Cooldown shootCD;

	/**
	 * The type of bullet it shoots
	 */
	protected final Bullet.Type bulletType;
	/**
	 * The time between shooting
	 */
	protected final int shootTriggerAmt;

	/**
	 * Constructor for the plant using default bullet and shoot trigger
	 */
	public OffensivePlant(int maxHP, Square square) {
		this(maxHP, DEFAULT_BULLET, DEFAULT_SHOOT_TRIGGER, square);
	}

	/**
	 * Default constructor
	 * @param maxHP
	 * @param bulletType
	 * @param shootTriggerAmt
	 * @param square
	 */
	public OffensivePlant(int maxHP, Bullet.Type bulletType, int shootTriggerAmt, Square square) {
		super(maxHP, square);
		this.bulletType = bulletType;
		this.shootTriggerAmt = shootTriggerAmt;
		shootCD = new Cooldown(DEFAULT_SHOOT_TRIGGER);
	}

	/**
	 *  Type of bullet
	 */
	public Bullet.Type getBulletType() {
		return bulletType;
	}

	/**
	 * The amount of time inbetween shooting
	 * @return
	 */
	public int getShootTriggerAmt() {
		return shootTriggerAmt;
	}

	/**
	 * Generate a bullet to shoot
	 */
	private void shoot() {
		Bullet bullet = BulletFactory.makeBullet(getBulletType());
		//Square nextSquare = square.getSquare(Field.Direction.RIGHT);
		getSquare().getStrip().getField().getLevel().addObserver(bullet);
		bullet.setSquare(getSquare());
	}

	/**
	 * Update the observer
	 */
	public void update(Observable o, Object arg) {
		if (getSquare().hasZombieAfter() && shootCD.isAvailable()) {
				shoot();
				shootCD.trigger();
		}
		super.tickCooldowns();
	}
}
