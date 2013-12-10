package test;
/**
 * Test cases for Builder Model Class 
 * 
 * @author Monisha Gunalan 100871444
 */
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pvz.level.*;

public class BuilderModelTest {
	private BuilderModel model;

	@Before
	public void setUp() throws Exception {
		model = new BuilderModel();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLoadDefaultValuesForLevel() {
		assertEquals(
				"level1\nMUD 0\nMUD 0\nGRASS 6\n10 normal\n20 normal\n30 normal\n40 normal\n50 normal\n60 normal\nMUD 0\nMUD 0",
				model.getDefaultValues(0));
	}
	
	@Test
	public void testGetLevels(){
		String[] testLevel = {"level1", "level2","level3"};
		model.setLevelsToEdit(testLevel);
		assertArrayEquals(testLevel, model.getLevels());
	}

	@Test
	public void testGetTerrianList() {
		String [] terrainList = {"MUD","MUD","GRASS","GRASS", "MUD" };
		model.setTerrainList(terrainList);
		assertArrayEquals(terrainList, model.getTerrianList());	
	}
	
	@Test
	public void testGetNumZombies() {
		int[] numZ = {0,1,2,0,2};
		model.setNumZombies(numZ);
		assertArrayEquals(numZ, model.getNumZombies());	
	}
	
}
