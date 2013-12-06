package pvz.unit;

import pvz.level.Square;

/**
 * Abstract super class for plants that generate sun for the
 * {@link Field} they are on. 
 *
 * Concrete classes of {@link Plant} should be instantiated
 * using {@link PlantFactory#makePlant}.
 *
 * @author Tianming Zhuang
 * 100875151
 */
public abstract class GeneratorPlant
	extends Plant {
	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The generator of the plant
	 */
	protected SunGenerator sunGenerator;

	/**
	 * protected constructor
	 * @param maxHP
	 * @param square
	 */
	protected GeneratorPlant(int maxHP, Square square) {
		super(maxHP, square);
	}
}

