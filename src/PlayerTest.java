import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class PlayerTest {
	Player player;
	Level mockLevel;
	PlayerCommand mockCommand;
	Square square, square2;
	Strip strip;
	Field mockField;
	int row, col;
	@Before
	public void setUp() throws Exception {
		mockLevel = mock(Level.class);
		row = 0;
		col = 0;
		strip = mock(Strip.class);
		player = new Player(mockLevel);
		mockCommand = mock(PlayerCommand.class);
		square = new Square("",row,col,strip);
		square2 = new Square("",row,col+1,strip);
		mockField = mock(Field.class);
		when (mockLevel.getField()).thenReturn(mockField);
		when(mockField.getLevel()).thenReturn(mockLevel);
		when(strip.getField()).thenReturn(mockField);
		when(mockLevel.getSquare(row, col)).thenReturn(square);
		when(mockLevel.getSquare(row, col+1)).thenReturn(square2);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGrowSunflower(){

		when(mockField.getTotalSun()).thenReturn(9999);
		assertTrue(player.grow(row, col, Plant.Type.SUNFLOWER));

	}

	@Test
	public void testGrowPeashooter(){


		when(mockField.getTotalSun()).thenReturn(9999);
		assertTrue(player.grow(row, col, Plant.Type.PEASHOOTER));

	}

	@Test
	public void testGrowSunWithNoSun(){

		when(mockField.getTotalSun()).thenReturn(0);
		assertFalse(player.grow(row, col, Plant.Type.SUNFLOWER));

	}

	@Test
	public void testGrowSunflowerOnCooldown(){

		when(mockField.getTotalSun()).thenReturn(9999);
		player.grow(row, col, Plant.Type.SUNFLOWER);
		assertFalse(player.grow(row, col+1, Plant.Type.SUNFLOWER));

	}

	@Test
	public void testGrowSunflowerAfterCooldown(){


		when(mockField.getTotalSun()).thenReturn(9999);
		player.grow(row, col, Plant.Type.SUNFLOWER);
		for (int i = 0; i < 5; i++){
			player.triggerCooldowns();
		}
		assertTrue(player.grow(row, col+1, Plant.Type.SUNFLOWER));

	}

	@Test
	public void testPlayWithNullPlayerCommand() {
		assertFalse(player.play(null));
	}

	@Test
	public void testPlayWithPlantSeedPlayerCommandWithSunflower() {
		when(mockField.getTotalSun()).thenReturn(9999);
		when(mockCommand.getCommandType()).thenReturn(PlayerCommand.CommandType.PLANT_SEED);
		when(mockCommand.getArg()).thenReturn("sunflower");
		when(mockCommand.getX()).thenReturn(0);
		when(mockCommand.getY()).thenReturn(0);
		assertTrue(player.play(mockCommand));
	}
	
	@Test
	public void testPlayWithPlantSeedPlayerCommandWithPeashooter() {
		when(mockField.getTotalSun()).thenReturn(9999);
		when(mockCommand.getCommandType()).thenReturn(PlayerCommand.CommandType.PLANT_SEED);
		when(mockCommand.getArg()).thenReturn("peashooter");
		when(mockCommand.getX()).thenReturn(0);
		when(mockCommand.getY()).thenReturn(0);
		assertTrue(player.play(mockCommand));
	}
	
	@Test
	public void testPlayWithDoNothing() {
		when(mockCommand.getCommandType()).thenReturn(PlayerCommand.CommandType.DO_NOTHING);
		assertTrue(player.play(mockCommand));
	}

}
