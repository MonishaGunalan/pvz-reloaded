import java.util.Set;
import java.util.HashSet;
public abstract class Unit 
	implements TurnBasedUnit{
	protected Square square;
	protected Set<Cooldown> cooldowns;
	private int row;
	private int col;

	protected Unit() {
		cooldowns = new HashSet<Cooldown>();
	}
	
	public Square getSquare(){
		return square;
	}
	
	public int getRow(){
		return row;
	}
	
	public int getCol(){
		return col;
	}
	
	public void setSquare(Square square){
		this.square = square;
	}

	public void tickCooldowns() {
		for (Cooldown cooldown: cooldowns) {
			cooldown.tick();
		}
	}
}
