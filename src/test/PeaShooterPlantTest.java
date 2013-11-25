package test;
/*
 * This class contains unit testing for all 
 * methods in the abstract class PeaShooterPlant
 * @author Tianming Zhuang
 * 100875151
 
 */
import static org.junit.Assert.*;

import org.junit.*;

import pvz.Field;
import pvz.Level;
import pvz.PeaShooterPlant;
import pvz.Plant;
import pvz.Square;
import pvz.Strip;
import pvz.Zombie;

public class PeaShooterPlantTest {
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
	}
	
	@Test
	public void constructor(){
		Plant p = new PeaShooterPlant(testSquare1);
		assertTrue("Constructor should set square to specified one", p.getSquare() == testSquare1);
	}
}
