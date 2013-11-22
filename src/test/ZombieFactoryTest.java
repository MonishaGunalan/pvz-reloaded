package test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pvz.NormalZombie;
import pvz.Zombie;
import pvz.ZombieFactory;
import pvz.Zombie.Type;

/**
 * 
 * @author Tianming Zhuang
 *
 */
public class ZombieFactoryTest {
	@Test
	public void testMakeZombieWithNullType(){
		assertTrue(ZombieFactory.makeZombie(null) == null);
	}
	
	@Test
	public void testMakeZombie(){
		assertTrue(ZombieFactory.makeZombie(Zombie.Type.NORMAL).getClass() == NormalZombie.class);
	}
}
