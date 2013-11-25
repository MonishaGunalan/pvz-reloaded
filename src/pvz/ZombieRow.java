package pvz;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The class contains a list of ZombieTurn objects which has the zombies and its
 * turn number at which it has to spawn in the field
 * 
 * @author Monisha Gunalan 100871444
 */

public class ZombieRow {

	/**
	 * The list of ZombieTurns in in this row
	 */
	private ArrayList<ZombieTurn> zombiesInRow;

	/**
	 * Constructor instantiate a new arraylist to store the Zombies and its
	 * turns
	 * 
	 */
	public ZombieRow() {
		zombiesInRow = new ArrayList<ZombieTurn>();
	}

	/**
	 * Add a zombieTurn to the list
	 * 
	 * @param ZombieTurn
	 */
	public void add(ZombieTurn z) {
		zombiesInRow.add(z);
	}

	/**
	 * Remove a zombieTurn from the list
	 * 
	 * @param ZombieTurn
	 */
	public void remove(ZombieTurn z) {
		zombiesInRow.remove(z);
	}

	/**
	 * Returns the size of the list containing zombieTurn
	 * 
	 * @return return the size of the list
	 */
	public int getSize() {
		return zombiesInRow.size();
	}

	/**
	 * Checks if the list is empty
	 * 
	 * @return true if the list is empty false otherwise
	 */
	public boolean isEmpty() {
		return (this.getSize() == 0);
	}

	/**
	 * Returns the zombie which has the specifie turn number and removes it from
	 * the list
	 * 
	 * @param turnNumber
	 *            The turn number of the zombie at which it is spawned
	 * @return return the zombie which has the requested turnumber
	 */
	public Zombie getZombie(int turnNumber) {
		Zombie zombie = null;
		Iterator<ZombieTurn> it = zombiesInRow.iterator();
		while (it.hasNext()) {
			ZombieTurn zt = it.next();
			if (zt.getTurn() == turnNumber) {
				zombie = zt.getZombie();
				System.out.println("Zombie: " + zombie.getType() + "  turn = "
						+ zt.getTurn());
				it.remove();
				return zombie;
			}
		}
		return null;
	}

}
