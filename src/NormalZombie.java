/**
 * The most basic type of zombie. This class should not be instantiated
 * via its contstructor. Instead, use {@link ZombieFactory#makeZombie(Zombie.Type)}.
 *
 * @author Tianming Zhuang
 * @version 1.0
 * @since 1.7
 */
public class NormalZombie
	extends Zombie {
	// Constants
	private static final int MAX_HP = 10;

	/** 
	* Class constructor. 
	*/
	public NormalZombie() {
		super(MAX_HP);
	}

	public void makeTurnAction() {
		// If there's a plant on the square, attack it
		// otherwise, move when possible
		if (square.hasPlant()) {
			super.hit(square.getPlant());
		} else {
			super.move(Field.Direction.LEFT);
		}

		super.tickCooldowns();
	}

	public Zombie.Type getType() {
		return Zombie.Type.NORMAL;
	}

}

