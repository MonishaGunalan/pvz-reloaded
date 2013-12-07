package pvz.unit;
import java.util.Observable;
import pvz.level.Field;
import pvz.level.Square;
import pvz.level.Player;

/**
 * This class is the parent of all zombie types.<!-- --> It contains default information
 * shared by several of its subclasses.<!-- -->
 *
 * @author Tianming Zhuang/Arzaan Irani
 * @version 1.0
 * @since 1.7
 */
public abstract class Zombie 
	extends PerishableUnit {
	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The default attack damage of zombie
	 */
	public static final int DEFAULT_ATK = 1;
	/**
	 * The Default attack trigger of zombie
	 */
	public static final int DEFAULT_ATTACK_TRIGGER = 2;
	/**
	 * The Default move trigger of zombie
	 */
	public static final int DEFAULT_MOVE_TRIGGER = 3;

	/**
	 * The direction the zombie can move
	 */
	private static final Field.Direction DEFAULT_MOVE_DIR = Field.Direction.LEFT;

	/**
	 * The move cooldown of zombie
	 */
	protected Cooldown moveCD;
	/**
	 * The attack cooldown of zombie
	 */
	protected Cooldown attackCD;
	/**
	 * The amount to trigger the attack cooldown by
	 */
	protected int attackTriggerAmt; 
	/**
	 * The amount to trigger the move cooldown by
	 */
	protected int moveTriggerAmt;
	/**
	 * The amount of dmg the zombie does
	 */
	protected int dmg;

	/**
	 * The status the zombie can be affected by
	 *
	 */
	public enum Status {CHILLED, FROZEN, NORMAL}

	/**
	 * The type of zombies
	 *
	 */
	public enum Type{ NORMAL, CONE, FLAG; }

	/**
	 * Protected constructor initialing to defaults values
	 * @param maxHP
	 */
	protected Zombie(int maxHP) {
		this(maxHP, DEFAULT_ATK, DEFAULT_ATTACK_TRIGGER, DEFAULT_MOVE_TRIGGER);
		moveCD.trigger();
	}

	/**
	 * Protected constructor initializing the zombie
	 * @param maxHP
	 * @param dmg
	 * @param attackTriggerAmt
	 * @param moveTriggerAmt
	 */
	protected Zombie(int maxHP, int dmg, int attackTriggerAmt, int moveTriggerAmt) {
		super(maxHP);

		this.dmg = dmg;
		this.attackTriggerAmt = attackTriggerAmt;
		this.moveTriggerAmt = moveTriggerAmt;

		this.attackCD = new Cooldown(attackTriggerAmt);
		this.moveCD = new Cooldown(moveTriggerAmt);
		cooldowns.add(attackCD);
		cooldowns.add(moveCD);

		// Add level as an observer of this zombie
		//addObserver(square.getStrip().getField().getLevel());
	}

	/**
	 * Get damage of Zombie
	 * @return The Damage
	 */
	public int getDmg() {
		return dmg;
	}

	/**
	 *  Get the amount to trigger CD by
	 * @return
	 */
	public int getMoveTriggerAmt() {
		return moveTriggerAmt;
	}
	
	/**
	 * Get the Attack Trigger Amount
	 * @return
	 */
	public int getAttackTriggerAmt() {
		return attackTriggerAmt;
	}

	/**
	 * The move
	 */
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
			Square dest = getSquare().getSquare(dir);
			// Destination is null meaning we are trying to move left off the field,
			// ie. current zombie has reached end of map
			if (dest == null) {
				// Let level know we reached the end so it can handle it
				setChanged();
				notifyObservers("zombie won");
				// Exit function without moving zombie
				return;
			}
			this.setSquare(dest);
			// Trigger the CD
			moveCD.trigger();
		}
	}

	/**
	 *  For debugging
	 * @param dest
	 */
	public void printMove(Square dest) {
		System.out.println("Zombie (" + getRow() + "," + getCol() + ")-->{" + dest.getRow() + "," + dest.getCol() + ")");
	}

	/**
	 *  Normal zombie hits a plant on current hp, reducing
	 *   its hp by a flat amount
	 * @param plant
	 */
	public void hit(Plant plant) {
		//System.out.println("calling Zombie.hit");
		if (attackCD.isAvailable()) {
			plant.reduceHP(getDmg());
			attackCD.trigger();
		}
	}

	public void update(Observable o, Object arg) {
		// If there's a plant on the square, attack it
		// otherwise, move when possible
		if (getSquare().hasPlant()) {
			//System.out.println(getSquare().getLoc());
			this.hit(getSquare().getPlant());
		} else if (!getSquare().hasBullet()) {
			this.move(Field.Direction.LEFT);
		}
		super.tickCooldowns();
	}

	@Override
	public void die() {
		setChanged();
		notifyObservers(Player.PlayStatus.ZOMBIE_DIED);
		super.die();
	}


	/**
	 *  Get the type of zombie
	 * @return
	 */
	public abstract Zombie.Type getType();
	
}
