public class Plant 
	extends PerishableUnit{
	protected Cooldown attackCD;

	public enum Status {
	}

	public enum Type{
		SUNFLOWER_PLANT(50, ;

		// Constants
		private static final int DEFAULT_ATTACK_TRIGGER = 0;
		// State variables for different types of bullets
		private final int cost;
		private final int attackTriggerAmt;
		private final Bullet.Type bullet;
		private final int maxHP;

		private final int sunGenerated;

		Type(int cost, int attackTriggerAmt, Bullet.Type bullet, int maxHP) {
			this.maxHP = maxHP;
			this.dmg = dmg;
			this.attackTriggerAmt = attackTriggerAmt;
			this.sunGenerated = 0;
		}

		Type(int cost, int attackTriggerAmt, Bullet.Type bullet, int maxHP, int sunGenerated) {
			this.maxHP = maxHP;
			this.dmg = dmg;
			this.attackTriggerAmt = attackTriggerAmt;
			this.sunGenerated = 0;
		}

		// Damage of bullet
		public int getDmg() {
			return dmg;
		}

		public int getAttackTriggerAmt() {
			return attackTriggerAmt;
		}

		public int getMaxHP() {
			return maxHP;
		}

	}
}
