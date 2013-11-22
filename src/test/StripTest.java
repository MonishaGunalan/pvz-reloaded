package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pvz.Field;
import pvz.Strip;

import static org.junit.Assert.*;
import junit.framework.TestCase;
/**
 * This class contains unit testing for all the methods in the class Strip
 * @author Monisha Gunalan
 * 100871444
 
 */

public class StripTest {

	private Strip testStrip;
	private Field testField1;

	@Before
	/**
	 * Set up the fixture
	 * @throws Exception
	 */
	public void setUp() throws Exception {
		testStrip = new Strip("grass", 3, null);
	}

	/**
	 * Method to test 'getField' method in class Strip
	 */
	@Test
	public void testGetField() {
		assertSame("The field that contains the strip should be testField1",
				testField1, testStrip.getField());
	}

	/**
	 * Method to test 'getSquares' method in class Strip
	 */
	@Test
	public void testGetSquares() {
		assertEquals("Total squares in strip should be"
				+ Field.DEFAULT_MAX_POSN + ".", Field.DEFAULT_MAX_POSN,
				testStrip.getSquares().length);
	}

	/**
	 * Method to test 'getSquare' method in class Strip
	 */
	@Test
	public void testGetSquare1() {
		assertNull("The square in the requested position should be null.",
				testStrip.getSquare(-2));
	}

	/**
	 * Method to test 'getSquares' method in class Strip
	 */
	@Test
	public void testGetSquare2() {
		assertEquals("The square at position 0 should be on Row 3.", 3,
				testStrip.getSquare(0).getRow());
	}

	/**
	 * Method to test 'getSquares' method in class Strip
	 */
	@Test
	public void testGetSquare3() {
		assertEquals("The square at position 4 should be on Row 3.", 3,
				testStrip.getSquare(4).getRow());
	}

	/**
	 * Method to test 'getSquares' method in class Strip
	 */
	@Test
	public void testGetSquare4() {
		assertNull("The square in the requested position should be null.",
				testStrip.getSquare(Field.DEFAULT_MAX_POSN));
	}

	@After
	public void tearDown() throws Exception {
	}

}
