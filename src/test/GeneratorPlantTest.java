package test;
import static org.junit.Assert.*;

import java.util.Observable;

import pvz.*;
import pvz.level.Field;
import pvz.level.Level;
import pvz.level.Square;
import pvz.level.Strip;
import pvz.unit.GeneratorPlant;
import pvz.unit.NormalZombie;
import pvz.unit.Zombie;

import org.junit.Before;
import org.junit.Test;


public class GeneratorPlantTest {

	private Level testLevel1;
	private Field testField1;
	private Strip testStrip1;
	private Square testSquare1;
	private GeneratorPlant testGeneratorPlant;
	private Zombie testZombie1;
	private String[] terrainType = { "mud", "grass", "grass", "grass", "mud" };

	@Before
	public void setUp() throws Exception {
		testLevel1 = new Level(1);
		testField1 = new Field(terrainType, testLevel1);
		testStrip1 = new Strip("grass", 3, testField1);
		testSquare1 = new Square( 3, 2, testStrip1);
		testZombie1 = new NormalZombie();
		testGeneratorPlant = new GeneratorPlant(4, testSquare1){

			@Override
			public void update(Observable o, Object arg) {
				// TODO Auto-generated method stub
				
			}
			
		};
	}
	
	@Test
	public void defaultConstructor(){
		assertTrue("Max HP should be set to default", 4 == testGeneratorPlant.getCurrentHP());
	}
}
