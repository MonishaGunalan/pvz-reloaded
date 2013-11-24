package pvz;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
/**
 * @author Tianming Zhuang/Arzaan Irani
 * 100875151/100826631
 */
public class ZombieFactory {
	
	/**
	 * Mapping of zombie type to zombie class
	 */
	private static final Map<Zombie.Type, Class<? extends Zombie>> zombieTable;
	
	static {
		Map<Zombie.Type, Class<? extends Zombie>> aTable = new HashMap<Zombie.Type, Class<? extends Zombie>>();

		aTable.put(Zombie.Type.NORMAL, NormalZombie.class);
		aTable.put(Zombie.Type.FLAG, FlagZombie.class);
		aTable.put(Zombie.Type.CONE, ConeZombie.class);

		zombieTable = Collections.unmodifiableMap(aTable);
	}

	/**
	 * Creates a new instance of Zombie of specified type.
	 * 
	 * @param zombieType Type of zombie to create
	 * @return New instance of specified zombie type
	 */
	public static Zombie makeZombie(Zombie.Type zombieType) {
		if (zombieType == null) {
			return null;
		}

		Zombie z = null;
		try {
			z = zombieTable.get(zombieType).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return z;
	}
}
