/*
 * @author Chris Nguyen
 * 100793244
 * 
 */
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

public class PlantFactory {
	private static final Map<Plant.Type, PlantData> plantTable;
	// Sunflower info
	private static final int SUNFLOWER_CD = 3;
	private static final int SUNFLOWER_COST = 50;
	// Peashooter info
	private static final int PEASHOOTER_CD = 4;
	private static final int PEASHOOTER_COST = 100;

	static {
		Map<Plant.Type, PlantData> aTable = new HashMap<Plant.Type, PlantData>();

		aTable.put(Plant.Type.SUNFLOWER, new PlantData(SunflowerPlant.class, SUNFLOWER_CD, SUNFLOWER_COST));
		aTable.put(Plant.Type.PEASHOOTER, new PlantData(PeaShooterPlant.class, PEASHOOTER_CD, PEASHOOTER_COST));

		plantTable = Collections.unmodifiableMap(aTable);
	}

	/**
	 * @param type          The type of plant to be made
	 * @return Plant        The Plant that is made
	 * 
	 * The method checks to see if the plant type is on cooldown or not
	 * if it is not on cooldown it creates the desired plant
	 * 
	 */
	public static Plant makePlant(Plant.Type plantType, Square square) {
		Plant p = null;
		Class plantClass = plantTable.get(plantType).pClass;
		try {
			p = (Plant)plantClass.getConstructor(Square.class).newInstance(square);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return p;
	}


	public static Class getClass(Plant.Type plantType) {
		return plantTable.get(plantType).pClass;
	}
	
	/**
	 * Returns a string formatted with the plants option
	 * @return the formatted string
	 */

	public static int getCooldown(Plant.Type plantType) { 
		return plantTable.get(plantType).seedCooldown;
	}

	public static String getPlantOptions(){
		StringBuilder b = new StringBuilder();
		for (Plant.Type cmd: Plant.Type.values()){
			b.append(cmd.name()).append(" ");

		}
		return b.toString();
	}

	public static int getCost(Plant.Type plantType) {
		return plantTable.get(plantType).seedCost;
	}
}
