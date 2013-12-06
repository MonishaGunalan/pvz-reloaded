package pvz.level;
import java.io.Serializable;

import pvz.unit.Zombie;
/**
 * The class represents the object which has the zombie and its turn to spawn on
 * the field
 * 
 * @author Monisha Gunalan 100871444
 */

public class ZombieTurn implements Serializable{
	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Turn number of the Zombie at which it should spawn
	 */
	private int turn;
	
	/**
	 * the zombie that is to be spawned
	 */
	private Zombie zombie;

	/**
	 * Constructor instantiates an object with zombie and its turn
	 * @param zombie
	 * @param turn
	 */
	public ZombieTurn(Zombie zombie, int turn) {
		this.zombie = zombie;
		this.turn = turn;
	}
	
	/**
	 * 
	 * @return the turn of the zombie
	 */

	public int getTurn() {
		return turn;
	}

	/**
	 * @ return the  zombie
	 */
	public Zombie getZombie() {
		return zombie;
	}

}
