/*
 * @author Tianming Zhuang
 * 100875151
 */
import java.util.Set;
import java.util.HashSet;

public abstract class Unit implements TurnBasedUnit {
	protected Square square;
	protected Set<Cooldown> cooldowns;

	protected Unit() {
		cooldowns = new HashSet<Cooldown>();
		square = null;
	}

	public Square getSquare() {
		return square;
	}

	public int getRow() {
		return square.getRow();
	}

	public int getCol() {
		return square.getCol();
	}

	public void setSquare(Square square) {
		//System.out.println("Calling setSquare");
		//System.out.println("Unit is of type: " + this.getClass().getName());
		//System.out.println("Is unit square null? " + (this.square == null));
		if (this.square != null) {
			//System.out.println("Attemping to remove; successful? " + this.square.remove(this));
			this.square.remove(this);
		}
		this.square = square;
		square.add(this);
	}

	public void tickCooldowns() {
		for (Cooldown cooldown : cooldowns) {
			cooldown.tick();
		}
	}

	@Override
	public String toString() {
		String s = "";
		s += this.getClass().getName();
		s += "@(" + getRow() + "," + getCol() + ")";
		return s;
	}
}
