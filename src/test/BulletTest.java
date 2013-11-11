package test;
import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import pvz.Bullet;
import pvz.Zombie;


import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;


public class BulletTest extends TestCase{
	private Level testLevel1;
	private Field testField1;
	private Strip testStrip1;
	private Square testSquare1;
	private Zombie testZombie1;
	private Bullet testBullet1;
	private String[] terrainType = { "mud", "grass", "grass", "grass", "mud" };

	@Before
	public void setUp() throws Exception {
		testLevel1 = new Level(1);
		testField1 = new Field(terrainType, testLevel1);
		testStrip1 = new Strip("grass", 3, testField1);
		testSquare1 = new Square("grass", 3, 2, testStrip1);
		testZombie1 = new NormalZombie();

		testZombie1.setSquare(testSquare1);
	}

	@Test
	public void TestHit(Zombie zombie) {
		testLevel1.notifyObservers();
		final int initObservers = testLevel1.countObservers();
		final int amountToReduce = 11;
		testZombie1.reduceHP(amountToReduce);
		final int finalObservers = testLevel1.countObservers();
		testLevel1.notifyObservers();
		final boolean observerRemoved = (initObservers - finalObservers == 1);
		System.out.println("initObservers: " + initObservers);
		System.out.println("finalObservers: " + finalObservers);
		System.out.println("observersRemoved: " + observerRemoved);
		final boolean unitRemoved = !testSquare1.hasZombie();
		System.out.println("unitRemoved: " + unitRemoved);
		assertTrue("Reducing current HP of " + NormalZombie.MAX_HP + " by " + amountToReduce, 
				observerRemoved && unitRemoved);
	}

	@Test
	public void getDmg(){
		assertTrue("Default dmg", 
				testZombie1.getDmg() == Zombie.DEFAULT_ATK);
	}
	
	@Test
	public void getMoveTriggerAmt(){
		assertTrue("Default move trigger", 
				testZombie1.getMoveTriggerAmt() == Zombie.DEFAULT_MOVE_TRIGGER);
	}


	@Test
	public void TestbulletMove(Field.Direction dir){
		Square dest = testBullet1.getSquare().getSquare(dir);
		for (int i=0; i<Zombie.DEFAULT_MOVE_TRIGGER; i++) {
			testBullet1.tickCooldowns();
		}
		final int initCol = testZombie1.getCol();
		testBullet1.move(Field.Direction.RIGHT);
		final int finalCol = testZombie1.getCol();
		final boolean moved = (finalCol - initCol == 1);
		assertTrue("Bullet should move sqaure to the right by 1", moved);
	}

}

