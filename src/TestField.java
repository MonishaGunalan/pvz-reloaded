import junit.framework.TestCase;

public class TestField extends TestCase {

	private Field testField;
	// private Level level;
	private String[] terrainType = { "mud", "grass", "grass", "grass", "mud" };

	// *****use mock to create level
	protected void setUp() throws Exception {
		super.setUp();
		// level = new Level(1);
		testField = new Field(terrainType, null);
	}

	// *** Test Constructor

	public void testGetLevel() {
		// yet to be filled
	}

	public void testGetStrip() {
		assertEquals("Total strips in field should be" + Field.DEFAULT_MAX_ROW
				+ ".", Field.DEFAULT_MAX_ROW, testField.getStrip().length);
	}

	public void testGetTotalSun() {
		assertEquals("Total sun points should be 0.", 0,
				testField.getTotalSun());
	}

	public void testAddSun() {
		int sunPoints = testField.getTotalSun();
		testField.addSun(4);
		assertEquals("Total sun points should be " + sunPoints + ".", (sunPoints+4),
				testField.getTotalSun());
	}

	public void testAddSun2() {
		int sunPoints = testField.getTotalSun();
		testField.addSun(-1);
		assertEquals("Total sun points should be " + sunPoints + ".", sunPoints,
				testField.getTotalSun());
	}

	public void testUseSun() {
		int sunPoints = testField.getTotalSun();
		testField.useSun(2);
		assertEquals("Total sun points should be "+ (sunPoints-2) +".", (sunPoints-2),
				testField.getTotalSun());
	}

	public void testUseSun2() {
		int sunPoints = testField.getTotalSun();
		testField.useSun(sunPoints+1);
		assertEquals("Total sun points should be "+ sunPoints +".", sunPoints,
				testField.getTotalSun());
	}

	// ********add a test method to check for negative value for use sun method

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}