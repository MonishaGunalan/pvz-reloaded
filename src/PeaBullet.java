public class PeaBullet
	extends Bullet {

	public PeaBullet(Square square) {
		// Set the square on which this bullet exists
		super.setSquare(square);
		// Create cooldown object for this bullet with appropriate
		// trigger amount for the type
		this.moveCD = new Cooldown(this.getType().getMoveTriggerAmt());
	}

	public Bullet.Type getType() {
		return Bullet.Type.PEA;
	}

	public void makeTurnAction() {
		if (square.hasZombies()) {
			this.hit(square.getFirstZombie());
		} else {
			this.move();
			this.tickCooldowns();
		}

	}

	// Moves the bullet appropriately for the turn
	// and trickers the move cooldown
	private void move() {
		if (moveCD.isAvailable()) {
			if (square.getNextSquare() != null) {
				// Remove bullet from this square and add
				// it to the next square
				square.getNextSquare().add(this);
				square.remove(this);
				// Trigger the CD
				moveCD.trigger();
			} else {
				square.remove(this);
			}
		}
	}

	// Pea bullet hits a single zombie and reduces
	// its hp by a flat amount, after which the bullet
	// is removed from the current square.
	private void hit(Zombie zombie) {
		zombie.reduceHP(Bullet.Type.PEA.getDmg());
		square.remove(this);
	}

	// Tick all cooldowns
	private void tickCooldowns() {
		moveCD.tick();
	}
}
