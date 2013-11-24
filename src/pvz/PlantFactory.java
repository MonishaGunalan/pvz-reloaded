package pvz;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
/**
 * Factory class for the Plant, it creates an instance of the plant needed
 * using the mapping found in plantTable
 *
 * @author Christopher Nguyen
 * @version 1.0
 * @since 1.7
 */
public class PlantFactory {
	/**
	 * Mapping of plant type to plant data
	 */
	private static final Map<Plant.Type, PlantData> plantTable;
	/**
	 * Sunflower cooldown
	 */
	private static final int SUNFLOWER_CD = 3;
	/**
	 * Sunflower cost
	 */
	private static final int SUNFLOWER_COST = 50;
	/**
	 * Peashooter cooldown
	 */
	private static final int PEASHOOTER_CD = 4;
	/**
	 * Peashooter cost
	 */
	private static final int PEASHOOTER_COST = 100;

	static {
		//Instanciate to map static time
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
		if (plantType == null || square == null){
			return null;
		}
		Plant p = null;
		Class<? extends Plant> plantClass = plantTable.get(plantType).pClass;
		try {
			p = (Plant)plantClass.getConstructor(Square.class).newInstance(square);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return p;
	}

	/**
	 * Return the Class mapping of a type
	 * @param plantType
	 * @return
	 */
	public static Class<? extends Plant> getClass(Plant.Type plantType) {
		return plantTable.get(plantType).pClass;
	}
	
	/**
	 * Returns a string formatted with the plants option
	 * @return the formatted string
	 */

	public static int getCooldown(Plant.Type plantType) { 
		return plantTable.get(plantType).seedCooldown;
	}

	/**
	 * Iterate through the plant type and get the string format
	 * @return the formatted string
	 */
	public static String getPlantOptions(){
		StringBuilder b = new StringBuilder();
		for (Plant.Type cmd: Plant.Type.values()){
			b.append(cmd.name()).append(" ");

		}
		return b.toString();
	}

	/**
	 * Get the cost of a specific Plant
	 * @param plantType
	 * @return The cost of the plant
	 */
	public static int getCost(Plant.Type plantType) {
		return plantTable.get(plantType).seedCost;
	}
}
