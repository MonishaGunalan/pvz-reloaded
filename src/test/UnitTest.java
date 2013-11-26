package test;

import static org.junit.Assert.*;

import org.junit.*;

import pvz.level.Field;
import pvz.level.Level;
import pvz.level.Square;
import pvz.level.Strip;
import pvz.unit.NormalZombie;
import pvz.unit.Zombie;
/**
 * This class contains unit testing for all 
 * methods in the abstract class Unit
 * @author Tianming Zhuang
 * 100875151
 
 */
public class UnitTest {
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
		testSquare1 = new Square( 3, 2, testStrip1);
		testZombie1 = new NormalZombie();
	}
	
	@Test
	public void NoSquareSet(){
		assertTrue("Zombie Unit initially has no Square set", testZombie1.getSquare() == null);
	}

	public void SetSquare(){
		testZombie1.setSquare(testSquare1);
		assertTrue("Square set should be the one returned by getSquare()", testZombie1.getSquare() == testSquare1);
	}
	
	@Test (expected = NullPointerException.class)
	public void GetRowNoSquareSet(){
		testZombie1.getRow();
	}

	@Test (expected = NullPointerException.class)
	public void GetColNoSquareSet(){
		testZombie1.getCol();
	}
	
	@Test
	public void GetRow(){
		testZombie1.setSquare(testSquare1);
		assertTrue("Row should be that of the square it was set to", testZombie1.getRow() == testSquare1.getRow());
	
	}

	@Test
	public void GetCol(){
		testZombie1.setSquare(testSquare1);
		assertTrue("Col should be that of the square it was set to", testZombie1.getCol() == testSquare1.getCol());
	
	}
	
	
}
