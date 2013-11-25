package pvz;

public class ZombieTurn {
	private int turn;
	private Zombie zombie;

	public ZombieTurn(Zombie zombie, int turn) {
		this.zombie = zombie;
		this.turn = turn;
	}

	public int getTurn() {
		return turn;
	}

	public Zombie getZombie() {
		return zombie;
	}

}
