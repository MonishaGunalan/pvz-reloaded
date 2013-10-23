public class Strip {
	private int fieldRow;
	private Square[] squares;
	private String terrain;
	private Field field;

	/*
	 * private enum terrainType { POOL('P'), LAWN('L'); private char value;
	 * 
	 * terrainType(char value) { this.value = value; } };
	 */

	public Strip(String terrain, int fieldRow, Field field) {
		this.fieldRow = fieldRow;
		this.terrain = terrain;
		this.field = field;
		squares = new Square[Field.DEFAULT_MAX_POSN];
		for (int j = 0; j < Field.DEFAULT_MAX_POSN; j++) {
			squares[j] = new Square(terrain, fieldRow, j, this);
		}

	}

	public Square[] getSquares() {
		return squares;
	}

	public Square getSquares(int posn) {
		return squares[posn];
	}

	public String toString() {
		String s = "";
		for (int j = 0; j < Field.DEFAULT_MAX_POSN; j++) {
			s += squares[j].toString();
		}
		s += " ";
		return s;
	}

}
