package pvz;

/**
 * The Cone head type of zombie. This class should not be instantiated
 * via its contstructor. Instead, use {@link ZombieFactory#makeZombie(Zombie.Type)}.
 *
 * @author Arzaan Irani
 * @version 1.0
 * @since 1.7
 */
public class ConeZombie
	extends Zombie {
	/**
	 * Max HP of  cone head zombie
	 */
	public static final int MAX_HP = 12;

	/** 
	* Class constructor. 
	*/
	public ConeZombie() {
		super(MAX_HP);
	}

	/**
	 * Returns the type of zombie associated
	 * with this concrete class as
	 * listed in the enumeration of the
	 * Zombie class.
	 */
	public Zombie.Type getType() {
		return Zombie.Type.CONE;
	}

}
