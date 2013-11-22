package test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pvz.Field;
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
	Zombie mockZombie;
	Field mockField;
	Level mockLevel;

	@Before
	public void setUp() throws Exception {
		//Mock the clases so we do not have to worry about the implemenation of the classes
		mockLevel = mock(Level.class);
		mockField = mock(Field.class);
		when(mockLevel.getField()).thenReturn(mockField);
		when(mockField.getLevel()).thenReturn(mockLevel);
		mockZombie = mock(Zombie.class);
		strip = new Strip(null, 0, mockField);
		strip.getSquare(5).add(mockZombie);
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
