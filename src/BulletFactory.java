import java.util.Map;

public class BulletFactory {
	private Map<Bullet.Type,Bullet> bulletTable;

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
