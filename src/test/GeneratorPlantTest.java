package test;
import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import pvz.SunGenerator;

public class GeneratorPlantTest extends TestCase{

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
	}
	
	@Test
	public void defaultConstructor(){
		assertTrue("Max HP should be set to default", testZombie1.getMaxHP() == NormalZombie.MAX_HP);
	}
}
