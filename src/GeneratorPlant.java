import java.util.Observable;

/*
 * @author Tianming Zhuang
 * 100875151
 */
public abstract class GeneratorPlant
	extends Plant {
	protected SunGenerator sunGenerator;

	protected GeneratorPlant(int maxHP, Square square) {
		super(maxHP, square);
	}
}

