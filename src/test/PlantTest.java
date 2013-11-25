package test;

import static org.junit.Assert.*;

import org.junit.*;

import pvz.Field;
import pvz.Level;
import pvz.NormalZombie;
import pvz.Square;
import pvz.Strip;
import pvz.Zombie;
import pvz.Zombie.Type;

/**
 * This class contains unit testing for all 
 * methods in the abstract class Plant
 * @author Tianming Zhuang
 * 100875151
 
 */
public class PlantTest {
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
	public void defaultConstructor(){
		assertTrue("Max HP should be set to default", testZombie1.getMaxHP() == NormalZombie.MAX_HP);
	}

	@Test
	public void getType(){
		assertTrue("NormalZombie type should be NORMAL", testZombie1.getType() == Zombie.Type.NORMAL);
	}
}
