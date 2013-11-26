package test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pvz.PeaShooterPlant;
import pvz.Plant;
import pvz.PlantFactory;
import pvz.Square;
import pvz.Plant.Type;
/**
 * 
 * @author Christopher Nguyen
 *
 */
public class PlantFactoryTest {

	Square square;

	@Before
	public void setUp() throws Exception {
		square = new Square(0,0,null);

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
