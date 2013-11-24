package pvz;


/**
 * This class implements the strip contained in the field Strips contains a list
 * of squares. Strips represent the rows of the field
 * 
 * @author Monisha Gunalan 100871444
 */

public class Strip {
	/**
	 * The row the field is located (used for moving up and down squares)
	 */
	private int fieldRow;
	/**
	 * The squares in the strip
	 */
	private Square[] squares;
	/**
	 * The terrain type of the strip
	 */
	private String terrain;
	/**
	 * The field it is located in
	 */
	private Field field;

	/**
	 * Constructor - creates squares which are contained in a strip
	 * 
	 * @param terrain
	 *            the terrain type of the field
	 * 
	 * @param fieldRow
	 *            the index of the strip in a field
	 * 
	 * @param field
	 *            the field which contains the strip
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

	/**
	 * 
	 * @return return the field that contains this strip
	 */
	public Field getField() {
		return this.field;
	}

	/**
	 * @returns list of squares contained in this strip
	 */
	public Square[] getSquares() {
		return squares;
	}

	/**
	 * @param posn
	 *            position of the square in the strip
	 * 
	 * @return a square in the requested position
	 */
	public Square getSquare(int posn){
		try{
			return squares[posn];
		}catch(ArrayIndexOutOfBoundsException ex) {
			System.out.println("The requested position is  outside the field");
			return null;
		}
	}

	/**
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
