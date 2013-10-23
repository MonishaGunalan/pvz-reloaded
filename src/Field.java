import java.util.ArrayList;
import java.util.List;

public class Field {

	public static final int DEFAULT_MAX_ROW = 5;
	public static final int DEFAULT_MAX_POSN = 5;
	public static final int SUN_GENERATION_PERIOD=3;
	public static final int SUN_GENERATION_VALUE=25;
	private List<Unit> units;
	private Cooldown sunGenerationCooldown;

	public enum Direction {
		LEFT, RIGHT;
	}

	private Strip[] strips;

	// private Set<TurnBasedElements> elements;

	public Field(String[] terrainType) {
		strips = new Strip[DEFAULT_MAX_ROW];
		for (int i = 0; i < DEFAULT_MAX_ROW; i++) {
			strips[i] = new Strip(terrainType[i], i, this);
		}
		units = new ArrayList<Unit>();
		sunGenerationCooldown = new Cooldown(SUN_GENERATION_PERIOD);
	}

	public Strip[] getStrip() {
		return strips;
	}

	// DEPRECATED
	// public void moveBulletForward(Bullet bullet) {
	// int col = bullet.getSquares().getCol();
	// int row = bullet.getSquares().getRow();
	// if (row < DEFAULT_MAX_ROW && col < DEFAULT_MAX_POSN - 1) {
	// strips[row].getSquares()[col].removeBullet(bullet);
	// strips[row].getSquares()[col + 1].add(bullet);
	// bullet.setSquare(this.getNextSquare(bullet.getSquares()));
	// }
	// }

	// public void moveZombieForward(Zombie zombie) {
	// int row = zombie.getSquares().getRow();
	// int col = zombie.getSquares().getCol();
	// if (row < DEFAULT_MAX_ROW && col < DEFAULT_MAX_POSN && col > 0) {
	// strips[row].getSquares()[col].removeZombie(zombie);
	// strips[row].getSquares()[col - 1].add(zombie);
	// zombie.setSquare(this.getNextSquare(zombie.getSquares()));
	// }
	// }

	public void seedPlant(Plant plant) {
		int row = plant.getRow();
		int col = plant.getCol();

		if (row < DEFAULT_MAX_ROW && col < DEFAULT_MAX_POSN) {
			strips[row].getSquares()[col].add(plant);
		}
	}

	public Square getNextSquare(Square square) {
		Square nextSquare = null;
		int row = square.getRow();
		int col = square.getCol();
		if (col < DEFAULT_MAX_POSN - 1) {
			nextSquare = strips[row].getSquares()[col + 1];
		}
		return nextSquare;
	}

	public Square getPrevSquare(Square square) {
		Square prevSquare = null;
		int row = square.getRow();
		int col = square.getCol();
		if (col > 0) {
			prevSquare = strips[row].getSquares()[col - 1];
		}
		return prevSquare;
	}

	public int getTotalSun() {
		// yet to be filled.
		int sun = 0;
		if (sunGenerationCooldown.isAvailable()){
			sun += SUN_GENERATION_VALUE;
			sunGenerationCooldown.trigger();
		}

		for (Strip strip: strips){
			for (Square square: strip.getSquares()){
				if (square.getPlant() instanceof GeneratorPlant){
					//TODO:: wait for implementation of generatorPlant
					//(Generator)
				}
			}
		}
		return sun;
	}
	
	

	public String toString() {
		String s = "";
		for (int i = 0; i < Field.DEFAULT_MAX_ROW; i++) {
			s += strips[i].toString();
		}
		s += "/n";
		return s;
	}

	public void addToUnitList(Unit unit) {
		units.add(unit);
	}

	public void makeTurnAction() {
		// TODO Auto-generated method stub
		sunGenerationCooldown.tick();
		for (Strip strip: strips){
			for (Square square: strip.getSquares()){
				square.getPlant().makeTurnAction();
				for (Zombie z: square.getZombies()){
					z.makeTurnAction();
				}
				for (Bullet b: square.getBullets()){
					b.makeTurnAction();
				}
			}
		}

	}

}
