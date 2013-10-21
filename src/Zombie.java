public abstract class Zombie 
	extends PerishableUnit{
	protected Cooldown moveCD;
	protected Cooldown attackCD;

	public enum Status {
	}

	public enum Type{
		NORMAL(10);

		// Constants
		private static final int DEFAULT_ATTACK_TRIGGER = 0;
		private static final int DEFAULT_MOVE_TRIGGER = 3;
		private static final int DEFAULT_DMG = 1;
		// State variables for different types of bullets
		private final int attackTriggerAmt;
		private final int moveTriggerAmt;
		private final int dmg;
		private final int maxHP;


		Type(int maxHP) {
			this(maxHP, DEFAULT_DMG, DEFAULT_MOVE_TRIGGER, DEFAULT_ATTACK_TRIGGER);
		}

		Type(int maxHP, int dmg, int moveTriggerAmt, int attackTriggerAmt) {
			this.maxHP = maxHP;
			this.dmg = dmg;
			this.moveTriggerAmt = moveTriggerAmt;
			this.attackTriggerAmt = attackTriggerAmt;
		}

		// Damage of bullet
		public int getDmg() {
			return dmg;
		}

		// Amount to trigger CD by
		public int getMoveTriggerAmt() {
			return moveTriggerAmt;
		}
		public int getAttackTriggerAmt() {
			return attackTriggerAmt;
		}

		public int getMaxHP() {
			return maxHP;
		}

	}
}
