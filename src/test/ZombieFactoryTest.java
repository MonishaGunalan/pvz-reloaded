package test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pvz.unit.ConeZombie;
import pvz.unit.FlagZombie;
import pvz.unit.NormalZombie;
import pvz.unit.Zombie;
import pvz.unit.ZombieFactory;
import pvz.unit.Zombie.Type;

/**
 * 
 * @author Tianming Zhuang/ Arzaan Irani (added in new zombies)
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
	
	@Test
	public void testMakeConeZombie(){
		assertTrue(ZombieFactory.makeZombie(Zombie.Type.CONE).getClass() == ConeZombie.class);
	}
	
	@Test
	public void testMakeFlagZombie(){
		assertTrue(ZombieFactory.makeZombie(Zombie.Type.FLAG).getClass() == FlagZombie.class);
	}
}
