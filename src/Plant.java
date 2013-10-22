public abstract class Plant 
	extends PerishableUnit{

	protected final int cost;

	public enum Type{SUNFLOWER_PLANT;}

	public Plant(int maxHP, int cost) {
		super(maxHP);
		this.cost = cost;
	}
}
