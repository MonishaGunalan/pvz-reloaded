package test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pvz.level.*;
public class DeepCopyTest {
	Level level;
	@Before
	public void setUp() {
		level = new Level(1);
	}

	@Test
	public void test() {
		Level copy = (Level)DeepCopy.copy(level);
		assertTrue(level.getLevelNumber() == copy.getLevelNumber());
		assertTrue(level.getTurnNumber() == copy.getTurnNumber());
	}
}
