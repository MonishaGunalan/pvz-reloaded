package test;
/*
 * This class contains unit testing for all 
 * methods in the abstract class FlagZombie
 * @author Arzaan Irani
 * 100826631
 
 */
import static org.junit.Assert.*;

import org.junit.*;

import pvz.level.Field;
import pvz.level.Level;
import pvz.level.Square;
import pvz.level.Strip;
import pvz.unit.FlagZombie;
import pvz.unit.ConeZombie;
import pvz.unit.NormalZombie;
import pvz.unit.Zombie;
import pvz.unit.Zombie.Type;


public class FlagZombieTest {
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
		testZombie1 = new FlagZombie();
	}
	
	@Test
	public void defaultConstructor(){
		assertTrue("Max HP should be set to default", testZombie1.getMaxHP() == FlagZombie.MAX_HP);
	}

	@Test
	public void getType(){
		assertTrue("FlagZombie type should be CONE", testZombie1.getType() == Zombie.Type.FLAG);
	}
}
