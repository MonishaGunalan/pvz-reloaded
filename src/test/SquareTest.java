package test;

/**
 * This class contains unit testing for all the methods in the class Square
 * @author Monisha Gunalan
 * 100871444

 */
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pvz.Bullet;
import pvz.Field;
import pvz.Level;
import pvz.NormalZombie;
import pvz.PeaBullet;
import pvz.Plant;
import pvz.Square;
import pvz.Strip;
import pvz.SunflowerPlant;
import pvz.Zombie;
import pvz.Field.Direction;

import junit.framework.TestCase;

public class SquareTest {
	private Square testSquare1, testSquare2, testSquare3, testSquare4;
	private Zombie testZombie;
	private Strip testStrip1, testStrip2;
	private Plant testPlant;
	private Bullet testBullet;
	private Field testField2;
	private Level testLevel2;
	private String[] terrainType = { "mud", "grass", "grass", "grass", "mud" };

	@Before
	/**
	 * Set up the fixture
	 * @throws Exception
	 */
	public void setUp() throws Exception {

		testLevel2 = new Level(1);
		testField2 = new Field(terrainType, testLevel2);
		testStrip1 = new Strip("grass", 3, testField2);
		testSquare1 = new Square( 3, 2, testStrip1);
		testSquare2 = new Square( 1, 1, testStrip1);
		testSquare3 = new Square( 3, 3, testStrip1);
		testSquare4 = new Square( 3, 1, testStrip1);
		testZombie = new NormalZombie();
		testPlant = new SunflowerPlant(testSquare3);
		testBullet = new PeaBullet();
	}

	/**
	 * Test method getRow in class square
	 */
	@Test
	public void testGetRow1() {
		assertEquals("Row number should be 3.", 3, testSquare1.getRow());
	}

	/**
	 * Test method getRow in class square
	 */
	@Test
	public void testGetRow2() {
		assertEquals("Row number should be 1.", 1, testSquare2.getRow());
	}

	/**
	 * Test method getCol in class square
	 */
	@Test
	public void testGetCol1() {
		assertEquals("Row number should be 2.", 2, testSquare1.getCol());
	}

	/**
	 * Test method getCol in class square
	 */
	@Test
	public void testGetCol2() {
		assertEquals("Row number should be 1.", 1, testSquare2.getCol());
	}

	/**
	 * Test method getPlant in class square. The plant is initialised to null in
	 * the constructor
	 */
	@Test
	public void testGetPlant1() {
		assertNull("The initial value of plant in square should be null.",
				testSquare1.getPlant());
	}

	/**
	 * Test Failure condition Test method getPlant in class square after adding
	 * a plant
	 * 
	 */
	@Test
	public void testGetPlant2() {
		testSquare3.add(testPlant);
		assertNotNull(
				"The value of the plant in the square should not be null.",
				testSquare3.getPlant());
	}

	/**
	 * Test method getFirstZombie in class square. There is no Zombie in the
	 * square yet so it returns a null
	 */
	@Test
	public void testGetFirstZombie0() {
		assertNull(
				"The initial value of first zombie in square should be null.",
				testSquare1.getFirstZombie());
	}

	/**
	 * Test method getFirstZombie in class square. The test should not return
	 * null after a zombie is added to the square
	 */
	@Test
	public void testGetFirstZombie2() {
		testSquare1.add(testZombie);
		assertNotNull(
				"The value of first zombie in square should not be null.",
				testSquare1.getFirstZombie());
	}

	/**
	 * Test method getSquare in class square. getSquare method returns te
	 * nearest square in the requested direction.
	 */
	@Test
	public void testGetSquare() {
		assertSame("There should be a  square in requested direction.",
				testStrip1.getSquare(2),
				testStrip1.getSquare(1).getSquare(Field.Direction.RIGHT));
	}

	/**
	 * Test method getSquare in class square.getSquare method returns te nearest
	 * square in the requested direction
	 */
	@Test
	public void testGetSquare1() {
		assertSame("There should be a  square in requested direction.",
				testStrip1.getSquare(2),
				testStrip1.getSquare(3).getSquare(Field.Direction.LEFT));
	}

	/**
	 * Test method getSquare in class square.getSquare method returns te nearest
	 * square in the requested direction. The test fails if square returns
	 * itself instead of returning the next square
	 */
	@Test
	public void testGetSquare2() {
		assertNotSame("There should be a  square in requested direction.",
				testStrip1.getSquare(3),
				testStrip1.getSquare(3).getSquare(Field.Direction.LEFT));
	}

	/**
	 * Test method 'add' in class square which invokes addZombie method Test if
	 * the zombie arraylist size has been incremented
	 */
	@Test
	public void testAddZ() {
		testSquare1.add(testZombie);
		assertEquals("The Zombie Array should contain a zombie.", 1,
				testSquare1.getZombies().size());
	}

	/**
	 * Test method 'hasZombie' in class square by adding a zombie to the square
	 */
	@Test
	public void testHasZombie() {
		testSquare1.add(testZombie);
		assertTrue("The square should have a zombie.", testSquare1.hasZombie());
	}

	/**
	 * Test the getFirstZombie method after adding a zombie
	 */
	@Test
	public void testGetFirstZombie1() {
		testSquare1.add(testZombie);
		assertSame("The first Zombie should be testZombie.", testZombie,
				testSquare1.getFirstZombie());
	}

	/**
	 * Test method 'add' in class square which invokes addPlant method
	 */
	@Test
	public void testAddP() {
		testSquare3.add(testPlant);
		assertSame("The square should contain testPlant.", testPlant,
				testSquare3.getPlant());
	}

	/**
	 * Test method 'hasPlant' in class square which check if a plant is present
	 * in a square
	 */
	@Test
	public void testHasPlant() {
		assertTrue("The square should have a plant.", testSquare3.hasPlant());
	}

	/**
	 * Test method 'add' in class square which invokes addBullet method Test if
	 * the bullet arraylist size has been incremented
	 */
	@Test
	public void testAddB() {
		testSquare4.add(testBullet);
		assertEquals("The bullet Array should contain a bullet.", 1,
				testSquare4.getBullets().size());
	}

	/**
	 * Test method 'hasBullet' in class square which check if a bullet is
	 * present in a square after adding a bullet to the square
	 */
	@Test
	public void testHasBullet() {
		testSquare4.add(testBullet);
		assertTrue("The square should have a bullet.", testSquare4.hasBullet());
	}

	/**
	 * Test method 'remove' in class square which invokes removeZombie method
	 * and Tests if the zombie arraylist size has been decremented
	 */
	@Test
	public void testRemoveZombieZ() {
		testSquare1.add(testZombie);
		assertTrue("The zombie should be removed.",
				testSquare1.remove(testZombie));
		assertEquals("The Zombie Array should be empty.", 0, testSquare1
				.getZombies().size());
	}

	/**
	 * Test method 'hasZombie' in class square after removing the zombie from
	 * the square
	 */
	@Test
	public void testHasZombie1() {
		assertFalse("The square should not have a Zombie.",
				testSquare1.hasZombie());
	}

	/**
	 * Test method 'remove' in class square which invokes removeBullet method
	 * and Tests if the bullet arraylist size has been decremented
	 */
	@Test
	public void testRemoveBullet() {
		testSquare4.add(testBullet);
		assertTrue("The Bullet removed should be testBullet.",
				testSquare4.remove(testBullet));
		assertEquals("The Bullet Array should be empty.", 0, testSquare4
				.getBullets().size());
	}

	/**
	 * Test method 'hasBullet' in class square after removing the bullet from
	 * the square
	 */
	@Test
	public void testHasBullet1() {
		assertFalse("The square should not have a bullet.",
				testSquare4.hasBullet());
	}

	/**
	 * Test method 'remove' in class square which invokes removeBullet method
	 * and Tests if the bullet arraylist size has been decremented
	 */
	@Test
	public void testRemoveBullet1() {
		assertFalse("There should be no bullet in the square.",
				testSquare4.remove(testBullet));
		assertEquals("The Bullet Array should be empty.", 0, testSquare4
				.getBullets().size());
	}

	/**
	 * Test method 'remove' in class square which invokes removePlant method and
	 * Tests if the square does not contain the plant after it is removed
	 */
	@Test
	public void testRemovePlant() {
		assertTrue("The plants should be removed.",
				testSquare3.remove(testPlant));
		assertNull("The square should not conatin a plant.",
				testSquare1.getPlant());
	}

	/**
	 * Test method 'hasPlant' in class square after removing a plant from the
	 * square
	 */
	@Test
	public void testHasPlant1() {
		assertFalse("The square should not have a plant.",
				testSquare4.hasPlant());
	}

	/**
	 * Test method 'getLoc' in class square
	 */
	@Test
	public void testGetLoc() {
		//System.out.println(testSquare1.getLoc());
		assertEquals("The square should be located in row 3 and col 2.",
				"Square@3,2", testSquare1.getLoc());
	}

	/**
	 * Test method 'getStrip' in class square
	 */
	@Test
	public void testGetStrip() {
		assertSame("The strip should be testStrip1.", testStrip1,
				testSquare3.getStrip());
	}
	

	/**
	 * Test method 'getStrip' in class square
	 */
	@Test
	public void testGetStrip1() {
		assertNotSame("The strip should be testStrip1.", testStrip2,
				testSquare3.getStrip());
	}

	@After
	public void tearDown() throws Exception {

	}

}
