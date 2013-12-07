package pvz.level;


/**
 * This class implements the strip contained in the field Strips contains a list
 * of squares. Strips represent the rows of the field
 * 
 * @author Monisha Gunalan 100871444 / Arzaan Irani 100826631
 */
import java.io.Serializable;

public class Strip
	implements Serializable {
	/**
	 * Serialization UID
	 * Do not change unless serialization with previous versions become
	 * incompatible
	 */
	static final long serialVersionUID = -7228675675509561302L;

	/**
	 * The row the field is located (used for moving up and down squares)
	 */
	private int fieldRow;
	/**
	 * The squares in the strip
	 */
	private Square[] squares;
	
	/**
	 * The field it is located in
	 */
	private Field field;
	/**
	 * The possible Terrains
	 */
	public enum Terrain {
		MUD, GRASS;
	}
	
	/**
	 * Terrains
	 */
	private Terrain terrain;
	
	
	/**
	 * Constructor - creates squares which are contained in a strip
	 * 
	 * @param terrain
	 *            the  enum type of terrain
	 * 
	 * @param fieldRow
	 *            the index of the strip in a field
	 * 
	 * @param field
	 *            the field which contains the strip
	 */
	public Strip(String terrain, int fieldRow, Field field) {
		this.fieldRow = fieldRow;
		this.terrain = Terrain.valueOf(terrain.toUpperCase());
		this.field = field;
		squares = new Square[Field.DEFAULT_MAX_POSN];
		for (int j = 0; j < Field.DEFAULT_MAX_POSN; j++) {
			squares[j] = new Square(fieldRow, j, this);
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
	 * Returns the list of squares that is this strip
	 * @return list of squares contained in this strip
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

	/**
	 * Gets the Terrain
	 * @return The type of terrain for this strip
	 */
	public Terrain getTerrain() {
		return this.terrain;
	}

}
