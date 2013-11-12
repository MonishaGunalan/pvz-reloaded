package test;
import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import pvz.Bullet;
import pvz.Zombie;

public class CooldownTest extends TestCase{
	private Level testLevel1;
	private Field testField1;
	private Strip testStrip1;
	private Square testSquare1;
	private Zombie testZombie1;
	private Plant testPlant1;
	private Cooldown testcd;
	private String[] terrainType = { "mud", "grass", "grass", "grass", "mud" };
	private int TRIGGER_AMOUNT;
	private int testCooldown;
	private int testpauseDuration;
	private int triggerAmount;

	@Before
	public void setUp() throws Exception {
		testLevel1 = new Level(1);
		testField1 = new Field(terrainType, testLevel1);
		testStrip1 = new Strip("grass", 3, testField1);
		testSquare1 = new Square("grass", 3, 2, testStrip1);
		
	}

	@Test
	public void defaultConstructor(){
		assertTrue("Cooldown should be triggered", TRIGGER_AMOUNT == this.triggerAmount);
	}
	
	@Test
	public void testgetCooldown(){
		assertTrue("Current maxHP should be equal to the default.", testcd.getCooldown() == TRIGGER_AMOUNT);
	}

	public void testisAvailable(){
		assertTrue(testcd.getCooldown()==0);
	}
	
	public void testisPaused() {
		if (testpauseDuration > 0){
			assertTrue(testcd.isPaused());
		}
	}
	
	public void testPause(int duration) {
		assertTrue(testpauseDuration == duration);
	}
}
		
