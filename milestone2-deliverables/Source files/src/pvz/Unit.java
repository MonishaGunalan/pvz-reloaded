package pvz;
/**
 * @author Tianming Zhuang
 * 100875151
 *
 * This class models all units in the game. All units must have a square
 * on which they reside (except for Zombie which may be instantiated 
 * without a square). Units keep a set of their cooldowns which track
 * which of their actions are available for use. Each turn, all the units
 * cooldowns should be ticked.
 */
import java.util.Set;
import java.util.HashSet;
import java.util.Observer;
import java.util.Observable;

public abstract class Unit 
	extends Observable
	implements Observer {
	/**
	 * The square the unit is located
	 */
	private Square square;
	/**
	 * The set of cooldown the unit has
	 */
	protected Set<Cooldown> cooldowns;

	/**
	 * Protected constructor
	 */
	protected Unit() {
		cooldowns = new HashSet<Cooldown>();
		square = null;
	}

	/** 
	 * Returns the current Square unit is on.
	 */
	public Square getSquare() {
		return square;
	}

	/** 
	 * Returns the row number in the Field unit is on.
	 */
	public int getRow() {
		return square.getRow();
	}

	/** 
	 * Returns the col number in the Field unit is on.
	 */
	public int getCol() {
		return square.getCol();
	}

	/** 
	 * Accessor for current Unit's Square.
	 *
	 * @param square The Square to put this unit on.
	 */
	public void setSquare(Square square) {
		//System.out.println("Calling setSquare");
		//System.out.println("Unit is of type: " + this.getClass().getName());
		//System.out.println("Is unit square null? " + (this.square == null));
		if (square == null){
			throw new NullPointerException("Square being set to is null");
		} else if (this.square != null) {
			//System.out.println("Attemping to remove; successful? " + this.square.remove(this));
			this.square.remove(this);
		}
		this.square = square;
		square.add(this);
	}

	/** 
	 * Iterates over unit's list of cooldowns and calls
	 * tick() on all of them.
	 */
	public void tickCooldowns() {
		for (Cooldown cooldown : cooldowns) {
			cooldown.tick();
		}
	}

	/** 
	 * Returns the concrete classname of current unit
	 * and its position in the field.
	 */
	@Override
		public String toString() {
			String s = "";
			s += this.getClass().getName();
			s += "@" + getRow() + "," + getCol() + "";
			return s;
		}
}
