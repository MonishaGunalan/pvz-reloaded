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
		if (this.square != null) {
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
}
