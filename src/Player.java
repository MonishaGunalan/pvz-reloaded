import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
//import CommandType.*;

public class Player {

	private int money;
	private int sun;
	private Field field;
	private PlantFactory plantFactory;
	Scanner c;
	//This map contains the mapping of all plant type to their active cooldown
	private Map<PlantFactory.PlantType, Integer> triggeredCooldowns;



	public Player(Field field){
		this.field = field;
		plantFactory = new PlantFactory();
		triggeredCooldowns = new HashMap<PlantFactory.PlantType, Integer>();
		sun = 0;
		money = 0;
		c = new Scanner(System.in);

	}

	public void play(){


		while (true){
			Command command = getNextCommand(); 

			//before do anything reduce cooldowns
			triggerCooldowns();

			switch(command.getCommandType()){
			case PLANT_SEED:
				PlantFactory.PlantType p = null;
				String plant = command.getArg();
				try{
					p = PlantFactory.PlantType.valueOf(plant.toUpperCase());
				}catch(IllegalArgumentException e){
					System.out.println("No such plant!");
					continue;
				}

				if (p != null && triggeredCooldowns.containsKey(p)){
					grow(0,0,p);

				}
				break;
			case UNDO:
				//TODO implement
				break;
			case REDO:
				//TODO implement
				break;
			default:
			}
			
		}
	}

	public Command getNextCommand(){
		String command = c.next();
		
		return new Command(command);
	}


	public boolean grow(int row, int col, PlantFactory.PlantType plantType){
		Square square = field.getStrip()[row].getSquare()[col];
		if(square.hasPlant() && plantType.getCost() <= sun){	
			Plant plant = plantFactory.makePlant(plantType);
			if (plant != null){
				System.out.println("Plant Created");
				System.out.println(plant.getClass().getName());
			} else {
				System.out.println("Plant Still On cooldown!");
			}


			if (plant != null){
				square.addPlant(plant);
				sun-= plantType.getCost();
				triggeredCooldowns.put(plantType, plantType.getCooldown());
				return true;
			}

		}

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
