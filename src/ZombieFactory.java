import java.util.Map;
import java.util.HashMap;

public class ZombieFactory {
	private Map<Zombie.Type,Zombie> zombieTable;
	
	public ZombieFactory() {
		zombieTable = new HashMap<Zombie.Type, Zombie>();

		zombieTable.put(Zombie.Type.PEA, PeaZombie.class);
	}

	public static Zombie makeZombie(Zombie.Type zombieType) {
		Zombie p = null;
		try {
			p = zombieTable.get(zombieType).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return p;
	}
}
