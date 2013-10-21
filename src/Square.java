import java.util.ArrayList;
import java.util.List;

public class Square {

	private Plant plant;
	private List<Zombie> zombies;
	private List<Bullet> bullets;
	private int row;
	private int col;
	private int numZombie;
	private int numBullet;

	public Square(String terrain, int row, int col) {
		this.row = row;
		this.col = col;
		zombies = new ArrayList<Zombie>();
		bullets = new ArrayList<Bullet>();
		this.plant = null;
		this.numZombie = 0;
		this.numBullet = 0;
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

	public void addPlant(Plant plant) {
		this.plant = plant;
	}

	public void addBullet(Bullet bullet) {
		bullets.add(bullet);
		this.numBullet++;
	}

	public void addZombie(Zombie zombie) {
		zombies.add(zombie);
		this.numZombie++;
	}

	public boolean removeZombie(Zombie z) {
		numZombie--;
		return zombies.remove(z);
	}

	public boolean removeBullet(Bullet b) {
		numZombie--;
		return zombies.remove(b);
	}

	public boolean removePlant(Plant p) {
		numZombie--;
		return zombies.remove(p);
	}

}
