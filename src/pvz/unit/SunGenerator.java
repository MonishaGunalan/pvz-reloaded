package pvz.unit;
/**
 * Instances of this class are bound to a {@link Field}.
 * The generator itself observes a the {@link Level} it
 * is on and each time the turn increments, it produces
 * sunlight for the field if possible. There is an internal
 * cooldown tracker which keeps track of if the ability to 
 * produce sunlight is cooldowned. 
 *
 * How often sunlight is generated is controlled by
 * {@link GEN_TRIGGER} and how much is produced each time is
 * dictated by {@link GEN_AMT}.
 *
 * @author Tianming Zhuang
 * 100875151
 */

import java.util.Observer;
import java.util.Observable;
import java.io.Serializable;

import pvz.level.Field;

/**
 * @author Tianming
 *
 */
public class SunGenerator 
	implements Observer, Serializable {
	/**
	 * Serialization UID
	 * Do not change unless serialization with previous versions become
	 * incompatible
	 */
	static final long serialVersionUID = 749710305764016775L;

	private final int GEN_TRIGGER;
	private final int GEN_AMT;

	private Field field;
	private Cooldown sunCooldown;

	/**
	 * Constructor. Registers this object as an observer
	 * of Level so that it knows when level increments.
	 * 
	 * @param trigger Number of turns between producing sun
	 * @param genAmt Amount of sun produced each time
	 * @param field Field to produce sun to
	 */
	public SunGenerator(int trigger, int genAmt, Field field) {
		GEN_TRIGGER = trigger;
		GEN_AMT = genAmt;
		sunCooldown = new Cooldown(GEN_TRIGGER);
		this.field = field;

		// Register to level so generator knows
		// when to produce sun
		this.field.getLevel().addObserver(this);
	}

	/**
	 * If GEN_TRIGGER turns have passed since 
	 * last producing sunlight, adds GEN_AMT sun
	 * to the field. Otherwise, tick cooldowns.
	 */
	public void generateSun() {
		if (sunCooldown.isAvailable()) {
			field.addSun(GEN_AMT);
			sunCooldown.trigger();
		} else {
			sunCooldown.tick();
		}
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable o, Object arg) {
		generateSun();
	}
}
