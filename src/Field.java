import java.util.Set;

public class Field {

	public static final int DEFAULT_MAX_ROW = 5;
	public static final int DEFAULT_MAX_POSN = 5;

	public enum Direction { LEFT, RIGHT; }
	private Strip[] strips;

	// private Set<TurnBasedElements> elements;

	public Field(String[] terrainType) {
		strips = new Strip[DEFAULT_MAX_ROW];
		for (int i = 0; i < DEFAULT_MAX_ROW; i++) {
			strips[i] = new Strip(terrainType[i], i);
		}
	}

	public Strip[] getStrip() {
		return strips;
	}

	public void moveBulletForward(Bullet bullet) {
		int col = bullet.getSquare().getCol();
		int row = bullet.getSquare().getRow();
		if (row < DEFAULT_MAX_ROW && col < DEFAULT_MAX_POSN - 1) {
			strips[row].getSquare()[col].removeBullet(bullet);
			strips[row].getSquare()[col + 1].addBullet(bullet);
			bullet.setSquare(this.getNextSquare(bullet.getSquare()));
		}
	}

	public void moveZombieForward(Zombie zombie) {
		int row = zombie.getSquare().getRow();
		int col = zombie.getSquare().getCol();
		if (row < DEFAULT_MAX_ROW && col < DEFAULT_MAX_POSN && col > 0) {
			strips[row].getSquare()[col].removeZombie(zombie);
			strips[row].getSquare()[col - 1].addZombie(zombie);
			zombie.setSquare(this.getNextSquare(zombie.getSquare()));
		}
	}

	public void seedPlant(Plant plant) {
		int row = plant.getRow();
		int col = plant.getCol();

		if (row < DEFAULT_MAX_ROW && col < DEFAULT_MAX_POSN) {
			strips[row].getSquare()[col].addPlant(plant);
		}
	}

	public Square getNextSquare(Square square) {
		Square nextSquare = null;
		int row = square.getRow();
		int col = square.getCol();
		if ( col < DEFAULT_MAX_POSN - 1) {
			nextSquare =  strips[row].getSquare()[col+1];
		}
		return nextSquare;
	}
	
	public Square getPrevSquare(Square square) {
		Square prevSquare = null;
		int row = square.getRow();
		int col = square.getCol();
		if (col > 0) {
			prevSquare =  strips[row].getSquare()[col-1];
		}
		return prevSquare;
	}
	
	public int getTotalSun(){
		//yet to be filled.
		return 0;
	}
	
	public String toString(){
		String s = "";
		for (int i = 0; i < Field.DEFAULT_MAX_ROW; i++) {
			s += strips[i].toString();
		}
		s += "/n";
		return s;
	}

}
