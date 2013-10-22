public abstract class OffensivePlant
	extends Plant {
	// Constants
	protected static final Bullet.Type DEFAULT_BULLET = Bullet.Type.PEA; 
	protected static final int DEFAULT_SHOOT_TRIGGER = 0;

	// Variables
	protected Cooldown shootCD;
	protected BulletFactory bulletFactory;

	protected final Bullet.Type bulletType;
	protected final int shootTriggerAmt;

	// Constructors
	public OffensivePlant(int maxHP, int cost) {
		this(maxHP, cost, DEFAULT_BULLET, DEFAULT_SHOOT_TRIGGER);
	}

	public OffensivePlant(int maxHP, int cost, Bullet.Type bulletType, int shootTriggerAmt) {
		super(maxHP);
		this.cost = cost;
		this.bulletType = bulletType;
		this.shootTriggerAmt = shootTriggerAmt;
		bulletFactory = new BulletFactory();
	}

	// Type of bullet
	public Bullet.Type getBulletType() {
		return bulletType;
	}

	public int getShootTriggerAmt() {
		return shootTriggerAmt;
	}
}
