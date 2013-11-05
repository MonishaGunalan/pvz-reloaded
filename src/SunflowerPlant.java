/*
 * @author Tianming Zhuang
 * 100875151
 */
import java.util.Observable;
public class SunflowerPlant
	extends GeneratorPlant {
	private static final int MAX_HP = 3;
	private static final int GEN_TRIGGER = 3;
	private static final int GEN_AMT = 25;

	public SunflowerPlant(Square square) {
		super(MAX_HP, square);
		super.sunGenerator = new SunGenerator(GEN_TRIGGER, GEN_AMT, square.getStrip().getField());
	}

	public void update(Observable o, Object arg) {
		return;
	}
}
