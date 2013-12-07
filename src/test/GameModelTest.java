package test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pvz.level.*;
public class GameModelTest {
	GameModel model;
	java.util.Observable o;

	@Before
	public void setUp() {
		model = new GameModel(false);
	}

	@Test
	public void getPlayer() {
		assertTrue(model.getPlayer() instanceof Player);
	}

	@Test
	public void getLevel() {
		assertTrue(model.getLevel() instanceof Level);
	}

	@Test
	public void writeHistoryOnce() {
		// turn initiates to 0
		try {
			model.writeHistory();
		} catch(java.io.NotSerializableException nse) {
			fail("Could not serialize the level");
		}
		model.getLevel().incrementTurn();
		// increment turn to 1
		assertTrue(1 == model.getLevel().getTurnNumber());
		assertTrue(model.undo());
		// after undo, turn should be 0
		assertTrue(0 == model.getLevel().getTurnNumber());
	}

	@Test
	public void writeHistorySeveralTimes() {
		// Get levels and increment
		// turn initiates to 0
		try {
			model.writeHistory();
		} catch(java.io.NotSerializableException nse) {
			fail("Could not serialize the level");
		}
		model.getLevel().incrementTurn();
		// after increment, turn = 1
		try {
			model.writeHistory();
		} catch(java.io.NotSerializableException nse) {
			fail("Could not serialize the level");
		}
		model.getLevel().incrementTurn();
		// after increment, turn = 2
		try {
			model.writeHistory();
		} catch(java.io.NotSerializableException nse) {
			fail("Could not serialize the level");
		}
		model.getLevel().incrementTurn();
		// after increment, turn = 3
		// Undos
		assertTrue(model.undo());
		assertTrue(2 == model.getLevel().getTurnNumber());
		assertTrue(model.undo());
		assertTrue(1 == model.getLevel().getTurnNumber());
		assertTrue(model.undo());
		assertTrue(0 == model.getLevel().getTurnNumber());
		// Undo stack should be empty
		assertFalse(model.undo());
		// Redos
		assertTrue(model.redo());
		assertTrue(1 == model.getLevel().getTurnNumber());
		assertTrue(model.redo());
		assertTrue(2 == model.getLevel().getTurnNumber());
		assertTrue(model.redo());
		assertTrue(3 == model.getLevel().getTurnNumber());
		// Redo stack should be empty
		assertFalse(model.redo());
	}

	@Test
	public void undoWithEmptyUndoStack() {
		assertFalse(model.redo());
	}

	@Test
	public void redoWithEmptyRedoStack() {
		assertFalse(model.redo());
	}

	@Test
	public void loadLevelValidLevel() {
	}
	
	@Test
	public void testRealTime(){
		GameModel m1 = new GameModel(true);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(m1.getLevel().getTurnNumber()==1);
	}
	@Test
	public void testStopTimer(){
		GameModel m1 = new GameModel(true);
		m1.stopTimer();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(m1.getLevel().getTurnNumber()==0);
	}
	
	@Test
	public void testStartTime(){
		GameModel m1 = new GameModel(true);
		m1.stopTimer();
		m1.startTimer();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(m1.getLevel().getTurnNumber()==1);

	@Test
	public void saveAndLoad() {
		int currentTurn = model.getLevel().getTurnNumber();
		model.save();
		for (int i=0; i<5; i++) {
			model.getLevel().incrementTurn();
		}
		model.load();
		assertTrue(model.getLevel().getTurnNumber() == currentTurn);
	}
}
