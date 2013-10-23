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
	private String terrain;

	public Square(String terrain, int row, int col, Strip strip) {
		this.row = row;
		this.col = col;
		zombies = new ArrayList<Zombie>();
		bullets = new ArrayList<Bullet>();
		this.plant = null;
		this.numZombie = 0;
		this.numBullet = 0;
		this.strip = strip;
		this.terrain = terrain;
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
		Square s = null;
		switch (dir) {
		case LEFT:
			s = this.strip.getSquare(col - 1);
			break;
		case RIGHT:
			s = this.strip.getSquare(col + 1);
			break;
		}

		return s;
	}

	public void add(Unit unit) {
		if (unit instanceof Plant) {
			remove((Plant) unit);
		} else if (unit instanceof Bullet) {
			remove((Bullet) unit);
		} else if (unit instanceof Zombie) {
			remove((Zombie) unit);
		}
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

	public boolean remove(Unit p) {
		if (p instanceof Zombie) {
			return remove((Zombie) p);
		} else if (p instanceof Plant) {
			return remove((Plant) p);
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
		if (this.terrain.equals("mud")) {
			s += '-';
		}

		if (this.hasPlant()) {
			s += 'p';
		} else {
			s += ' ';
		}

		if (this.hasZombie()) {
			s += 'z';
		} else {
			s += ' ';
		}

		if (this.hasBullet()) {
			s += '>';
		} else {
			s += ' ';
		}

		s += ']';

		return s;
	}
}
