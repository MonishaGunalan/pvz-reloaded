public class Cooldown {
	// Amount to trigger cd by
	private final int TRIGGER_AMOUNT;
	// Triggered cd
	private int cooldown;

	public Cooldown(int triggerAmount) {
		this.TRIGGER_AMOUNT = triggerAmount;
		this.cooldown = 0;
	}

	public void trigger() {
		// Triggers with a default multiplier of 1
		this.trigger(1);
	}

	public void trigger(int multiplier) {
		this.cooldown *= multiplier * TRIGGER_AMOUNT;
	}

	public void tick() {
		if (this.cooldown > 0) {
			this.cooldown--;
		}
	}

	public void reset() {
		this.cooldown = 0;
	}

	public int getCooldown() {
		return this.cooldown;
	}

	public boolean isAvailable() {
		return this.cooldown == 0;
	}
}
