import java.util.Map;

public class BulletFactory {
	private Map<Bullet.Type,Bullet> bulletTable;

	public BulletFactory() {
		bulletTable = new HashMap<Bullet>();

	}
}
