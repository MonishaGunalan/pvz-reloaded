package test;
import static org.junit.Assert.*;
import junit.framework.TestCase;
import pvz.*;
import org.junit.Before;
import org.junit.Test;

import pvz.level.Field;
import pvz.level.Level;
import pvz.level.Square;
import pvz.level.Strip;
import pvz.unit.Cooldown;
import pvz.unit.Plant;
import pvz.unit.Zombie;

public class CooldownTest {
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
		testSquare1 = new Square( 3, 2, testStrip1);
		
	}

	@Test
	public void defaultConstructor(){
		assertTrue("Cooldown should be triggered", TRIGGER_AMOUNT == this.triggerAmount);
	}
	
	
	@Test
	public void testisPaused() {
		if (testpauseDuration > 0){
			assertTrue(testcd.isPaused());
		}
	}

}
		
