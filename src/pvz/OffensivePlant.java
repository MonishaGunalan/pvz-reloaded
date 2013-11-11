package pvz;
import java.util.Observable;

/*
 * @author Tianming Zhuang
 * 100875151
 */
public abstract class OffensivePlant
	extends Plant {
	// Constants
	public static final Bullet.Type DEFAULT_BULLET = Bullet.Type.PEA; 
	public static final int DEFAULT_SHOOT_TRIGGER = 1;

	// Variables
	protected Cooldown shootCD;

	protected final Bullet.Type bulletType;
	protected final int shootTriggerAmt;

	// Constructors
	public OffensivePlant(int maxHP, Square square) {
		this(maxHP, DEFAULT_BULLET, DEFAULT_SHOOT_TRIGGER, square);
	}

	public OffensivePlant(int maxHP, Bullet.Type bulletType, int shootTriggerAmt, Square square) {
		super(maxHP, square);
		this.bulletType = bulletType;
		this.shootTriggerAmt = shootTriggerAmt;
		shootCD = new Cooldown(DEFAULT_SHOOT_TRIGGER);
	}

	// Type of bullet
	public Bullet.Type getBulletType() {
		return bulletType;
	}

	public int getShootTriggerAmt() {
		return shootTriggerAmt;
	}

	private void shoot() {
		Bullet bullet = BulletFactory.makeBullet(getBulletType());
		//Square nextSquare = square.getSquare(Field.Direction.RIGHT);
		getSquare().getStrip().getField().getLevel().addObserver(bullet);
		bullet.setSquare(getSquare());
	}

	public void update(Observable o, Object arg) {
		if (getSquare().hasZombieAfter() && shootCD.isAvailable()) {
				shoot();
				shootCD.trigger();
		}
		super.tickCooldowns();
	}
}
