package pvz.unit;


/**
 * @author  Arzaan Irani
 * 100826631
 *
 * This class models units have a reduceable
 * amount of HP which can die after their HP
 * reaches 0.
 */
public abstract class PerishableUnit 
	extends Unit{
	/**
	 * Maximum hp
	 */
    protected int maxHP;
    /**
     * Current HP
     */
    protected int currentHP;

	/**
	 * Constructor.
	 *
	 * @param maxHP Maximum amount of HP unit should have
	 */
	protected PerishableUnit(int maxHP) {
		super();
		// Max hp must be positive
		if (maxHP < 0) {
			throw new IllegalArgumentException();
		}
		this. maxHP = maxHP;
		this.currentHP = this.maxHP;
	}

	/**
	 * Returns the unit's maximum HP
	 */
	public int getMaxHP(){
		return maxHP;
	}
	
	/**
	 * Returns the unit's current HP
	 */
	public int getCurrentHP(){
		return currentHP;
	}
	
	/**
	 * Reduces unit's current hp by amount. If the unit's current
	 * hp becomes zero or less, the unit dies.
	 *
	 * @param amount Number to reduce current HP by
	 */
	public void reduceHP(int amount){
		// Amount reduced should be non-negative
		if (amount <= 0) {
			throw new IllegalArgumentException();
		}
		// Reduce current HP by amount
		this.currentHP -= amount;
		// Check if unit is still alive
		if (this.currentHP <= 0) {
			this.die();
		}
	}
	
	/**
	 * Unit dies. Clear refereces and delete it as an observer of Level.
	 */
	public void die(){
		//System.out.println(this.getClass().getName() + "@" + getRow() + "," + getCol() + " is dead.");
		setChanged();
		notifyObservers("zombie died");
		getSquare().getStrip().getField().getLevel().deleteObserver(this);
		this.getSquare().remove(this);
	}
	


}
