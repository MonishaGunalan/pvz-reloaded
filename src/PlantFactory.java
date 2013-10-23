public class PlantFactory {

	public enum PlantType {
		SUNFLOWER(5,5,  SunflowerPlant.class),
		PEASHOOTER(5,5,PeaShooterPlant.class);
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



	public PlantFactory(){

	}




	/**
	 * @param type                The type of plant to be made
	 * @return Plant        The Plant that is made
	 * 
	 * The method checks to see if the plant type is on cooldown or not
	 * if it is not on cooldown it creates the desired plant
	 * 
	 */
	public Plant makePlant(PlantType type){


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

	public static String getPlantOptions(){
		StringBuilder b = new StringBuilder();
		for (PlantType cmd: PlantType.values()){
			b.append(cmd.name()).append(" ");

		}
		return b.toString();
	}
}
