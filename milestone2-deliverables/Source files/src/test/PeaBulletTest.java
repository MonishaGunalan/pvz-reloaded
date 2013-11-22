package test;
import static org.junit.Assert.*;
import junit.framework.TestCase;
import pvz.*;
import org.junit.Before;
import org.junit.Test;

public class PeaBulletTest{

	private Level testLevel1;
	private Field testField1;
	private Strip testStrip1;
	private Square testSquare1;
	private Zombie testZombie1;
	private PeaBullet testPeaBullet;
	private String[] terrainType = { "mud", "grass", "grass", "grass", "mud" };

	@Before
	public void setUp() throws Exception {
		testLevel1 = new Level(1);
		testField1 = new Field(terrainType, testLevel1);
		testStrip1 = new Strip("grass", 3, testField1);
		testSquare1 = new Square("grass", 3, 2, testStrip1);
		testZombie1 = new NormalZombie();
		testPeaBullet = new PeaBullet();
		testZombie1.setSquare(testSquare1);
	}

	@Test
	public void getType() {
		assertTrue(testPeaBullet.getType().name().equals("PEA"));
	}
}


