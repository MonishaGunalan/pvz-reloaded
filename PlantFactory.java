import java.util.HashMap;
import java.util.Map;



public class PlantFactory {
 
	public enum PlantType {
		SUNFLOWER(5,5,  SunflowerPlant.class),
		PEASHOOTER(5,5,Plant.class);
		private int cooldown;
		private int cost;
		private Class<? extends Plant> plantClass;
		//private String name;
		private PlantType(int cost, int cooldown,Class<? extends Plant> plantClass){
			this.cost = cost;
			this.cooldown = cooldown;
			this.plantClass = plantClass;
		}

		public Class<? extends Plant> getPlantClass(){
			return this.plantClass;
		}

		public int getCooldown(){
			return this.cooldown;
		}

		public int getCost(){
			return this.cost;
		}

	}

	//This map contains the mapping of all plant type to their active cooldown
	private Map<PlantType, Integer> activeCooldownMap;

	public PlantFactory(){
		activeCooldownMap = new HashMap<PlantType, Integer>();
	}

	/**
	 * This method should be called before makePlant so we don't decrement the cooldown as soon as it is added
	 */
	public void triggerCooldowns(){
		for (PlantType plantType: activeCooldownMap.keySet()){
			int cooldown = activeCooldownMap.get(plantType);
			cooldown --;
			if (cooldown == -1){
				activeCooldownMap.remove(plantType);
			} else{
				activeCooldownMap.put(plantType, cooldown);
			}
		}
	}


	/**
	 * @param type		The type of plant to be made
	 * @return Plant	The Plant that is made
	 * 
	 * The method checks to see if the plant type is on cooldown or not
	 * if it is not on cooldown it creates the desired plant
	 * 
	 */
	public Plant makePlant(PlantType type){

		if (activeCooldownMap.containsKey(type)){
			//Plant Type still on cooldown
			return null;
		}
		Plant p = null;
		try {
			p = type.getPlantClass().newInstance();

		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		activeCooldownMap.put(type, type.getCooldown());
		return p;

	}
}
