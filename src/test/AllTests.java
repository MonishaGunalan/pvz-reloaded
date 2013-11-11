package test;

/*
 * Runner for use with JUnit.  All the test classes are run together using this class
 * @author Monisha Gunalan
 * 100871444
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import pvz.BulletFactory;
import pvz.NormalZombie;
import pvz.PeaShooterPlant;
import pvz.PerishableUnit;
import pvz.SunGenerator;
import pvz.SunflowerPlant;
import pvz.Unit;
import pvz.Zombie;
import pvz.ZombieFactory;

@RunWith(Suite.class)
@SuiteClasses({ PlayerTest.class, NormalZombie.class, PerishableUnit.class,
		Zombie.class, Unit.class, SunflowerPlant.class, PeaShooterPlant.class,
		ZombieFactory.class, BulletFactory.class, SunGenerator.class,
		PlantFactoryTest.class, LevelTest.class, PlayerCommandTest.class,
		FieldTest.class, StripTest.class, SquareTest.class })
public class AllTests {

}
