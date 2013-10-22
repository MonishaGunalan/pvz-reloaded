public abstract class Bullet
	extends Unit 
	implements MoveableUnit{
	protected Cooldown moveCD; // Number of turns bullet must wait after moving
	protected final int dmg; // Dmg bullet does to a zombie
	protected final int moveTriggerAmt; // Number of turns bullet has to wait before moving again

	protected Bullet(int dmg, int moveTriggerAmt) {
		super();
		this.dmg = dmg;
		this.moveTriggerAmt = moveTriggerAmt;
		this.moveCD = new Cooldown(getMoveTriggerAmt());
		super.cooldowns.add(this.moveCD);
	}

	// Types of bullets
	public enum Type{PEA;}

	// Pea bullet hits a single zombie and reduces
	// its hp by a flat amount, after which the bullet
	// is removed from the current square.
	protected void hit(Zombie zombie) {
		zombie.reduceHP(getDmg());
		square.remove(this);
	}

	// Damage of bullet
	public int getDmg() {
		return dmg;
	}

	// Amount to trigger moveCD by each time
	// bullet moves
	public int getMoveTriggerAmt() {
		return moveTriggerAmt;
	}

	// Moves the bullet appropriately for the turn
	// and trickers the move cooldown
	public void move(Field.Direction dir) {
		if (moveCD.isAvailable()) {
			if (square.getSquare(dir) != null) {
				// Remove bullet from this square and add
				// it to the next square
				square.getSquare(dir).add(this);
				square.remove(this);
				// Trigger the CD
				moveCD.trigger();
			} else {
				square.remove(this);
			}
		}
	}

	public abstract Bullet.Type getType();
}
