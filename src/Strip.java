/*
 * @author Monisha Gunalan
 * 100871444
 */

public class Strip {
	private int fieldRow;
	private Square[] squares;
	private String terrain;
	private Field field;
	/*
	 * Constructor - creates squares which are contained in a strip
	 * @param terrian the terraintype of the field
	 * @param fieldRow the index of the strip in a field
	 * @param field the field which contains the strip
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

	/*
	 * @returns  list of squares contained in this strip
	 */
	public Square[] getSquares() {
		return squares;
	}
	
	/*
	 * @param posn position of the square in the strip
	 * @return a square in the requested position
	 */
	public Square getSquare(int posn) {
		return squares[posn];
	}

	/*
	 * @return returns a string represention of the strip
	 */
	public String toString() {
		String s = "";
		for (int j = 0; j < Field.DEFAULT_MAX_POSN; j++) {
			s += squares[j].toString();
		}
		s += " \n";
		return s;
	}

}
