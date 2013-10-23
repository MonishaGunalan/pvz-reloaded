import java.util.Map;

public class BulletFactory {
	private Map<Bullet.Type,Bullet> bulletTable;

	public BulletFactory() {
		bulletTable = new HashMap<Bullet>();
		
		bulletTable.put(Bullet.Type.PEA, PeaBullet.class);
	}

	public makeBullet(Bullet.Type bulletType) {
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
