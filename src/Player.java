import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Player {

	private int money;
	private int sun;
	//private Field field;
	private PlantFactory plantFactory;

	//This map contains the mapping of all plant type to their active cooldown
	private Map<PlantFactory.PlantType, Integer> triggeredCooldowns;

	public Player(/*Field field*/){
		//this.field = field;
		plantFactory = new PlantFactory();
		triggeredCooldowns = new HashMap<PlantFactory.PlantType, Integer>();
		sun = 0;
		money = 0;
	}

	public void play(){
		Scanner c = new Scanner(System.in);

		while (true){
			String command = c.next();

			//before do anything reduce cooldowns
			triggerCooldowns();
			PlantFactory.PlantType p = null;
			try{
				p = PlantFactory.PlantType.valueOf(command.toUpperCase());
			}catch(IllegalArgumentException e){
				System.out.println("No such plant!");
				continue;
			}

			if (p != null && triggeredCooldowns.containsKey(p)){
				grow(0,0,p);

			}
		}
	}


	public boolean grow(int row, int col, PlantFactory.PlantType plantType){
		//		Square square = field.getSquare(row,col);
		//		if(square.getPlant() != null && Type.getCost() <= sun){	
		Plant plant = plantFactory.makePlant(plantType);
		if (plant != null){
			System.out.println("Plant Created");
			System.out.println(plant.getClass().getName());
		} else {
			System.out.println("Plant Still On cooldown!");
		}



		if (plant != null){
			//			field.getSquare(row, col).setPlant()

			sun-= plantType.getCost();
			triggeredCooldowns.put(plantType, plantType.getCooldown());
			return true;
		}

		//		}

		return false;
	}

	/**
	 * This method should be called before makePlant so we don't decrement the cooldown as soon as it is added
	 */
	public void triggerCooldowns(){
		for (PlantFactory.PlantType plantType: triggeredCooldowns.keySet()){
			int cooldown = triggeredCooldowns.get(plantType);
			cooldown --;
			if (cooldown == -1){
				triggeredCooldowns.remove(plantType);
			} else{
				triggeredCooldowns.put(plantType, cooldown);
			}
		}
	}


}
