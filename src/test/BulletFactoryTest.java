package test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pvz.unit.Bullet;
import pvz.unit.BulletFactory;
import pvz.unit.PeaBullet;
import pvz.unit.Bullet.Type;

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
