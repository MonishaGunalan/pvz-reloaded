import java.util.ArrayList;
import java.util.List;

public class Square {

	private Plant plant;
	private List<Zombie> zombies;
	private List<Bullet> bullets;
	private Strip strip;
	private int row;
	private int col;
	private int numZombie;
	private int numBullet;

	public Square(String terrain, int row, int col, Strip strip) {
		this.row = row;
		this.col = col;
		zombies = new ArrayList<Zombie>();
		bullets = new ArrayList<Bullet>();
		this.plant = null;
		this.numZombie = 0;
		this.numBullet = 0;
		this.strip = strip;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public Plant getPlant() {
		return plant;
	}

	public Square getSquare(Field.Direction dir) {
		Square s;
		switch (dir) {
			case Field.Direction.LEFT: s = this.strip.getSquares(col-1); break;
			case Field.Direction.RIGHT: s = this.strip.getSquares(col+1); break;
		}

		return s;
	}

	public void add(Plant plant) {
		this.plant = plant;
	}

	public void add(Bullet bullet) {
		bullets.add(bullet);
		this.numBullet++;
	}

	public void add(Zombie zombie) {
		zombies.add(zombie);
		this.numZombie++;
	}

	public boolean remove(PerishableUnit p) {
		if (p instanceof Zombie) {
			return remove((Zombie)p);
		} else if (p instanceof Plant) {
			return remove((Plant)p);
		}

		// If code reaches here, p is not a valid PerishableUnit
		return false;
	}

	public boolean remove(Zombie z) {
		numZombie--;
		return zombies.remove(z);
	}

	public boolean remove(Bullet b) {
		numZombie--;
		return zombies.remove(b);
	}

	public boolean remove(Plant p) {
		numZombie--;
		return zombies.remove(p);
	}

	public boolean hasZombie() {
		if (zombies.size() > 0) {
			return true;
		}
		return false;
	}

	public boolean hasPlant() {
		if (this.plant != null) {
			return true;
		}
		return false;
	}

	public boolean hasBullet() {
		if (bullets.size() > 0) {
			return true;
		}
		return false;
	}

	public String toString() {
		String s = "[";
		if (this.hasPlant()) {
			s += 'p';
		}else{
			s += ' ';
		}
		if (this.hasZombie()) {
			s += 'z';
		}else{
			s += ' ';
		}
		if (this.hasBullet()) {
			s += '>';
		}else{
			s += ' ';
		}
		s += ']';
		return s;
	}
}
