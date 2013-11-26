package test;
/*
 * This class contains unit testing for all 
 * methods in the abstract class SunGenerator
 * @author Tianming Zhuang
 * 100875151
 
 */
import static org.junit.Assert.*;

import org.junit.*;

import pvz.level.Field;
import pvz.level.Level;
import pvz.level.Square;
import pvz.level.Strip;
import pvz.unit.SunGenerator;
import pvz.unit.Zombie;

public class SunGeneratorTest {
	private Level testLevel1;
	private Field testField1;
	private Strip testStrip1;
	private Square testSquare1;
	private Zombie testZombie1;
	private String[] terrainType = { "mud", "grass", "grass", "grass", "mud" };
	private SunGenerator testGenerator1;
	private int testTrigger;
	private int testSunAmt;

	@Before
	public void setUp() throws Exception {
		testLevel1 = new Level(1);
		testField1 = new Field(terrainType, testLevel1);
		testStrip1 = new Strip("grass", 3, testField1);
		testSquare1 = new Square( 3, 2, testStrip1);
		testTrigger = 2;
		testSunAmt = 10;
		testGenerator1 = new SunGenerator(testTrigger, testSunAmt, testField1);
	}
	
	@Test
	public void generateSun(){
		int initSun = testField1.getTotalSun();
		testGenerator1.generateSun();
		int finalSun = testField1.getTotalSun();

		// Check that sun was produced
		final boolean isProducing = (finalSun == initSun + testSunAmt);

		initSun = testField1.getTotalSun();
		testGenerator1.generateSun();
		finalSun = testField1.getTotalSun();

		// Check that generator is on cooldown
		// after producing
		final boolean cooldownWorking = (finalSun == initSun);

		assertTrue(isProducing && cooldownWorking);
	}
}
