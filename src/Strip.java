public class Strip {
	private int fieldRow;
	private Square[] squares;
	private String terrain;

	/*
	private enum terrainType {
		POOL('P'), LAWN('L');
		private char value;

		terrainType(char value) {
			this.value = value;
		}
	};
	*/

	public Strip(String terrain, int fieldRow) {
		this.fieldRow = fieldRow;
		this.terrain = terrain;

		squares = new Square[Field.DEFAULT_MAX_POSN];
		for (int j = 0; j < Field.DEFAULT_MAX_POSN; j++) {
			squares[j] = new Square(terrain, fieldRow, j);
		}

	}
	
	public Square[] getSquare(){
		return squares;
	}
	
	public String toString(){
		String s = "";
		for (int j = 0; j < Field.DEFAULT_MAX_POSN; j++) {
			s += squares[j].toString();
		}
		s += " ";
		return s;
	}
	
	
	

}