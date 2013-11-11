/*
 * Runner for use with JUnit.  All the test classes are run together using this class
 * @author Monisha Gunalan
 * 100871444
 */

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests extends TestSuite {
	
	/*
	 * runs all the test classes include in the suite
	 */
	public static void main(String[] args) {
		junit.textui.TestRunner.run(AllTests.suite());
	}

	/*
	 * create a new Suite and add the test classes 
	 */
	public static Test suite() {
		TestSuite suite = new TestSuite("Tests for Plants vs Zombies Game");
		suite.addTest(new TestSuite(TestStrip.class));
		suite.addTest(new TestSuite(TestSquare.class));
		suite.addTest(new TestSuite(TestField.class));
		return suite;
	}
}