package test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pvz.level.Field;
import pvz.level.GameModel;
import pvz.level.Level;
import pvz.level.Square;
import pvz.level.Strip;
import pvz.unit.DefensivePlant;
import pvz.unit.OffensivePlant;
import pvz.unit.Zombie;
import pvz.unit.Zombie.Type;
/**
 * 
 * @author Arzaan irani
 *
 */
public class DefensivePlantTest {

	DefensivePlant plant;

	Strip strip;
	Zombie zombie;
	Field field;
	Level level;

	@Before
	public void setUp() throws Exception {
		//Mock the clases so we do not have to worry about the implemenation of the classes
		GameModel model = new GameModel(false);
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
		plant = new DefensivePlant(20, strip.getSquare(0) ){

		};

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testShootViaUpdate() {
		plant.update(null, null);
		plant.update(null, null);
		assertFalse(strip.getSquare(0).hasBullet());
	}

}
