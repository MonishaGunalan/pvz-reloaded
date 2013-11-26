package test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pvz.unit.NormalZombie;
import pvz.unit.Zombie;
import pvz.unit.ZombieFactory;
import pvz.unit.Zombie.Type;

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
