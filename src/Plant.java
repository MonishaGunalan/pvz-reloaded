/*
 * @author Tianming Zhuang
 * 100875151
 */
public abstract class Plant 
	extends PerishableUnit{

	public enum Type{SUNFLOWER, PEASHOOTER;}

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
