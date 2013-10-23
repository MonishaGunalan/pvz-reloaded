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

	public static Bullet makeBullet(Bullet.Type bulletType) {
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
