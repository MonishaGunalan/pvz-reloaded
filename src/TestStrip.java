import junit.framework.TestCase;

public class TestStrip extends TestCase {

	private Strip testStrip;
	private Field testField1;

	protected void setUp() throws Exception {
		super.setUp();
		testStrip = new Strip("grass", 3, null);
	}

	// *** Test Constructor

	public void testGetField() {
		assertSame("The field that contains the strip should be testField1",
				testField1, testStrip.getField());
	}

	public void testGetSquares() {
		assertEquals("Total squares in strip should be"
				+ Field.DEFAULT_MAX_POSN + ".", Field.DEFAULT_MAX_POSN,
				testStrip.getSquares().length);
	}

	public void testGetSquare1() {
		assertNull("The square in the requested position should be null.",
				testStrip.getSquare(-2));
	}

	public void testGetSquare2() {
		assertEquals("The square at position 0 should be on Row 3.", 3,
				testStrip.getSquare(0).getRow());
	}

	public void testGetSquare3() {
		assertEquals("The square at position 4 should be on Row 3.", 3,
				testStrip.getSquare(4).getRow());
	}

	public void testGetSquare4() {
		assertNull("The square in the requested position should be null.",
				testStrip.getSquare(Field.DEFAULT_MAX_POSN));
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
