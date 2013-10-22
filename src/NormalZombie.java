public class NormalZombie
	extends Zombie {
	// Constants
	private static final int DMG = 1;
	private static final int MAX_HP = 10;
	private static final int ATTACK_TRIGGER = 0;
	private static final int MOVE_TRIGGER = 3;

	// COnstructor
	public NormalZombie() {
		super(MAX_HP, DMG, ATTACK_TRIGGER, MOVE_TRIGGER);
	}

	public void makeTurnAction() {
		// If there's a plant on the square, attack it
		// otherwise, move when possible
		if (square.hasPlant()) {
			super.hit(square.getPlant());
		} else {
			super.move(Field.Direcion.LEFT);
		}
	}

	public Zombie.Type getType() {
		return Zombie.Type.NORMAL;
	}

}

