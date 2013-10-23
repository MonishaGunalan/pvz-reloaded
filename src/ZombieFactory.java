/*
 * @author Tianming Zhuang
 * 100875151
 */
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

public class ZombieFactory {
	private static final Map<Zombie.Type, Class<? extends Zombie>> zombieTable;
	
	static {
		Map<Zombie.Type, Class<? extends Zombie>> aTable = new HashMap<Zombie.Type, Class<? extends Zombie>>();

		aTable.put(Zombie.Type.NORMAL, NormalZombie.class);

		zombieTable = Collections.unmodifiableMap(aTable);
	}

	public static Zombie makeZombie(Zombie.Type zombieType) {
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
