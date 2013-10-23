public abstract class Zombie 
	extends PerishableUnit
	implements MoveableUnit{

	// Constants
	protected static final int DEFAULT_ATK = 10;
	protected static final int DEFAULT_ATTACK_TRIGGER = 0;
	protected static final int DEFAULT_MOVE_TRIGGER = 3;

	// State variables for different types of zombies
	protected Cooldown moveCD;
	protected Cooldown attackCD;
	protected int attackTriggerAmt; 
	protected int moveTriggerAmt;
	protected int dmg;

	public enum Status {CHILLED, FROZEN, NORMAL}

	public enum Type{ NORMAL;}

	protected Zombie(int maxHP) {
		this(maxHP, DEFAULT_ATK, DEFAULT_ATTACK_TRIGGER, DEFAULT_MOVE_TRIGGER);
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

	// Moves the bullet appropriately for the turn
	// and trickers the move cooldown
	public void move(Field.Direction dir) {
		if (moveCD.isAvailable()) {
			// Remove bullet from this square and add
			// it to the next square
			square.getSquare(Field.Direction.LEFT).add(this);
			square.remove(this);
			// Trigger the CD
			moveCD.trigger();
		}
	}

	// Normal zombie hits a plant on current hp, reducing
	// its hp by a flat amount
	public void hit(Plant plant) {
		if (attackCD.isAvailable()) {
			plant.reduceHP(getDmg());
			attackCD.trigger();
		}
	}

	// Abstract classes
	public abstract Zombie.Type getType();
	
}
