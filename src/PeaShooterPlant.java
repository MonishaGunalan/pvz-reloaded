public class PeaShooterPlant
	extends OffensivePlant {
	private static final int MAX_HP = 3;
	private static final int COST = 100;

	public PeaShooterPlant() {
		super(MAX_HP, COST);
		cooldowns.add(shootCD);
	}

	private void shoot() {
		Bullet bullet = BulletFactory.makeBullet(getBulletType());
		Square nextSquare = square.getSquare(Field.Direction.RIGHT);
		bullet.setSquare(nextSquare);
	}

	public void makeTurnAction() {
		if (square.hasZombieAfter() && shootCD.isAvailable()) {
				shoot();
				shootCD.trigger();
		}
		super.tickCooldowns();
	}
}
