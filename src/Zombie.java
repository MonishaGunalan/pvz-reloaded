import java.util.Observable;

/**
 * This class is the parent of all zombie types.<!-- --> It contains default information
 * shared by several of its subclasses.<!-- -->
 *
 * @author Tianming Zhuang
 * @version 1.0
 * @since 1.7
 */
public abstract class Zombie 
	extends PerishableUnit {

	// Constants
	/**
	 * this is a test
	 */
	protected static final int DEFAULT_ATK = 10; 
	protected static final int DEFAULT_ATTACK_TRIGGER = 0;
	protected static final int DEFAULT_MOVE_TRIGGER = 3;

	private static final Field.Direction DEFAULT_MOVE_DIR = Field.Direction.LEFT;

	// State variables for different types of zombies
	protected Cooldown moveCD;
	protected Cooldown attackCD;
	protected int attackTriggerAmt; 
	protected int moveTriggerAmt;
	protected int dmg;


	public enum Status {CHILLED, FROZEN, NORMAL}

	public enum Type{ NORMAL; }

	protected Zombie(int maxHP) {
		this(maxHP, DEFAULT_ATK, DEFAULT_ATTACK_TRIGGER, DEFAULT_MOVE_TRIGGER);
		moveCD.trigger();
	}

	protected Zombie(int maxHP, int dmg, int attackTriggerAmt, int moveTriggerAmt) {
		super(maxHP);

		this.dmg = dmg;
		this.attackTriggerAmt = attackTriggerAmt;
		this.moveTriggerAmt = moveTriggerAmt;

		this.attackCD = new Cooldown(attackTriggerAmt);
		this.moveCD = new Cooldown(moveTriggerAmt);
		cooldowns.add(attackCD);
		cooldowns.add(moveCD);
	}

	// Damage of bullet
	public int getDmg() {
		return dmg;
	}

	// Amount to trigger CD by
	public int getMoveTriggerAmt() {
		return moveTriggerAmt;
	}
	public int getAttackTriggerAmt() {
		return attackTriggerAmt;
	}

	public Cooldown getMoveCooldown() {
		return this.moveCD;
	}

	protected void move() {
		move(DEFAULT_MOVE_DIR);
	}

	/**
	 * Moves the bullet 1 square in the specified direction
	 *
	 * @param dir The direction to move in
	 */
	protected void move(Field.Direction dir) {
		if (moveCD.isAvailable()) {
			//System.out.println("row: " + square.getRow() + " col: " + square.getCol());
			// Remove bullet from this square and add
			// it to the next square
			Square dest = square.getSquare(Field.Direction.LEFT);
			this.setSquare(dest);
			// Trigger the CD
			moveCD.trigger();
		}
	}

	private void printMove(Square dest) {
		System.out.println("Zombie (" + getRow() + "," + getCol() + ")-->{" + dest.getRow() + "," + dest.getCol() + ")");
	}

	// Normal zombie hits a plant on current hp, reducing
	// its hp by a flat amount
	public void hit(Plant plant) {
		if (attackCD.isAvailable()) {
			plant.reduceHP(getDmg());
			attackCD.trigger();
		}
	}

	public void update(Observable o, Object arg) {
		// If there's a plant on the square, attack it
		// otherwise, move when possible
		if (square.hasPlant()) {
			this.hit(square.getPlant());
		} else {
			this.move(Field.Direction.LEFT);
		}
		super.tickCooldowns();
	}


	// Abstract classes
	public abstract Zombie.Type getType();
	
}
