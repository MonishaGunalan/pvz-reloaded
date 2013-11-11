import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
public class BulletFactoryTest {
	@Test
	public void testMakeBulletWithNullType(){
		assertTrue(BulletFactory.makeBullet(null) == null);
	}
	
	@Test
	public void testMakeBullet(){
		assertTrue(BulletFactory.makeBullet(Bullet.Type.PEA).getClass() == PeaBullet.class);
	}
}
