public class NormalZombie
	extends Zombie {
	// Constants
	private static final int MAX_HP = 10;

	// COnstructor
	public NormalZombie() {
		super(MAX_HP);
	}

	public void makeTurnAction() {
		// If there's a plant on the square, attack it
		// otherwise, move when possible
		if (square.hasPlant()) {
			super.hit(square.getPlant());
		} else {
			super.move(Field.Direcion.LEFT);
		}

		super.tickCooldowns();
	}

	public Zombie.Type getType() {
		return Zombie.Type.NORMAL;
	}

}

