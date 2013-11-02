/**
 * This Factory Class generates all the plant instances
 *
 * @author Christopher Nguyen
 * @version 1.0
 * @since 1.7
 */
public class PlantFactory {

	/**
	 * The enum binds all the cooldown and cost to the plant class
	 * @author Christopher Nguyen
	 *
	 */
	public enum PlantType {
		SUNFLOWER(5,5,  SunflowerPlant.class),
		PEASHOOTER(5,5,PeaShooterPlant.class);
		
		/**
		 * Cooldown for the number of turns before the plant can be planted again
		 */
		private int cooldown;
		/**
		 * The sun cost of the plant
		 */
		private int cost;
		/**
		 * The type of plant it is
		 */
		private Class<? extends Plant> plantClass;

		/**
		 * priavte constructor
		 * @param cost
		 * @param cooldown
		 * @param plantClass
		 */
		private PlantType(int cost, int cooldown,Class<? extends Plant> plantClass){
			this.cost = cost;
			this.cooldown = cooldown;
			this.plantClass = plantClass;
		}

		/**
		 * Getter for Plant Class
		 * @return
		 */
		public Class<? extends Plant> getPlantClass(){
			return this.plantClass;
		}

		/**
		 * Getter for cooldown
		 * @return
		 */
		public int getCooldown(){
			return this.cooldown;
		}

		/**
		 * Getter for cost
		 * @return
		 */
		public int getCost(){
			return this.cost;
		}

	}

	/**
	 * @param type          The type of plant to be made
	 * @return Plant        The Plant that is made
	 * 
	 * The method checks to see if the plant type is on cooldown or not
	 * if it is not on cooldown it creates the desired plant
	 * 
	 */
	public static Plant makePlant(PlantType type){


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


		return p;

	}
	
	/**
	 * Returns a string formatted with the plants option
	 * @return the formatted string
	 */
	public static String getPlantOptions(){
		StringBuilder b = new StringBuilder();
		for (PlantType cmd: PlantType.values()){
			b.append(cmd.name()).append(" ");

		}
		return b.toString();
	}
}
