public abstract class Bullet
	extends Unit {
	// Number of turns bullet must wait after moving
	protected Cooldown moveCD;

	// Types of bullets
	public enum Type{
		PEA(1);

		// Constants
		private static final int DEFAULT_MOVE_TRIGGER = 0;
		// State variables for different types of bullets
		private final int dmg;
		private final int moveTriggerAmt;

		Type(int dmg) {
			this(dmg, DEFAULT_MOVE_TRIGGER);
		}

		Type(int dmg, int moveTriggerAmt) {
			this.dmg = dmg;
			this.moveTriggerAmt = moveTriggerAmt;
		}

		// Damage of bullet
		public int getDmg() {
			return dmg;
		}

		// Amount to trigger CD by
		public int getMoveTriggerAmt() {
			return moveTriggerAmt;
		}
	}
}
