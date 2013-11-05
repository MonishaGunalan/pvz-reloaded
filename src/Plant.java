/*
 * @author Tianming Zhuang
 * 100875151
 */
public abstract class Plant 
	extends PerishableUnit{

	public enum Type{SUNFLOWER, PEASHOOTER;}

	public Plant(int maxHP, Square square) {
		super(maxHP);
		this.square = square;
	}
}
