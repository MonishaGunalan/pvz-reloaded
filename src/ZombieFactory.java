import java.util.Map;
import java.util.HashMap;

public class ZombieFactory {
	private Map<Zombie.Type, Class<? extends Zombie>> zombieTable;
	
	public ZombieFactory() {
		zombieTable = new HashMap<Zombie.Type, Class<? extends Zombie>>();

		zombieTable.put(Zombie.Type.NORMAL, NormalZombie.class);
	}

	public Zombie makeZombie(Zombie.Type zombieType) {
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
