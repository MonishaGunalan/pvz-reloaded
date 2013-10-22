public class SunGenerator {
	private final int GEN_TRIGGER;
	private final int GEN_AMT;

	private int sunCooldown;

	public SunGenerator(int trigger, int genAmt) {
		GEN_TRIGGER = trigger;
		this.GEN_AMT = genAmt;
		cooldown = new Cooldown(GEN_TRIGGER);
	}

	public int generateSun() {
		if (sunCooldown.isAvailable()) {
			return GEN_AMT;
		}
	}
}
