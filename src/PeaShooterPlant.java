public class PeaShooterPlant
	extends OffensivePlant {
	private static final int COST = 100;

	public PeaShooterPlant(int maxHP) {
		super(maxHP, COST);
		cooldowns.add(shootCD);
	}

	private void shoot() {
		Bullet bullet = bulletFactory.makeBullet(getBulletType());
		Square nextSquare = square.getSquare(Field.Direction.RIGHT);
		nextSquare.add(bullet);
	}

	public makeTurnAction() {
		if (square.hasZombieInStrip()) {
			shoot();
		}
		super.tickCooldowns();
	}
}