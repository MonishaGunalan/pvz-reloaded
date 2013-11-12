package test;
import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pvz.*;
import pvz.Zombie.Type;


public class BulletTest{

	private Bullet bullet;
	private Zombie zombie;
	private Strip strip;
	private Level level;
	private int dmg;
	
	@Before
	public void setUp() throws Exception {
		dmg = 1;
		bullet = new Bullet(dmg,1){
			@Override
			public Type getType() {
				// Not needed for junit testing
				return null;
			}
		};
		zombie = new Zombie(20) {
			
			@Override
			public Type getType() {
				// Not needed for junit testing
				return null;
			}
		};
		level = new Level(1);
		strip = level.getField().getStrip()[1];
		bullet.setSquare(strip.getSquare(1));

		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHitByUpdate() {
		zombie.setSquare(strip.getSquare(2));
		int startHP = zombie.getCurrentHP();
		bullet.update(null, null);
		assertTrue(zombie.getCurrentHP()+dmg == startHP);
	}

	@Test
	public void testGetDmg() {
		assertTrue(bullet.getDmg() == dmg);
	}

	@Test
	public void testGetMoveTrigger() {
		assertTrue(bullet.getMoveTriggerAmt() == 1);
	}

	@Test
	public void testMoveByUpdate() {
		int startCol = bullet.getCol();
		bullet.update(null, null);
		bullet.update(null, null);
		assertTrue(bullet.getCol() == startCol+1);
	}
	

}

