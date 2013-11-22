package test;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pvz.PlayerCommand;
import pvz.PlayerCommand.CommandType;

/**
 * 
 * @author Chirstopher Nguyen
 *
 */
public class PlayerCommandTest {

	PlayerCommand playerCommand;
	int row, col;
	String arg;
	@Before
	public void setUp() throws Exception {
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