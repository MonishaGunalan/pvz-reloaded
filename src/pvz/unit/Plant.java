package pvz.unit;

import pvz.level.Square;

/**
 * @author Tianming Zhuang / Arzaan irani (Implemented wallnut)
 * 100875151
 */
public abstract class Plant 
	extends PerishableUnit{

	/**
	 * The possible types a plant can have
	 *
	 */
	public enum Type{SUNFLOWER, PEASHOOTER, WALLNUT;}

	/**
	 * Constructor
	 *
	 * @param maxHP Maximum HP for unit
	 * @param square The square the unit is on
	 */
	protected Plant(int maxHP, Square square) {
		super(maxHP);
		this.setSquare(square);
	}
}
