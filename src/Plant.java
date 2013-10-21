public abstract class Plant 
	extends PerishableUnit{
	protected Cooldown attackCD;

	public enum Status {
	}

	public enum Type{
		SUNFLOWER_PLANT(50, 3);

		// Constants
		private static final int DEFAULT_ATTACK_TRIGGER = 0;
		// State variables for different types of bullets
		private final int cost;
		private final int attackTriggerAmt;
		private final Bullet.Type bulletType;
		private final int maxHP;

		private final int sunGenerated;

		// Constructor for defensive plants
		Type(int cost, int maxHP) {
			this(cost, maxHP, 0);
		}

		// Constructor for generator plants
		Type(int cost, int maxHP, int sunGenerated) {
			this(cost, maxHP, -1, null);
		}

		// Constructor for offensive plants
		Type(int cost, int maxHP, int attackTriggerAmt, Bullet.Type bulletType) {
			this.maxHP = maxHP;
			this.bulletType = bulletType;
			this.attackTriggerAmt = attackTriggerAmt;
			this.sunGenerated = 0;
		}


		// Type of bullet
		public Bullet.Type getBulletType() {
			return bulletType;
		}

		public int getAttackTriggerAmt() {
			return attackTriggerAmt;
		}

		public int getMaxHP() {
			return maxHP;
		}

	}
}
