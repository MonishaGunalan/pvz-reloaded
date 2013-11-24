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
@SuiteClasses({ PlayerTest.class, NormalZombieTest.class,
		PerishableUnitTest.class, ZombieTest.class, UnitTest.class,
		SunflowerPlantTest.class, PeaShooterPlantTest.class,
		ZombieFactoryTest.class, BulletFactoryTest.class,
		SunGeneratorTest.class, PlantFactoryTest.class, LevelTest.class,
		PlayerCommandTest.class, FieldTest.class, StripTest.class,
		SquareTest.class, BulletTest.class, PeaBulletTest.class,
		CooldownTest.class, GeneratorPlantTest.class })
public class AllTests {

}
