/*
 * @author Chris Nguyen
 * 100793244
 * 
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Player {
	public final int START_SCORE = 0;

	private int score;
	private Level level;

	//This map contains the mapping of all plant type to their active cooldown
	private Map<Plant.Type, Cooldown> triggeredCooldowns;


	/**
	 * public constructor for player
	 * @param level	The level the player starts at
	 */
	public Player(Level level){
		this.level = level;

		triggeredCooldowns = new HashMap<Plant.Type, Cooldown>();
		for (Plant.Type p: Plant.Type.values()){
			// We need to resolve static function from 
			Class c = PlantFactory.getClass(p);
			triggeredCooldowns.put(p, new Cooldown(PlantFactory.getCooldown(p)));
		}
		score = START_SCORE;

	}

	/**
	 * The play via command line as the means of input
	 * and pass to a helper function to handle the command
	 */
	public void play(){
		Scanner c = new Scanner(System.in);
		while (true){
			//Get the player command
			System.out.println("Current Sun Points: " + level.getField().getTotalSun());
			PlayerCommand command = new PlayerCommand(c); 
			play(command);
			System.out.println(level.getField().toString());
		}
	}

	/**
	 * The helper function that will handle the command
	 * @param command The command to be input
	 */
	public boolean play (PlayerCommand command){
		if (command == null){
			return false;
		}
		switch(command.getCommandType()){
			case PLANT_SEED:
				//Try to create the plant based on the playercommand
				Plant.Type p = null;
				String plant = command.getArg();
				try{
					p = Plant.Type.valueOf(plant.toUpperCase());
				}catch(IllegalArgumentException e){
					System.out.println("No such plant!");
					return false;
				}
				boolean growSuccessful = false;
				if (p != null){
					growSuccessful = grow(command.getRow(),command.getCol(),p);

				}
				if (growSuccessful){
					level.incrementTurn();
					triggerCooldowns();
				}
				return growSuccessful;
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
		return true;



	}



	/**
	 * Grow the plant in the square at row col if the player
	 * has sufficient sun points and the cooldown is not active
	 * @param row
	 * @param col
	 * @param plantType
	 * @return The boolean if the the plant was grown
	 */
	public boolean grow(int row, int col, Plant.Type plantType){
		// Get the plant class from type
		Class plantClass = PlantFactory.getClass(plantType);
		int plantCost = PlantFactory.getCost(plantType);
		//TODO:: return with more meaningful error messages
		Cooldown plantTypeCD = triggeredCooldowns.get(plantType);
		if (!plantTypeCD.isAvailable()){
			System.out.println("Plant Still On cooldown for " + plantTypeCD.getCooldown() + " more turns!");
			return false;
		}

		// Get reference to indicated square
		Square square = level.getSquare(row, col);

		// Failure to plant
		if (square.hasPlant()){
			// Occupied square
			System.out.println("There is already a plant present in the square!");
			return false;
		} else if (plantCost > level.getField().getTotalSun()){
			// Not enough sun
			System.out.println("Insufficient funds!");
			return false;
		}

		Plant plant = PlantFactory.makePlant(plantType, square);
		if (plant != null){
			System.out.println("Plant Created");
			System.out.println(plant.getClass().getName());
			System.out.println("Using " + plantCost + " amount of sun.");
			level.getField().useSun(plantCost);
			level.addObserver(plant);
			triggeredCooldowns.get(plantType).trigger();
			return true;
		}

		return false;
	}

	/**
	 * Iterate through all the cooldowns and tick any that are active
	 */
	public void triggerCooldowns(){
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

	public void setScore(int score){
		this.score = score;
	}

}
