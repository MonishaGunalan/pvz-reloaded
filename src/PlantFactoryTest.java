import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
public class PlantFactoryTest {
	
	@Test
	public void testMakePlantWithNullType(){
		assertTrue(PlantFactory.makePlant(null) instanceof Zombie);
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
