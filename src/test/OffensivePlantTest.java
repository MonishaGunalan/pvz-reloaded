package test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pvz.Field;
import pvz.GameModel;
import pvz.Level;
import pvz.OffensivePlant;
import pvz.Square;
import pvz.Strip;
import pvz.Zombie;
import pvz.Zombie.Type;
/**
 * 
 * @author Christopher Nguyen
 *
 */
public class OffensivePlantTest {

	OffensivePlant plant;

	Strip strip;
	Zombie zombie;
	Field field;
	Level level;

	@Before
	public void setUp() throws Exception {
		//Mock the clases so we do not have to worry about the implemenation of the classes
		GameModel model = new GameModel();
		level = model.getLevel();
		field = level.getField();
		strip = field.getStrip()[0];

		zombie = new Zombie(2){

			@Override
			public Type getType() {
				// TODO Auto-generated method stub
				return null;
			}
			
		};
		strip.getSquare(5).add(zombie);
		plant = new OffensivePlant(10, strip.getSquare(0) ){

		};

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testShootViaUpdate() {
		plant.update(null, null);
		plant.update(null, null);
		assertTrue(strip.getSquare(0).hasBullet());
	}

}
