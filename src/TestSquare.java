import junit.framework.TestCase;

public class TestSquare extends TestCase {
	private Square testSquare1, testSquare2, testSquare3, testSquare4;
	private Zombie testZombie;
	private Strip testStrip1;
	private Plant testPlant;
	private Bullet testBullet;
	private Field testField2;
	private Level testLevel2;
	private String[] terrainType = { "mud", "grass", "grass", "grass", "mud" };

	protected void setUp() throws Exception {
		super.setUp();
		testLevel2 = new Level(1);
		testField2 = new Field(terrainType, testLevel2);
		testStrip1 = new Strip("grass", 3, testField2);
		testSquare1 = new Square("grass", 3, 2, testStrip1);
		testSquare2 = new Square("grass", 1, 1, testStrip1);
		testSquare3 = new Square("grass", 3, 3, testStrip1);
		testSquare4 = new Square("grass", 3, 1, testStrip1);
		testZombie = new NormalZombie();
		testPlant = new SunflowerPlant(testSquare3);
		testBullet = new PeaBullet();
	}

	public void testGetRow1() {
		assertEquals("Row number should be 3.", 3, testSquare1.getRow());
	}

	public void testGetRow2() {
		assertEquals("Row number should be 1.", 1, testSquare2.getRow());
	}

	public void testGetCol1() {
		assertEquals("Row number should be 2.", 2, testSquare1.getCol());
	}

	public void testGetCol2() {
		assertEquals("Row number should be 1.", 1, testSquare2.getCol());
	}

	public void testGetPlant() {
		assertNull("The initial value of plant in square should be null.",
				testSquare1.getPlant());
	}

	public void testGetFirstZombie() {
		assertNull(
				"The initial value of first zombie in square should be null.",
				testSquare1.getFirstZombie());
	}

	public void testGetSquare() {
		assertSame("There should be a  square in requested direction.",
				testStrip1.getSquare(2),
				testStrip1.getSquare(1).getSquare(Field.Direction.RIGHT));
	}

	public void testGetSquare1() {
		assertSame("There should be a  square in requested direction.",
				testStrip1.getSquare(2),
				testStrip1.getSquare(3).getSquare(Field.Direction.LEFT));
	}

	public void testAddZ() {
		testSquare1.add(testZombie);
		assertEquals("The Zombie Array should contain a zombie.", 1,
				testSquare1.getZombies().size());
	}

	public void testHasZombie() {
		testSquare1.add(testZombie);
		assertTrue("The square should have a zombie.", testSquare1.hasZombie());
	}

	// test the getFirstZombie method after adding a zombie
	public void testGetFirstZombie1() {
		testSquare1.add(testZombie);
		assertSame("The first Zombie should be testZombie.", testZombie,
				testSquare1.getFirstZombie());
	}

	public void testAddP() {
		testSquare3.add(testPlant);
		assertSame("The square should contain testPlant.", testPlant,
				testSquare3.getPlant());
	}

	public void testHasPlant() {
		assertTrue("The square should have a plant.", testSquare3.hasPlant());
	}

	public void testAddB() {
		testSquare4.add(testBullet);
		assertEquals("The bullet Array should contain a bullet.", 1,
				testSquare4.getBullets().size());
	}

	public void testHasBullet() {
		testSquare4.add(testBullet);
		assertTrue("The square should have a bullet.", testSquare4.hasBullet());
	}

	public void testRemoveZombieZ() {
		testSquare1.add(testZombie);
		assertTrue("The zombie should be removed.",
				testSquare1.remove(testZombie));
		assertEquals("The Zombie Array should be empty.", 0, testSquare1
				.getZombies().size());
	}

	public void testHasZombie1() {
		assertFalse("The square should not have a Zombie.",
				testSquare1.hasZombie());
	}

	public void testRemoveBullet() {
		testSquare4.add(testBullet);
		assertTrue("The Bullet removed should be testBullet.",
				testSquare4.remove(testBullet));
		assertEquals("The Bullet Array should be empty.", 0, testSquare4
				.getBullets().size());
	}

	public void testHasBullet1() {
		assertFalse("The square should not have a bullet.",
				testSquare4.hasBullet());
	}

	public void testRemoveBullet1() {
		assertFalse("There should be no bullet in the square.",
				testSquare4.remove(testBullet));
		assertEquals("The Bullet Array should be empty.", 0, testSquare4
				.getBullets().size());
	}

	public void testRemovePlant() {
		assertTrue("The plants should be removed.",
				testSquare3.remove(testPlant));
		assertNull("The square should not conatin a plant.",
				testSquare1.getPlant());
	}

	public void testHasPlant1() {
		assertFalse("The square should not have a plant.",
				testSquare4.hasPlant());
	}

	public void testGetLoc() {
		System.out.println(testSquare1.getLoc());
		assertEquals("The square should be located in row 3 and col 2.",
				"Square@3,2", testSquare1.getLoc());
	}

	public void testGetStrip() {
		assertSame("The strip should be testStrip1.", testStrip1,
				testSquare3.getStrip());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}