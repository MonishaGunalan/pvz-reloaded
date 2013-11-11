/*
 * This class contains unit testing for all the methods in the class Field
 * @author Monisha Gunalan
 * 100871444
 
 */
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import junit.framework.TestCase;

public class FieldTest {

	private Field testField;
	private Level testLevel;
	private String[] terrainType = { "mud", "grass", "grass", "grass", "mud" };

	@Before
	public void setUp() throws Exception {
		testLevel = new Level(1);
		testField = new Field(terrainType, testLevel);
	}

	/*
	 * Method to test 'getLevel' method in class Field
	 */
	@Test
	public void testGetLevel() {
		assertEquals("Total strips in field should be" + testLevel + ".",
				testLevel, testField.getLevel());
	}

	/*
	 * Method to test 'getStrip' method in class Field
	 */
	@Test
	public void testGetStrip() {
		assertEquals("Total strips in field should be" + Field.DEFAULT_MAX_ROW
				+ ".", Field.DEFAULT_MAX_ROW, testField.getStrip().length);
	}

	/*
	 * Method to test 'getTotalSun' method in class Field
	 */
	@Test
	public void testGetTotalSun() {
		assertEquals("Total sun points should be 0.", 0,
				testField.getTotalSun());
	}

	/*
	 * Method to test 'addSun' method in class Field
	 */
	@Test
	public void testAddSun() {
		int sunPoints = testField.getTotalSun();
		testField.addSun(4);
		assertEquals("Total sun points should be " + sunPoints + ".",
				(sunPoints + 4), testField.getTotalSun());
	}

	/*
	 * Method to test 'addSun' method in class Field
	 */
	@Test
	public void testAddSun2() {
		int sunPoints = testField.getTotalSun();
		testField.addSun(-1);
		assertEquals("Total sun points should be " + sunPoints + ".",
				sunPoints, testField.getTotalSun());
	}

	/*
	 * Method to test 'useSun' method in class Field
	 */
	@Test
	public void testUseSun() {
		int sunPoints = testField.getTotalSun();
		testField.useSun(2);
		assertEquals("Total sun points should be " + (sunPoints - 2) + ".",
				(sunPoints), testField.getTotalSun());
	}

	/*
	 * Method to test 'useSun' method in class Field
	 */
	@Test
	public void testUseSun2() {
		int sunPoints = testField.getTotalSun();
		testField.useSun(sunPoints + 1);
		assertEquals("Total sun points should be " + sunPoints + ".",
				sunPoints, testField.getTotalSun());
	}

	@After
	public void tearDown() throws Exception {

	}

}