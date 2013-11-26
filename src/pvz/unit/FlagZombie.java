package pvz.unit;

import pvz.unit.Zombie.Type;

/**
 * The Flag head type of zombie. This class should not be instantiated
 * via its contstructor. Instead, use {@link ZombieFactory#makeZombie(Zombie.Type)}.
 *
 * @author Arzaan Irani
 * @version 1.0
 * @since 1.7
 */
public class FlagZombie
	extends Zombie {
	/**
	 * Max HP of  cone head zombie
	 */
	public static final int MAX_HP = 10;

	/** 
	* Class constructor. 
	*/
	public FlagZombie() {
		super(MAX_HP);
	}

	/**
	 * Returns the type of zombie associated
	 * with this concrete class as
	 * listed in the enumeration of the
	 * Zombie class.
	 */
	public Zombie.Type getType() {
		return Zombie.Type.FLAG;
	}

}
