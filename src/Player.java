import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * This class represents the player playing the the game
 * it keeps track of the current score, sun points and level
 *
 * @author Christopher Nguyen
 * @version 1.0
 * @since 1.7
 */
public class Player {

	/**
	 * The current score of the player (Not implemented)
	 */
	private int score;
	/**
	 * The Current sun points the playe rhas
	 */
	private int sun;
	/**
	 * The current level the player is playing
	 */
	private Level level;

	/**
	 * This map contains the mapping of all plant type to their active cooldown
	 */
	private Map<PlantFactory.PlantType, Cooldown> triggeredCooldowns;


	/**
	 * public constructor for player
	 * @param level	The level the player starts at
	 */
	public Player(Level level){
		this.level = level;

		triggeredCooldowns = new HashMap<PlantFactory.PlantType, Cooldown>();
		for (PlantFactory.PlantType p: PlantFactory.PlantType.values()){
			triggeredCooldowns.put(p, new Cooldown(p.getCooldown()));
		}
		sun = 25;
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
			System.out.println("Current Sun Points: " + sun);
			PlayerCommand command = new PlayerCommand(c); 
			play(command);
			System.out.println(level.getField().toString());
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
			if (p != null){
				growSuccessful = grow(command.getX(),command.getY(),p);

			}
			if (growSuccessful){
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
		case DO_NOTHING:
			level.incrementTurn();
			triggerCooldowns();
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
		//TODO:: return with more meaningful error messages
		if (!triggeredCooldowns.get(plantType).isAvailable()){
			System.out.println("Plant Still On cooldown!");
			return false;
		}

		Square square = level.getField().getStrip()[row].getSquare(col);
		if (square.hasPlant()){
			System.out.println("There is already a plant present in the square!");
			return false;
		} else if (plantType.getCost() > sun){
			System.out.println("Insufficient funds!");
			return false;
		}

		Plant plant = PlantFactory.makePlant(plantType);
		if (plant != null){
			System.out.println("Plant Created");
			System.out.println(plant.getClass().getName());
			plant.setSquare(square);
			sun-= plantType.getCost();
			triggeredCooldowns.get(plantType).trigger();
			return true;
		}

		return false;
	}

	/**
	 * Iterate through all the cooldowns and tick any that are active
	 */
	public void triggerCooldowns(){
		sun += level.getField().getTotalSun();
		for (Cooldown cooldown: triggeredCooldowns.values()){
			cooldown.tick();
		}
	}


	/**
	 * Save the player state
	 */
	public void save(){

		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(GameModel.playerDataFileLocation));
			out.write(level.getLevelNumber());
			out.write(score);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Set the player score (Not implemented)
	 * @param score
	 */
	public void setScore(int score){
		this.score = score;
	}

}
