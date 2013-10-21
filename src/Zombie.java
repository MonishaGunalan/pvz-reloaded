
public class Zombie {
	private int turn;
	private int col;
	private int row;
	

	public Zombie(int turn, int row, String type){
		this.row = row;
		this.col = (Integer) null;
	}
	
	
	public int getTurn(){
		return turn;
	}
	
	public int getRow(){
		return row;
	}
	
	public int getCol(){
		return row;
	}
}
