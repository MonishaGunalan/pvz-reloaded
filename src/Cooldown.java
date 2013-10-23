public class Cooldown {
	// Amount to trigger cd by
	private final int TRIGGER_AMOUNT;
	// Triggered cd
	private int cooldown;
	private int pauseDuration;

	public Cooldown(int triggerAmount) {
		this.TRIGGER_AMOUNT = triggerAmount;
		this.cooldown = 0;
	}

	public void trigger() {
		// Triggers with a default multiplier of 1
		this.trigger(1);
	}

	public void trigger(int multiplier) {
		this.cooldown = multiplier * (TRIGGER_AMOUNT+1) - 1;
	}

	public void tick() {
		if (isPaused()) {
			pauseDuration--;
		} else if (!isAvailable()) {
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

	public boolean isPaused() {
		return this.pauseDuration > 0;
	}

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
