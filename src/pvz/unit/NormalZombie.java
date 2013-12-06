package pvz.unit;



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
	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Max HP of zombie
	 */
	public static final int MAX_HP = 10;

	/** 
	* Class constructor. 
	*/
	public NormalZombie() {
		super(MAX_HP);
	}

	/**
	 * Returns the type of zombie associated
	 * with this concrete class as
	 * listed in the enumeration of the
	 * Zombie class.
	 */
	public Zombie.Type getType() {
		return Zombie.Type.NORMAL;
	}

}

