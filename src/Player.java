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

	//Scanner c;
	//This map contains the mapping of all plant type to their active cooldown
	private Map<PlantFactory.PlantType, Integer> triggeredCooldowns;


	/**
	 * public constructor for player
	 * @param level	The level the player starts at
	 */
	public Player(Level level){
		this.level = level;

		triggeredCooldowns = new HashMap<PlantFactory.PlantType, Integer>();
		sun = 0;
		score = 0;


	}

	/**
	 * The play via command line as the means of input
	 * and pass to a helper function to handle the command
	 */
	public void play(){
		Scanner c = new Scanner(System.in);
		while (true){
			//Get the player command
			PlayerCommand command = new PlayerCommand(c); 
			play(command);
		}
	}

	/**
	 * The helper function that will handle the command
	 * @param command The command to be input
	 */
	private void play (PlayerCommand command){
		switch(command.getCommandType()){
		case PLANT_SEED:
			//Try to create the plant based on the playercommand
			PlantFactory.PlantType p = null;
			String plant = command.getArg();
			try{
				p = PlantFactory.PlantType.valueOf(plant.toUpperCase());
			}catch(IllegalArgumentException e){
				System.out.println("No such plant!");
				return;
			}
			boolean growSuccessful = false;
			if (p != null && triggeredCooldowns.containsKey(p)){
				growSuccessful = grow(0,0,p);

			}
			if (growSuccessful){
				System.out.println(level.toString());
				level.incrementTurn();
				triggerCooldowns();
			}
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



	/**
	 * Grow the plant in the square at row col if the player
	 * has sufficient sun points and the cooldown is not active
	 * @param row
	 * @param col
	 * @param plantType
	 * @return The boolean if the the plant was grown
	 */
	public boolean grow(int row, int col, PlantFactory.PlantType plantType){

		//TODO:: give a message if sun points are sufficient or square already has plants
		Square square = level.getField().getStrip()[row].getSquare(col);
		if(square.hasPlant() && plantType.getCost() <= sun){	
			Plant plant = PlantFactory.makePlant(plantType);
			if (plant != null){
				System.out.println("Plant Created");
				System.out.println(plant.getClass().getName());
			} else {
				System.out.println("Plant Still On cooldown!");
			}


			if (plant != null){
				square.add(plant);
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
			if (cooldown == 0){
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
