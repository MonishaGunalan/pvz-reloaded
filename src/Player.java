import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
//import CommandType.*;

public class Player {

	private int score;
	private int sun;
	private Level level;
	private PlantFactory plantFactory;
	Scanner c;
	//This map contains the mapping of all plant type to their active cooldown
	private Map<PlantFactory.PlantType, Integer> triggeredCooldowns;



	public Player(Level level){
		this.level = level;
		plantFactory = new PlantFactory();
		triggeredCooldowns = new HashMap<PlantFactory.PlantType, Integer>();
		sun = 0;
		score = 0;
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
				level.incrementTurn();
				break;
			case UNDO:
				//TODO implement a Turn Class that will encapsulate the data of a turn
				break;
			case REDO:
				//TODO implement a Turn Class that will encapsulate the data of a turn
				break;
			default:
			}
			
		}
	}

	public Command getNextCommand(){
		System.out.println(Command.getCommandOptions());
		String command = c.next();
		
		return new Command(command,c);
	}


	public boolean grow(int row, int col, PlantFactory.PlantType plantType){
		Square square = level.getField().getStrip()[row].getSquare()[col];
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

	
	public void save(){
		
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("playerData.txt"));
			out.write(level.getLevelNumber());
			out.write(score);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void setScore(int score){
		this.score = score;
	}

}
