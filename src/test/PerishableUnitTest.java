package test;
/*
 * This class contains unit testing for all 
 * methods in the abstract class PeriahsableUnit
 * @author Tianming Zhuang
 * 100875151

*/
import static org.junit.Assert.*;

import org.junit.*;

import pvz.Field;
import pvz.Level;
import pvz.NormalZombie;
import pvz.Square;
import pvz.Strip;
import pvz.Zombie;

public class PerishableUnitTest {
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
		public void maxHP(){
			assertTrue("Current maxHP should be equal to the default.", testZombie1.getMaxHP() == NormalZombie.MAX_HP);
		}

	@Test
		public void defaultCurrentHP(){
			assertTrue("Current HP should initialize to max HP", testZombie1.getCurrentHP() == testZombie1.getMaxHP());
		}

	@Test
		public void reduceCurrentHPStandard(){
			final int amountToReduce = 5;
			testZombie1.reduceHP(amountToReduce);
			assertTrue("Reducing current HP of " + NormalZombie.MAX_HP + " by " + amountToReduce, 
					testZombie1.getCurrentHP() == (NormalZombie.MAX_HP - amountToReduce));
		}

	@Test (expected = IllegalArgumentException.class)
		public void reduceCurrentHPNegative(){
			final int amountToReduce = -5;
			testZombie1.reduceHP(amountToReduce);
		}

	@Test
		public void unitDie() {
			testLevel1.notifyObservers();
			// Number of observers in Level before unit dies
			final int initObservers = testLevel1.countObservers();
			// Call reduceHP
			final int amountToReduce = 11;
			testZombie1.reduceHP(amountToReduce);
			// Number of observers in Level after unit dies
			final int finalObservers = testLevel1.countObservers();
			testLevel1.notifyObservers();

			// Check that number of observers in Level decreased by 1
			final boolean observerRemoved = (initObservers - finalObservers == 1);
			//System.out.println("initObservers: " + initObservers);
			//System.out.println("finalObservers: " + finalObservers);
			//System.out.println("observersRemoved: " + observerRemoved);
			// Check that square no longer has a zombie
			final boolean unitRemoved = !testSquare1.hasZombie();
			//System.out.println("unitRemoved: " + unitRemoved);

			assertFalse("Reducing current HP of " + NormalZombie.MAX_HP + " by " + amountToReduce, 
					observerRemoved && unitRemoved);
		}
}
