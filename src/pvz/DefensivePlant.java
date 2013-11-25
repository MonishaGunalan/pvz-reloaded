package pvz;
import java.util.Observable;

/**
 * @author Arzaan Irani
 * 100826631
 */
public abstract class DefensivePlant
	extends Plant {

	/**
	 *  The shoot cooldown
	 */
	protected Cooldown shootCD;


	/**
	 * Default constructor
	 * @param maxHP
	 * @param square
	 */
	public DefensivePlant(int maxHP, Square square) {
		super(maxHP, square);
	}

	/**
	 * Update the observer
	 */
	public void update(Observable o, Object arg) {
		if (getSquare().hasZombieAfter()) {
			shootCD.trigger();
		}
		super.tickCooldowns();
	}
}
