/*
 * @author Monisha Gunalan
 * 100871444
 */
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
/*
 * @param TerrianType[]  the terrian type for each row
 */
	public Field(String[] terrainType) {
		strips = new Strip[DEFAULT_MAX_ROW];
		for (int i = 0; i < DEFAULT_MAX_ROW; i++) {
			strips[i] = new Strip(terrainType[i], i, this);
		}
		units = new ArrayList<Unit>();
		sunGenerationCooldown = new Cooldown(SUN_GENERATION_PERIOD);
	}
/*
 * @return Strip[] returns the array of strips in the field
 */
	public Strip[] getStrip() {
		return strips;
	}

	/*
	 * plant the seed in the field
	 * @param plant plant
	 */
	public void seedPlant(Plant plant) {
		int row = plant.getRow();
		int col = plant.getCol();

		if (row < DEFAULT_MAX_ROW && col < DEFAULT_MAX_POSN) {
			strips[row].getSquares()[col].add(plant);
		}
	}

	/*
	 * @param square the square element
	 * @return square rdturns the next square in the fiels
	 */
	public Square getNextSquare(Square square) {
		Square nextSquare = null;
		int row = square.getRow();
		int col = square.getCol();
		if (col < DEFAULT_MAX_POSN - 1) {
			nextSquare = strips[row].getSquares()[col + 1];
		}
		return nextSquare;
	}

	/*
	 * 
	 * @param square the square element
	 * @return square rdturns the previous square in the fiels
	 */
	 
	public Square getPrevSquare(Square square) {
		Square prevSquare = null;
		int row = square.getRow();
		int col = square.getCol();
		if (col > 0) {
			prevSquare = strips[row].getSquares()[col - 1];
		}
		return prevSquare;
	}

	/*
	 * get the total sun generated
	 * @return sun points
	 */
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


/*
 * @return returns the string representation of the field
 */
	public String toString() {
		String s = "";
		for (int i = 0; i < Field.DEFAULT_MAX_ROW; i++) {
			s += strips[i].toString();
		}
		s += "\n";
		return s;
	}

	/*
	 * @param unit add the unit to thelist of units in the field
	 */
	public void addToUnitList(Unit unit) {
		units.add(unit);
	}

	/*
	 * trigger action on the unit to move after every turn
	 */
	public void makeTurnAction() {
		// TODO Auto-generated method stub
		sunGenerationCooldown.tick();
		for (Strip strip: strips){
			for (Square square: strip.getSquares()){
				if (square.getPlant() != null){
					square.getPlant().makeTurnAction();
				}
				for (Bullet b: new ArrayList<Bullet>(square.getBullets())){
					b.makeTurnAction();
				}
				for (Zombie z: new ArrayList<Zombie>(square.getZombies())){
					z.makeTurnAction();
					//System.out.println("Zombie move cooldown: "  + z.getMoveCooldown());
				}
			}
		}

	}

}
