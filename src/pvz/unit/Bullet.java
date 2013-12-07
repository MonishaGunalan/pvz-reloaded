package pvz.unit;
import java.util.Observable;

import pvz.level.Field;
import pvz.level.Square;
import pvz.level.Field.Direction;

/**
 * This is an abstract superclass for all bullets
 * in the game. It keeps track of when a bullet can move,
 * and how much damage it does to a {@link Zombie} it hits.
 *
 * All bullets observe the level it is on, and does an action
 * when level increments turn.
 *
 * @author Tianming Zhuang
 * 100875151
 */
public abstract class Bullet
extends Unit {
	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Number of turns bullet must wait after moving
	 */
	protected Cooldown moveCD;
	/**
	 * Dmg bullet does to a zombie
	 */
	protected final int dmg; 
	/**
	 * Number of turns bullet has to wait before moving again
	 */
	protected final int moveTriggerAmt;

	/**
	 * protected constructor, sets the damage and move trigger amount
	 * @param dmg
	 * @param moveTriggerAmt
	 */
	protected Bullet(int dmg, int moveTriggerAmt) {
		super();
		this.dmg = dmg;
		this.moveTriggerAmt = moveTriggerAmt;
		this.moveCD = new Cooldown(getMoveTriggerAmt());
		this.moveCD.trigger();
		super.cooldowns.add(this.moveCD);
	}

	/**
	 *  Types of bullets
	 * 
	 *
	 */
	public enum Type{PEA;}

	/**
	 *  Pea bullet hits a single zombie and reduces
	 *  its hp by a flat amount, after which the bullet
	 *  is removed from the current square.
	 * @param zombie
	 */
	protected void hit(Zombie zombie) {
		zombie.reduceHP(getDmg());
		//System.out.println("Attemption to remove bullet; successful? " + square.remove(this));
		getSquare().getStrip().getField().getLevel().deleteObserver(this);
		getSquare().remove(this);
	}

	/**
	 *  Damage of bullet
	 * @return Damage of bullet
	 */
	public int getDmg() {
		return dmg;
	}

	/**
	 * Amount to trigger moveCD by each time
	 * bullet moves
	 * @return
	 */
	public int getMoveTriggerAmt() {
		return moveTriggerAmt;
	}

	/**
	 *  Moves the bullet appropriately for the turn
	 *  and trickers the move cooldown
	 * @param dir the direction moving
	 */
	protected void move(Field.Direction dir) {
		if (moveCD.isAvailable()) {
			Square dest = getSquare().getSquare(dir);
			if (dest != null) {
				// Remove bullet from this square and add
				// it to the next square
				this.setSquare(dest);
				// Trigger the CD
				moveCD.trigger();
			} else {
				getSquare().remove(this);
			}
		}
	}

	/**
	 * For debugging uses
	 * @param dest
	 */
	private void printMove(Square dest) {
		System.out.println("Bullet (" + getRow() + "," + getCol() + ")-->{" + dest.getRow() + "," + dest.getCol() + ")");
	}

	/**
	 * Update the observer
	 */
	public void update(Observable o, Object arg) {
		//System.out.println(this + " calling square.getSquare(right);");
		Square dest = getSquare().getSquare(Field.Direction.RIGHT);
		if (getSquare().hasZombie()) {
			this.hit(getSquare().getFirstZombie());
		} else if (dest != null && dest.hasZombie()) {
			this.hit(dest.getFirstZombie());
		} else if (moveCD.isAvailable()) {
			this.move(Field.Direction.RIGHT);
		}
		super.tickCooldowns();
	}

	/**
	 * The implementing classes should know what type of bullet it is
	 * @return
	 */
	public abstract Bullet.Type getType();
}
