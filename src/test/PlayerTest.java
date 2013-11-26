package test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pvz.*;
import pvz.level.Field;
import pvz.level.GameModel;
import pvz.level.Level;
import pvz.level.Player;
import pvz.level.PlayerCommand;
import pvz.level.Square;
import pvz.level.Strip;
import pvz.unit.Plant;

/**
 * 
 * @author Christopher Nguyen
 *
 */
public class PlayerTest {
	Player player;
	Level level;
	Square square, square2;
	Strip strip;
	Field field;
	GameModel model;
	int row, col;
	@Before
	public void setUp() throws Exception {
		model = new GameModel();
		level = model.getLevel();
		row = 0;
		col = 0;
		field = level.getField();
		strip = field.getStrip()[row];

		player = new Player(model);
		square = strip.getSquare(col);
		square2 = strip.getSquare(col+1);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGrowSunflower(){
		assertTrue(player.grow(row, col, Plant.Type.SUNFLOWER) == Player.PlayStatus.NORMAL);

	}

	@Test
	public void testGrowPeashooter(){
		assertTrue(player.grow(row, col, Plant.Type.PEASHOOTER) == Player.PlayStatus.NORMAL);

	}

	@Test
	public void testGrowSunWithNoSun(){
		player.grow(0, 1, Plant.Type.PEASHOOTER);
		assertTrue(player.grow(row, col, Plant.Type.SUNFLOWER) == Player.PlayStatus.NOT_ENOUGH_SUN);

	}

	@Test
	public void testGrowSunflowerOnCooldown(){
		player.grow(row, col, Plant.Type.SUNFLOWER);
		assertTrue(player.grow(row, col+1, Plant.Type.SUNFLOWER) == Player.PlayStatus.COOLDOWN_NOT_READY);

	}

	@Test
	public void testPlayWithNullPlayerCommand() {
		assertTrue(player.play(null) == Player.PlayStatus.INVALID_COMMAND);
	}

	@Test
	public void testPlayWithPlantSeedPlayerCommandWithSunflower() {
		
		PlayerCommand command = new PlayerCommand(PlayerCommand.CommandType.PLANT_SEED, 0, 0, "sunflower");
		assertTrue(player.play(command) == Player.PlayStatus.NORMAL);
	}
	
	@Test
	public void testPlayWithPlantSeedPlayerCommandWithPeashooter() {
		PlayerCommand command = new PlayerCommand(PlayerCommand.CommandType.PLANT_SEED, 0, 0, "peashooter");
		Player.PlayStatus status = player.play(command);
		System.out.println("STATUS: " + status);
		//assertTrue(player.play(command) == Player.PlayStatus.NORMAL);
		assertTrue(status == Player.PlayStatus.NORMAL);
	}
	
	@Test
	public void testPlayWithDoNothing() {
		PlayerCommand command = new PlayerCommand(PlayerCommand.CommandType.DO_NOTHING, 0, 0, "");
		assertTrue(player.play(command) == Player.PlayStatus.NORMAL);
	}

}
