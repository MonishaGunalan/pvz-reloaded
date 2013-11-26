package pvz.unit;
/*
 * @author Tianming Zhuang
 * 100875151
 */
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

public class BulletFactory {
	private static Map<Bullet.Type, Class<? extends Bullet>> bulletTable;
	
	static {
		Map<Bullet.Type, Class<? extends Bullet>> aTable = new HashMap<Bullet.Type, Class<? extends Bullet>>();

		aTable.put(Bullet.Type.PEA, PeaBullet.class);

		bulletTable = Collections.unmodifiableMap(aTable);
	}

	/**
	 * Creates a new instance of a bullet of type 
	 * bulletType.
	 * 
	 * @param bulletType Type of bullet to create
	 * @return New instance of specified bullet
	 */
	public static Bullet makeBullet(Bullet.Type bulletType) {
		if (bulletType == null) {
			return null;
		}

		Bullet p = null;
		try {
			p = bulletTable.get(bulletType).newInstance();
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
