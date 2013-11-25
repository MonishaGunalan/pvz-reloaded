package pvz;
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

	public enum PlayStatus{
		GAMEOVER,
		VICTORY,
		NOT_ENOUGH_SUN,
		INVALID_POSITION,
		COOLDOWN_NOT_READY,
		INVALID_COMMAND,
		COMMAND_FAILED,
		NORMAL;
	}
	/**
	 * The starting score;
	 */
	public final int START_SCORE = 0;
	/**
	 * The current score of the player (Not implemented)
	 */
	private int score;
	/**
	 * Model in charge of this game
	 */
	private GameModel model;
	/**
	 * This map contains the mapping of all plant type to their active cooldown
	 */
	private Map<Plant.Type, Cooldown> triggeredCooldowns;

	/**
	 * public constructor for player
	 * @param The model the player is interacting with
	 */
	public Player(GameModel model){
		this.model = model;

		triggeredCooldowns = new HashMap<Plant.Type, Cooldown>();
		for (Plant.Type p: Plant.Type.values()){
			// We need to resolve static function from 
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
			System.out.println("Current Sun Points: " + model.getLevel().getField().getTotalSun());
			PlayerCommand command = getPlayerCommand(c);
			//call the helper method
			play(command);
			System.out.println(model.getLevel().getField().toString());
		}
	}

	/**
	 * The helper function if using text version that will handle the command
	 * @param command The command to be input
	 * @return
	 */
	public PlayStatus play (PlayerCommand command){
		if (command == null){
			return PlayStatus.INVALID_COMMAND;
		}
		switch(command.getCommandType()){
			case PLANT_SEED:
				//Try to create the plant based on the playercommand
				Plant.Type p = null;
				String plant = command.getArg();
				//Get the plant type
				try{
					p = Plant.Type.valueOf(plant.toUpperCase());
				}catch(IllegalArgumentException e){
					System.out.println("No such plant!");
					return PlayStatus.INVALID_COMMAND;
				}
				PlayStatus growSuccessful = null;
				//try to grow the plant
				if (p != null){
					growSuccessful = grow(command.getRow(),command.getCol(),p);

				}
				//increment turn if the grow was successful
				if (growSuccessful == PlayStatus.NORMAL){
					model.getLevel().incrementTurn();
					triggerCooldowns();
				} else{
					return growSuccessful;
				}
				break;

			case UNDO:
				if (model.undo()){
					return PlayStatus.NORMAL;
				}else {
					return PlayStatus.COMMAND_FAILED;
				}
			case REDO:
				if (model.redo()){
					return PlayStatus.NORMAL;
				}else {
					return PlayStatus.COMMAND_FAILED;
				}
			case DO_NOTHING:
				//Player does nothing that turn, just increment to the next turn
				model.getLevel().incrementTurn();
				triggerCooldowns();
				break;
			default:
		}
		//if (model.getLevel().isGameOver())
		//return PlayStatus.GAME_OVER;
		//}else if (model.getLevel().isVictoious())
		//return PlayStatus.victory
		//else{
		return PlayStatus.NORMAL;
		//}

	}

	/**
	 * Gets the player command
	 */
	private PlayerCommand getPlayerCommand(Scanner c){
		return new PlayerCommand(c);
	}

	/**
	 * Grow the plant in the square at row col if the player
	 * has sufficient sun points and the cooldown is not active
	 * @param row
	 * @param col
	 * @param plantType
	 * @return The boolean if the the plant was grown
	 */
	public Player.PlayStatus grow(int row, int col, Plant.Type plantType){

		int plantCost = PlantFactory.getCost(plantType);
		//TODO:: return with more meaningful error messages
		Cooldown plantTypeCD = triggeredCooldowns.get(plantType);
		if (!plantTypeCD.isAvailable()){
			System.out.println("Plant Still On cooldown for " + plantTypeCD.getCooldown() + " more turns!");
			return Player.PlayStatus.COOLDOWN_NOT_READY;
		}

		// Get reference to indicated square
		Square square = model.getLevel().getSquare(row, col);

		// Check if it can be plant in the location
		if (square.hasPlant()){
			// Occupied square
			System.out.println("There is already a plant present in the square!");
			return Player.PlayStatus.INVALID_POSITION;
		} else if (plantCost > model.getLevel().getField().getTotalSun()){
			// Not enough sun
			System.out.println("Insufficient funds!");
			return Player.PlayStatus.NOT_ENOUGH_SUN;
		}

		//Make the plant and update the level based on the plant
		Plant plant = PlantFactory.makePlant(plantType, square);
		if (plant != null){
			System.out.println("Plant Created");
			System.out.println(plant.getClass().getName());
			System.out.println("Using " + plantCost + " amount of sun.");
			model.getLevel().getField().useSun(plantCost);
			model.getLevel().addObserver(plant);
			triggeredCooldowns.get(plantType).trigger();
			return Player.PlayStatus.NORMAL;
		}

		return Player.PlayStatus.INVALID_COMMAND;
	}

	/**
	 * Iterate through all the cooldowns and tick any that are active
	 */
	public void triggerCooldowns(){
		//iterate through all it's cooldown and trigger it
		for (Cooldown cooldown: triggeredCooldowns.values()){
			cooldown.tick();
		}
	}


	/**
	 * Save the player state Not used
	 */
	public void save(){

		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(GameModel.playerDataFileLocation));
			out.write(model.getLevel().getLevelNumber());
			out.write(score);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Setter method for score
	 * @param score
	 */
	public void setScore(int score){
		this.score = score;
	}

}
