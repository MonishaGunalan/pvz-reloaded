package test;
/*
 * This class contains unit testing for all 
 * methods in the abstract class Zombie
 * @author Tianming Zhuang
 * 100875151

 */
import static org.junit.Assert.*;

import org.junit.*;

import pvz.level.Field;
import pvz.level.Level;
import pvz.level.Square;
import pvz.level.Strip;
import pvz.level.Field.Direction;
import pvz.unit.NormalZombie;
import pvz.unit.Plant;
import pvz.unit.PlantFactory;
import pvz.unit.Zombie;
import pvz.unit.Zombie.Type;

/**
 * 
 * @author Tianming Zhuang
 *
 */
public class ZombieTest {
	private Level testLevel1;
	private Field testField1;
	private Strip testStrip1;
	private Square testSquare1;
	private Zombie testZombie1;
	private String[] terrainType = { "mud", "grass", "grass", "grass", "mud" };

	@Before
	public void setUp() throws Exception {
		testLevel1 = new Level(1);
		testField1 = new Field(terrainType, testLevel1);
		testStrip1 = new Strip("grass", 3, testField1);
		testSquare1 = new Square(3, 2, testStrip1);
		testZombie1 = new Zombie(10){

			@Override
			public Type getType() {
				// TODO Auto-generated method stub
				return null;
			}

		};

		testZombie1.setSquare(testSquare1);
	}

	@Test
	public void getDmg(){
		assertTrue("Default dmg", 
				testZombie1.getDmg() == Zombie.DEFAULT_ATK);
	}

	@Test
	public void getMoveTriggerAmt(){
		assertTrue("Default move trigger", 
				testZombie1.getMoveTriggerAmt() == Zombie.DEFAULT_MOVE_TRIGGER);
	}

	@Test
	public void getAttackTriggerAmt(){
		assertTrue("Default attack trigger", 
				testZombie1.getAttackTriggerAmt() == Zombie.DEFAULT_ATTACK_TRIGGER);
	}


	@Test
	public void hitPlant(){
		// Make a plant
		Plant p = PlantFactory.makePlant(Plant.Type.SUNFLOWER, testSquare1);
		// Get its initial hp
		final int initPlantHP = p.getCurrentHP();
		// Hit it
		testZombie1.hit(p);
		// Find HP after hit
		final int finalPlantHP = p.getCurrentHP();

		// Check if plant hp was reduced by correct amount
		final boolean plantHit = (finalPlantHP == initPlantHP - Zombie.DEFAULT_ATK);

		assertTrue("Plant hp should have been reduced", plantHit);
	}


}
