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

	/*
	 * constructor: can contain zombies, plant and bullets
	 * 
	 * @param terrain  terrian type of the field 
	 * @param row the position in the field row
	 * @param col position in the firld column
	 * @param strip the strip which contains this square
	 */
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

	/*
	 * @return the position of the square in field row
	 */
	public int getRow() {
		return row;
	}

	/*
	 * @return the position of the square in field column
	 */
	public int getCol() {
		return col;
	}

	/*
	 * @return the plant present in this square
	 */
	public Plant getPlant() {
		return plant;
	}

	/*
	 * @return the first zombie in the square
	 */
	public Zombie getFirstZombie() {
		return zombies.get(0);
	}

	/*
	 * @param dir direction of the unit present in the square
	 * @return returns the nearest square in the specified direction
	 */
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

	/*
	 * add the specified unit to the square
	 * @param unit is the element such as zombie, plant bullet
	 */
	public void add(Unit unit) {
		if (unit instanceof Plant) {
			remove((Plant) unit);
		} else if (unit instanceof Bullet) {
			remove((Bullet) unit);
		} else if (unit instanceof Zombie) {
			remove((Zombie) unit);
		}
	}

	/*
	 * Adds the plant to the square
	 * @plant the plant
	 */
	public void add(Plant plant) {
		this.plant = plant;
	}

	/*
	 * Adds the bullet to the square
	 * @bullet the bullet
	 */
	public void add(Bullet bullet) {
		bullets.add(bullet);
		this.numBullet++;
	}

	/*
	 * Adds the zombie to the square
	 * @zombie the zombie
	 */
	public void add(Zombie zombie) {
		zombies.add(zombie);
		this.numZombie++;
	}

	/*
	 * removes the unit from the square
	 * @param p the unit element
	 * @return returns true when the unit can be removed
	 * 
	 */
	public boolean remove(Unit p) {
		if (p instanceof Zombie) {
			return remove((Zombie) p);
		} else if (p instanceof Plant) {
			return remove((Plant) p);
		}

		// If code reaches here, p is not a valid PerishableUnit
		return false;
	}
	
	/*
	 * removes the zombie from the square
	 * @param z zombie  
	 * @return returns true when the unit can be removed
	 * 
	 */
	public boolean remove(Zombie z) {
		numZombie--;
		return zombies.remove(z);
	}

	/*
	 * removes the bullet from the square
	 * @param b bullet
	 * @return returns true when the unit can be removed
	 * 
	 */
	public boolean remove(Bullet b) {
		numZombie--;
		return zombies.remove(b);
	}

	/*
	 * removes the plant from the square
	 * @param p plant  
	 * @return returns true when the unit can be removed
	 * 
	 */
	public boolean remove(Plant p) {
		numZombie--;
		return zombies.remove(p);
	}
	/*
	 * check if there is a zombie after it
	 * 
	 * @return true if there exits a zombie
	 */
	public boolean hasZombieAfter() {
		int currentPosn = this.getCol();
		boolean hasZombie = false;
		for (int i=currentPosn; i<Field.DEFAULT_MAX_ROW; i++) {
			hasZombie |= strip.getSquare(i).hasZombie();
		}

		return hasZombie;
	}
	
	/*
	 * @return returns true if the square contains a zombie
	 */
	public boolean hasZombie() {
		if (zombies.size() > 0) {
			return true;
		}
		return false;
	}
	
	/*
	 * @return returns true if the square contains aplant
	 */
	public boolean hasPlant() {
		if (this.plant != null) {
			return true;
		}
		return false;
	}
	
	/*
	 * @return returns true if the square contains a bullet
	 */
	public boolean hasBullet() {
		if (bullets.size() > 0) {
			return true;
		}
		return false;
	}

	public List<Zombie> getZombies() {
		return zombies;
	}

	public List<Bullet> getBullets() {
		return bullets;
	}

	/*
	 * @return returns a toString representation of the square
	 */

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
