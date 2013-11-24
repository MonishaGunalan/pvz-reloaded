package pvz;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

/**
 * This class implements the square. The square can contain, plants, zombies and bullets
 * @author Monisha Gunalan
 * 100871444
 */
public class Square
	implements Serializable {
	/**
	 * Serialization UID
	 * Do not change unless serialization with previous versions become
	 * incompatible
	 */
	static final long serialVersionUID = 1049118517451004034L;

	/**
	 * The plant located in the square
	 */
	private Plant plant;
	/**
	 * The list of zombies that inhibit the square
	 */
	private List<Zombie> zombies;
	/**
	 * The list of bullets located on the square
	 */
	private List<Bullet> bullets;
	/**
	 * The strip the square is currently on
	 */
	private Strip strip;
	/**
	 * The row the square is located
	 */
	private int row;
	/**
	 * The column the square is located
	 */
	private int col;
	/**
	 * The number of zombies on the square
	 */
	private int numZombie;
	/**
	 * The number of bullets on the square
	 */
	private int numBullet;
	/**
	 * The terrain of the square
	 */
	private String terrain;

	/**
	 * constructor: can contain zombies, plant and bullets
	 * 
	 * @param terrain
	 *            terrian type of the field
	 * 
	 * @param row
	 *            the position in the field row
	 * 
	 * @param col
	 *            position in the firld column
	 * 
	 * @param strip
	 *            the strip which contains this square
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

	/**
	 * @return the position of the square in field row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @return the position of the square in field column
	 */
	public int getCol() {
		return col;
	}

	/**
	 * @return the plant present in this square
	 */
	public Plant getPlant() {
		return plant;
	}

	/**
	 * @return the first zombie in the square
	 */
	public Zombie getFirstZombie() {
		if (zombies.size() > 0) {
			return zombies.get(0);
		}
		return null;
	}

	/**
	 * @param dir
	 *            direction of the unit present in the square
	 * 
	 * @return returns the nearest square in the specified direction
	 */
	public Square getSquare(Field.Direction dir) {
		Square s = null;
		switch (dir) {
		case LEFT:
			if (col - 1 >= 0)
				s = this.strip.getSquare(col - 1);
			break;
		case RIGHT:
			if (col + 1 < Field.DEFAULT_MAX_POSN)
				s = this.strip.getSquare(col + 1);
			break;
		}

		return s;
	}

	/**
	 * add the specified unit to the square
	 * 
	 * @param unit
	 *            is the element such as zombie, plant bullet
	 */
	public void add(Unit unit) {
		if (unit instanceof Plant) {
			add((Plant) unit);
		} else if (unit instanceof Bullet) {
			add((Bullet) unit);
		} else if (unit instanceof Zombie) {
			add((Zombie) unit);
		}
	}

	/**
	 * Adds the plant to the square
	 * 
	 * @plant the plant
	 */
	public void add(Plant plant) {
		// System.out.println("Add plant");
		if (!this.hasPlant()) {
			this.plant = plant;
		}
	}

	/**
	 * Adds the bullet to the square
	 * 
	 * @bullet the bullet
	 */
	public void add(Bullet bullet) {
		// System.out.println("adding bullet @ " + getRow() + "," + getCol());
		bullets.add(bullet);
		this.numBullet++;
	}

	/**
	 * Adds the zombie to the square
	 * 
	 * @zombie the zombie
	 */
	public void add(Zombie zombie) {
		zombies.add(zombie);
		// Make sure level is observing zombie so it knows if zombie
		// reaches end of strip
		zombie.addObserver(getStrip().getField().getLevel());
		// System.out.println("adding zombie @ " + getRow() + "," + getCol());
		this.numZombie++;
	}

	/**
	 * removes the unit from the square
	 * 
	 * @param p
	 *            the unit element
	 * 
	 * @return returns true when the unit can be removed
	 */
	public boolean remove(Unit unit) {

		if (unit instanceof Zombie) {
			return remove((Zombie) unit);
		} else if (unit instanceof Plant) {
			return remove((Plant) unit);
		} else if (unit instanceof Bullet) {
			return remove((Bullet) unit);
		}

		// If code reaches here, p is not a valid PerishableUnit
		return false;
	}

	/**
	 * removes the zombie from the square
	 * 
	 * @param z
	 *            zombie
	 * 
	 * @return returns true when the unit can be removed
	 */
	public boolean remove(Zombie z) {
		// System.out.println("removing zombie @ " + getRow() + "," + getCol());
		numZombie--;
		return zombies.remove(z);
	}

	/**
	 * removes the bullet from the square
	 * 
	 * @param b
	 *            bullet
	 * 
	 * @return returns true when the unit can be removed
	 */
	public boolean remove(Bullet b) {
		// System.out.println("removing bullet @ " + getRow() + "," + getCol());
		if (hasBullet()) {
			numBullet--;
			return bullets.remove(b);
		}
		return false;
	}

	/**
	 * removes the plant from the square
	 * 
	 * @param p
	 *            plant
	 * 
	 * @return returns true when the unit can be removed
	 */
	public boolean remove(Plant p) {
		this.plant = null;
		return true;
	}

	/**
	 * check if there is a zombie after it
	 * 
	 * @return true if there exits a zombie
	 */
	public boolean hasZombieAfter() {
		// System.out.println("Checking for zombie after plant..");
		int currentPosn = this.getCol();
		boolean hasZombie = false;
		for (int i = currentPosn; i < Field.DEFAULT_MAX_POSN; i++) {
			hasZombie |= strip.getSquare(i).hasZombie();
		}

		return hasZombie;
	}

	/**
	 * @return returns true if the square contains a zombie
	 */
	public boolean hasZombie() {
		return (zombies.size() > 0);
	}

	/**
	 * @return returns true if the square contains aplant
	 */
	public boolean hasPlant() {
		return this.plant != null;
	}

	/**
	 * @return returns true if the square contains a bullet
	 */
	public boolean hasBullet() {
		return bullets.size() > 0;
	}

	/**
	 * 
	 * @return returns a list of Zombies in the square
	 */
	public List<Zombie> getZombies() {
		return zombies;
	}

	/**
	 * 
	 * @return returns a list of Bullets in the square
	 */
	public List<Bullet> getBullets() {
		return bullets;
	}

	/**
	 * @return returns a toString representation of the square
	 */
	public String toString() {
		String s = "[";
		if (this.terrain.equals("mud")) {
			s += "-";
		}

		if (this.hasPlant()) {
			s += "p";
		} else {
			s += " ";
		}

		if (this.hasBullet()) {
			s += ">" + numBullet;
		} else {
			s += "  ";
		}

		if (this.hasZombie()) {
			s += "z" + numZombie;
		} else {
			s += "  ";
		}

		s += "]";

		return s;
	}

	/**
	 * @return returns the location of the square
	 */
	public String getLoc() {
		String s = "";
		s += "Square@" + getRow() + "," + getCol();
		return s;
	}

	/**
	 * @return returns the strip in which the square is present
	 */
	public Strip getStrip() {
		return this.strip;
	}
	
	/**
	 * Evaluates a specific zombie after it has been brought into the square
	 * either by  move or  by spawn
	 * @param z
	 */
	public void evaluateZombie(Zombie z){
		List<Bullet> bulletList = new ArrayList<Bullet>(this.bullets);
		for (Bullet b: bulletList){
			b.hit(z);
			if (z.getCurrentHP() <= 0){
				break;
			}
		}
	}
}
