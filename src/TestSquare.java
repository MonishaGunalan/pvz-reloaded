import junit.framework.TestCase;


public class TestSquare extends TestCase {
	private Square testSquare;
	
	//********create a temp Strip using Mock
	protected void setUp() throws Exception {
		super.setUp();
		testSquare = new Square("grass", 3, 2, null);
	}
	
	

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}