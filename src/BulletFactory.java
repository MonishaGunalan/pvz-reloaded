import java.util.Map;
import java.util.HashMap;

public class BulletFactory {
	private Map<Bullet.Type, Class<? extends Bullet>> bulletTable;
	
	public BulletFactory() {
		bulletTable = new HashMap<Bullet.Type, Class<? extends Bullet>>();

		bulletTable.put(Bullet.Type.PEA, PeaBullet.class);
	}

	public Bullet makeBullet(Bullet.Type bulletType) {
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
