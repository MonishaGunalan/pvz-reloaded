/*
 * @author Tianming Zhuang
 * 100875151
 */

import java.util.Observer;
import java.util.Observable;

public class SunGenerator 
	implements Observer {
	private final int GEN_TRIGGER;
	private final int GEN_AMT;

	private Field field;
	private Cooldown sunCooldown;

	public SunGenerator(int trigger, int genAmt, Field field) {
		GEN_TRIGGER = trigger;
		GEN_AMT = genAmt;
		sunCooldown = new Cooldown(GEN_TRIGGER);
		this.field = field;

		// Register to level so generator knows
		// when to produce sun
		this.field.getLevel().addObserver(this);
	}

	public void generateSun() {
		if (sunCooldown.isAvailable()) {
			field.addSun(GEN_AMT);
			sunCooldown.trigger();
		} else {
			sunCooldown.tick();
		}
	}

	public void update(Observable o, Object arg) {
		generateSun();
	}
}
