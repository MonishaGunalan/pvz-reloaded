package test;

import static org.junit.Assert.*;

import org.junit.*;

import pvz.level.Field;
import pvz.level.Level;
import pvz.level.Square;
import pvz.level.Strip;
import pvz.unit.NormalZombie;
import pvz.unit.ConeZombie;
import pvz.unit.FlagZombie;
import pvz.unit.Zombie;
import pvz.unit.Zombie.Type;

/**
 * This class contains unit testing for all 
 * methods in the abstract class Plant
 * @author Tianming Zhuang/Arzaan Irani (added in tests for new zombies)
 * 100875151
 
 */
public class PlantTest {
	private Level testLevel1;
	private Field testField1;
	private Strip testStrip1;
	private Square testSquare1;
	private Zombie testZombie1;
	private Zombie testZombie2;
	private Zombie testZombie3;
	private String[] terrainType = { "mud", "grass", "grass", "grass", "mud" };

	@Before
	public void setUp() throws Exception {
		testLevel1 = new Level(1);
		testField1 = new Field(terrainType, testLevel1);
		testStrip1 = new Strip("grass", 3, testField1);
		testSquare1 = new Square( 3, 2, testStrip1);
		testZombie1 = new NormalZombie();
		testZombie2 = new ConeZombie();
		testZombie3 = new FlagZombie();

	}
	
	@Test
	public void defaultConstructor(){
		assertTrue("Max HP should be set to default", testZombie1.getMaxHP() == NormalZombie.MAX_HP);
	}

	@Test
	public void getType(){
		assertTrue("NormalZombie type should be NORMAL", testZombie1.getType() == Zombie.Type.NORMAL);
	}

	@Test
	public void defaultConeConstructor(){
		assertTrue("Max HP should be set to default", testZombie2.getMaxHP() == ConeZombie.MAX_HP);
	}

	@Test
	public void getConeType(){
		assertTrue("ConeZombie type should be NORMAL", testZombie2.getType() == Zombie.Type.CONE);
	}

		@Test
	public void defaultFlagConstructor(){
		assertTrue("Max HP should be set to default", testZombie3.getMaxHP() == FlagZombie.MAX_HP);
	}

	@Test
	public void getFlagType(){
		assertTrue("FlagZombie type should be NORMAL", testZombie3.getType() == Zombie.Type.FLAG);
	}
}

