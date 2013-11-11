package test;
import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import pvz.Bullet;
import pvz.Zombie;


public class BulletTest extends TestCase{

	Bullet bullet;
	int dmg, moveTriggerAmt, moveCD;
	
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		dmg = 0;
		moveTriggerAmt = 0;
		moveCD = 0;
		bullet = new Bullet(dmg,moveTriggerAmt);
	}
	
	
	
	//********Constructor
	
	public void testhit(Zombie zombie) {
		
		
		
	}
	
	
	

}

