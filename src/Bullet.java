/*
 * @author Tianming Zhuang
 * 100875151
 */
public abstract class Bullet
	extends Unit {
	protected Cooldown moveCD; // Number of turns bullet must wait after moving
	protected final int dmg; // Dmg bullet does to a zombie
	protected final int moveTriggerAmt; // Number of turns bullet has to wait before moving again

	protected Bullet(int dmg, int moveTriggerAmt) {
		super();
		this.dmg = dmg;
		this.moveTriggerAmt = moveTriggerAmt;
		this.moveCD = new Cooldown(getMoveTriggerAmt());
		this.moveCD.trigger();
		super.cooldowns.add(this.moveCD);
	}

	// Types of bullets
	public enum Type{PEA;}

	// Pea bullet hits a single zombie and reduces
	// its hp by a flat amount, after which the bullet
	// is removed from the current square.
	protected void hit(Zombie zombie) {
		zombie.reduceHP(getDmg());
		//System.out.println("Attemption to remove bullet; successful? " + square.remove(this));
		//square.remove(this);
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
	protected void move(Field.Direction dir) {
		if (moveCD.isAvailable()) {
			Square dest = square.getSquare(dir);
			printMove(dest);
			if (dest != null) {
				// Remove bullet from this square and add
				// it to the next square
				this.setSquare(dest);
				// Trigger the CD
				moveCD.trigger();
			} else {
				square.remove(this);
			}
		}
	}

	private void printMove(Square dest) {
		System.out.println("Bullet (" + getRow() + "," + getCol() + ")-->{" + dest.getRow() + "," + dest.getCol() + ")");
	}

	public void makeTurnAction() {
		System.out.println(this + " calling square.getSquare(right);");
		Square dest = square.getSquare(Field.Direction.RIGHT);
		if (dest == null) {
			System.out.println("WTF HOW IS DEST NULL?");
		}
		if (square.hasZombie()) {
			System.out.println("1");
			//System.out.println("HASS A FUCKING ZOMBIE");
			this.hit(square.getFirstZombie());
		} else if (dest.hasZombie()) {
			System.out.println("2");
			this.hit(dest.getFirstZombie());
		} else if (moveCD.isAvailable()) {
			System.out.println("3");
			//System.out.println("Is move cd avail? " + moveCD.isAvailable());
			this.move(Field.Direction.RIGHT);
		}
		super.tickCooldowns();
	}


	public abstract Bullet.Type getType();
}
