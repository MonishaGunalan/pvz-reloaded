package test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pvz.*;
import pvz.level.Level;
import pvz.level.Square;
import pvz.unit.PeaShooterPlant;
import pvz.unit.Plant;
import pvz.unit.PlantFactory;
/**
 * 
 * @author Christopher Nguyen
 *
 */
public class PlantFactoryTest {
	Level level;
	Square square;

	@Before
	public void setUp() throws Exception {
		level = new Level(1);
		square = level.getField().getStrip()[2].getSquare(0);
	}

	@Test
	public void testMakePlantWithNullSquare(){
		assertTrue(PlantFactory.makePlant(Plant.Type.PEASHOOTER, null) == null);
	}

	@Test
	public void testMakePlantWithNullType(){
		assertFalse(PlantFactory.makePlant(null, square) instanceof Plant);
	}

	@Test
	public void testMakePlant(){
		if (square == null) {
			System.out.println("WTF");
		}
		assertTrue(PlantFactory.makePlant(Plant.Type.PEASHOOTER, square).getClass() == PeaShooterPlant.class);
	}


	@Test
	public void testGetClass(){
		assertTrue(PlantFactory.getClass(Plant.Type.PEASHOOTER) == PeaShooterPlant.class);

	}

	@Test
	public void testGetCooldown(){
		assertTrue(PlantFactory.getCooldown(Plant.Type.SUNFLOWER)== 3);
	}

	@Test
	public void testGetCost(){
		assertTrue(PlantFactory.getCost(Plant.Type.SUNFLOWER)== 50);
	}
}
