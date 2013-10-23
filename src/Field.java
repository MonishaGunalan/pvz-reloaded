import java.util.ArrayList;
import java.util.List;

public class Field {

	public static final int DEFAULT_MAX_ROW = 5;
	public static final int DEFAULT_MAX_POSN = 5;
	private List<Unit> units;

	public enum Direction {
		LEFT, RIGHT;
	}

	private Strip[] strips;

	public Field(String[] terrainType) {
		strips = new Strip[DEFAULT_MAX_ROW];
		for (int i = 0; i < DEFAULT_MAX_ROW; i++) {
			strips[i] = new Strip(terrainType[i], i, this);
		}
		units = new ArrayList<Unit>();
	}

	public Strip[] getStrip() {
		return strips;
	}

	public void seedPlant(Plant plant) {
		int row = plant.getRow();
		int col = plant.getCol();

		if (row < DEFAULT_MAX_ROW && col < DEFAULT_MAX_POSN) {
			strips[row].getSquares()[col].add(plant);
		}
	}

	public Square getNextSquare(Square square) {
		Square nextSquare = null;
		int row = square.getRow();
		int col = square.getCol();
		if (col < DEFAULT_MAX_POSN - 1) {
			nextSquare = strips[row].getSquares()[col + 1];
		}
		return nextSquare;
	}

	public Square getPrevSquare(Square square) {
		Square prevSquare = null;
		int row = square.getRow();
		int col = square.getCol();
		if (col > 0) {
			prevSquare = strips[row].getSquares()[col - 1];
		}
		return prevSquare;
	}

	public int getTotalSun() {
		// yet to be filled.
		return 0;
	}

	public String toString() {
		String s = "";
		for (int i = 0; i < Field.DEFAULT_MAX_ROW; i++) {
			s += strips[i].toString();
		}
		s += "/n";
		return s;
	}

	public void addToUnitList(Unit unit) {
		units.add(unit);
	}

}
