/*
 * @author Tianming Zhuang
 * 100875151
 */
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

	public Zombie.Type getType() {
		return Zombie.Type.NORMAL;
	}

}

