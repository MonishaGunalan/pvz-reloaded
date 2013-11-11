package test;
/*
 * This class contains unit testing for all 
 * methods in the abstract class Zombie
 * @author Tianming Zhuang
 * 100875151

*/
import static org.junit.Assert.*;

import org.junit.*;

import pvz.Field;
import pvz.Level;
import pvz.NormalZombie;
import pvz.Plant;
import pvz.PlantFactory;
import pvz.Square;
import pvz.Strip;
import pvz.Zombie;
import pvz.Field.Direction;
import pvz.Zombie.Type;


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
			testSquare1 = new Square("grass", 3, 2, testStrip1);
			testZombie1 = new NormalZombie();

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
		public void normalMove(){
			// Tick cooldowns so zombie can move
			for (int i=0; i<Zombie.DEFAULT_MOVE_TRIGGER; i++) {
				testZombie1.tickCooldowns();
			}
			final int initCol = testZombie1.getCol();
			testZombie1.move();
			final int finalCol = testZombie1.getCol();

			// Since we're moving left by default,
			// finalCol should be 1 less than initCol after move
			final boolean moved = (initCol - finalCol == 1);
			assertTrue("Default move should move zombie left by 1", moved);
		}

	@Test
		public void moveRight(){
			// Tick cooldowns so zombie can move
			for (int i=0; i<Zombie.DEFAULT_MOVE_TRIGGER; i++) {
				testZombie1.tickCooldowns();
			}
			final int initCol = testZombie1.getCol();
			testZombie1.move(Field.Direction.RIGHT);
			final int finalCol = testZombie1.getCol();

			// Since we're moving right,
			// finalCol should be 1 more than initCol after move
			final boolean moved = (finalCol - initCol == 1);
			assertTrue("Default move should move zombie left by 1", moved);
		}

	//@Test
		//public void moveOffField(){
			//testZombie1.setSquare(testField1.getStrips()[0].getSquares()[0]);
			//final int initCol = testZombie1.getCol();
			//testZombie1.move();
			//final int finalCol = testZombie1.getCol();

			//// We're moving left from 0,0,
			//final boolean moved = (finalCol - init = 1)
			//assertTrue("Default move should move zombie left by 1", moved);
		//}

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


	@Test
		public void getType(){
			assertTrue("Check that zombie type is normal", testZombie1.getType() == Zombie.Type.NORMAL);
		}

}
