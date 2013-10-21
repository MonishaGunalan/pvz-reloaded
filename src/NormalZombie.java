public class NormalZombie
	extends Zombie {
	public NormalZombie() {
		this.attackCD = new Cooldown(this.getType().getAttackTriggerAmt());
		this.moveCD = new Cooldown(this.getType().getMoveTriggerAmt());
		super.maxHP = this.getType().getMaxHP();
		super.currentHP = super.maxHP;
	}

	public void makeTurnAction() {
		// If there's a plant on the square, attack it
		// otherwise, move when possible
		if (square.hasPlant()) {
			this.attack(square.getPlant());
		} else {
			this.move();
		}
	}

	public Zombie.Type getType() {
		return Zombie.Type.NORMAL;
	}

	// Moves the bullet appropriately for the turn
	// and trickers the move cooldown
	private void move() {
		if (moveCD.isAvailable()) {
			// Remove bullet from this square and add
			// it to the next square
			square.getPrevSquare().add(this);
			square.remove(this);
			// Trigger the CD
			moveCD.trigger();
		}
	}

	// Normal zombie hits a plant on current hp, reducing
	// its hp by a flat amount
	private void hit(Plant plant) {
		if (attackCD.isAvailable) {
			plant.reduceHP(Bullet.Type.NORMAL.getDmg());
			attackCD.trigger();
		}
	}

	// Tick all cooldowns
	private void tickCooldowns() {
		moveCD.tick();
		attackCD.tick()
	}
}

