package test;
import static org.junit.Assert.*;
import junit.framework.TestCase;
import pvz.*;
import org.junit.Before;
import org.junit.Test;

public class OffensivePlantTest{
	private Level testLevel1;
	private Field testField1;
	private Strip testStrip1;
	private Square testSquare1;
	private Zombie testZombie1;
	private Plant testPlant1;
	private OffensivePlant testOfPlant;
	private String[] terrainType = { "mud", "grass", "grass", "grass", "mud" };

	@Before
	public void setUp() throws Exception {
		testLevel1 = new Level(1);
		testField1 = new Field(terrainType, testLevel1);
		testStrip1 = new Strip("grass", 3, testField1);
		testSquare1 = new Square("grass", 3, 2, testStrip1);
	}
	/*
	@Test
	public void getShootTriggerAmt(){
		assertTrue("Default shoot trigger", 
				testOfPlant.getShootTriggerAmt() == OffensivePlant.DEFAULT_SHOOT_TRIGGER);
	}
	
	@Test
	public void getBulletType(){
		assertTrue("Bullet type should be DEFAULT_BULLET", 
				((OffensivePlant) testOfPlant).getBulletType() == testOfPlant.DEFAULT_BULLET);
	}
	
	@Test
	public void shoot() {
		testLevel1.notifyObservers();
		// Number of observers in Level before unit dies
		final int initObservers = testLevel1.countObservers();
		// Call reduceHP
		final int amountToAdd = 11;
		testZombie1.reduceHP(amountToAdd);
		// Number of observers in Level after unit dies
		final int finalObservers = testLevel1.countObservers();
		testLevel1.notifyObservers();

		// Check that number of observers in Level increased by 1
		final boolean observerAdded = (initObservers - finalObservers >= 1);
		System.out.println("initObservers: " + initObservers);
		System.out.println("finalObservers: " + finalObservers);
		System.out.println("observersRemoved: " + observerAdded);
		// Check that square no longer has a zombie
		final boolean unitAdded = !testSquare1.hasZombie();
		System.out.println("unitAdded: " + unitAdded);

		assertTrue("Reducing current HP of " + NormalZombie.MAX_HP + " by " + amountToAdd, 
				observerAdded && unitAdded);
	}
	
*/
}
