package pvz;

import java.util.ArrayList;
import java.util.Iterator;

public class ZombieRow {
	private ArrayList<ZombieTurn> zombiesInRow;

	public ZombieRow() {
		zombiesInRow = new ArrayList<ZombieTurn>();
	}

	public void add(ZombieTurn z) {
		zombiesInRow.add(z);
	}

	public void remove(ZombieTurn z) {
		zombiesInRow.remove(z);
	}

	public int getSize() {
		return zombiesInRow.size();
	}

	public boolean isEmpty() {
		return (this.getSize() == 0);
	}

	public Zombie getZombie(int turnNumber) {
		Zombie zombie = null;
		Iterator<ZombieTurn> it = zombiesInRow.iterator();
		while (it.hasNext()) {
			ZombieTurn zt = it.next();
			if (zt.getTurn() == turnNumber) {
				zombie = zt.getZombie();
				System.out.println("Zombie: "+zombie.getType() + "  turn = "+ zt.getTurn());
				it.remove();
				return zombie;
			}
		}
		return null;
	}

}
