package pvz;
/**
 * @author Tianming Zhuang
 * 100875151
 */
public class Cooldown {
	/**
	 *  Amount to trigger cd by
	 */
	private final int TRIGGER_AMOUNT;
	/**
	 *  Triggered cd
	 */
	private int cooldown;
	/**
	 * duration where nothing happens in the cooldown
	 */
	private int pauseDuration;

	/**
	 * Sets the amount of turns it must wait before triggering
	 * @param triggerAmount
	 */
	public Cooldown(int triggerAmount) {
		this.TRIGGER_AMOUNT = triggerAmount;
		this.cooldown = 0;
	}

	/**
	 * Trigger the cooldown
	 */
	public void trigger() {
		// Triggers with a default multiplier of 1
		this.trigger(1);
	}

	/**
	 * Trigger the cooldown using a multipler to lengthen the duration
	 * @param multiplier
	 */
	public void trigger(int multiplier) {
		this.cooldown = multiplier * (TRIGGER_AMOUNT+1) - 1;
	}

	/**
	 * countdown the cooldown
	 */
	public void tick() {
		if (isPaused()) {
			pauseDuration--;
		} else if (!isAvailable()) {
			this.cooldown--;
		}
	}

	/**
	 * Resets the cooldown back to 0
	 */
	public void reset() {
		this.cooldown = 0;
	}

	/**
	 * The integer format of the cooldown
	 * @return
	 */
	public int getCooldown() {
		return this.cooldown;
	}

	/**
	 * check if the cooldown is off cooldown
	 * @return
	 */
	public boolean isAvailable() {
		return this.cooldown == 0;
	}

	/**
	 * check if the cooldown is paused
	 * @return
	 */
	public boolean isPaused() {
		return this.pauseDuration > 0;
	}

	/**
	 * Pause the cooldown for a duration of turns
	 * @param duration
	 */
	public void pause(int duration) {
		this.pauseDuration = duration;
	}

	@Override
	public String toString() { 
		String s = "";
		s += cooldown;
		return s;
	}
}
