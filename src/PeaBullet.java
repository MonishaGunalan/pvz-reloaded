public class PeaBullet
	extends Bullet {
	// Constants
	private static final int MOVE_TRIGGER = 0;
	private static final int DMG = 1;

	public PeaBullet(Square square) {
		super(DMG, MOVE_TRIGGER);
		// Set the square on which this bullet exists
		super.setSquare(square);
		// Instantiate cooldowns
		moveCD = new Cooldown(MOVE_TRIGGER);
		// Build set of all cooldowns
		cooldowns.add(moveCD);
		// Damage bullet does
	}

	public void makeTurnAction() {
		if (square.hasZombies()) {
			super.hit(square.getFirstZombie());
		} else {
			super.move(Field.Direction.RIGHT);
			super.tickCooldowns();
		}

	}

	public Bullet.Type getType() {
		return Bullet.Type.PEA;
	}
}
