/*
 * @author Tianming Zhuang
 * 100875151
 */
public class SunflowerPlant
	extends GeneratorPlant {
	private static final int MAX_HP = 3;
	private static final int GEN_TRIGGER = 3;
	private static final int GEN_AMT = 25;
	private static final int COST = 50;

	public SunflowerPlant() {
		super(MAX_HP, COST);
		super.sunGenerator = new SunGenerator(GEN_TRIGGER, GEN_AMT);
	}

	public void makeTurnAction() {}

}

