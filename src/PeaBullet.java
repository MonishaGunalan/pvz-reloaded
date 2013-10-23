public class PeaBullet
	extends Bullet {
	// Constants
	private static final int MOVE_TRIGGER = 1;
	private static final int DMG = 1;

	public PeaBullet() {
		super(DMG, MOVE_TRIGGER);
		// Instantiate cooldowns
//		moveCD = new Cooldown(MOVE_TRIGGER);
//		moveCD.trigger();
//		// Build set of all cooldowns
//		cooldowns.add(moveCD);
		// Damage bullet does
	}

	public void makeTurnAction() {
		if (square.hasZombie()) {
			System.out.println("HASS A FUCKING ZOMBIE");
			super.hit(square.getFirstZombie());
		} else if (moveCD.isAvailable()) {
			System.out.println("Is move cd avail? " + moveCD.isAvailable());
			super.move(Field.Direction.RIGHT);
			moveCD.trigger();
		}
		super.tickCooldowns();
	}

	public Bullet.Type getType() {
		return Bullet.Type.PEA;
	}
}
