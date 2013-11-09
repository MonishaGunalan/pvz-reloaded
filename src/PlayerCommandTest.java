import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;




public class PlayerCommandTest extends TestCase {

	PlayerCommand playerCommand;
	int row, col;
	String arg;
	@Before
	public void setUp() throws Exception {
		super.setUp();
		row = 0;
		col = 0;
		arg = "abc";
		playerCommand = new PlayerCommand(PlayerCommand.CommandType.DO_NOTHING,row,col, arg);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetRow(){
		assertTrue(playerCommand.getRow()==row);

	}
	
	@Test
	public void testGetCol(){
		assertTrue(playerCommand.getCol()==col);

	}
	
	@Test
	public void testGetArg(){
		assertTrue(playerCommand.getArg().equals(arg));

	}

}