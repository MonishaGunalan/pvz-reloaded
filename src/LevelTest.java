import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
public class LevelTest {

	
	Level level;
	@Before
	public void setUp() throws Exception {

		level = new Level(1);

	}
	
	@Test
	public void testSpawnZombie(){
		level.spawnZombie(0, 0, "NORMAL");
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSpawnZombieInvalidRow(){
		level.spawnZombie(-1, 0, "NORMAL");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSpawnZombieInvalidRow2(){
		level.spawnZombie(Field.DEFAULT_MAX_ROW+1, 0, "NORMAL");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSpawnZombieInvalidTurn(){
		level.spawnZombie(0, -1, "NORMAL");
	}
	
	@Test
	public void testGetLevelNumber(){
		assertTrue(level.getLevelNumber() == 1);
	}
	
	@Test
	public void testGetField(){
		assertTrue(level.getField().getClass() == Field.class);
	}

	@Test
	public void testGetTurnNumber(){
		assertTrue(level.getTurnNumber() == 0);
	}
	
	@Test
	public void testGetTurnNumberAfterIncrementTurn(){
		assertTrue(level.getTurnNumber() == 0);
		level.incrementTurn();
		assertTrue(level.getTurnNumber() == 1);
	}
}