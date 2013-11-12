package pvz;
import java.util.Observable;

/**
 * @author Tianming Zhuang
 * 100875151
 */
public abstract class GeneratorPlant
	extends Plant {
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

