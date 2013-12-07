package pvz.unit;
import java.util.Observable;

import pvz.level.Square;

/**
 * Abstract superclass for plants who's only function
 * is to abosrb damange (they cannot shoot or move).
 *
 * Concrete classes of {@link Plant} should be instantiated
 * using {@link PlantFactory#makePlant}.
 *
 * @author Arzaan Irani
 * 100826631
 */
public abstract class DefensivePlant
	extends Plant {
	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 1L;

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
		super.tickCooldowns();
	}
}
