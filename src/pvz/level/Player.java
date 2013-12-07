package pvz.level;

import java.util.Scanner;
import pvz.unit.Cooldown;
import pvz.unit.Plant;
import pvz.unit.PlantFactory;
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
	 * List of statuses
	 */
	public enum PlayStatus{
		GAMEOVER,
		VICTORY,
		ZOMBIE_DIED,
		NOT_ENOUGH_SUN,
		INVALID_POSITION,
		COOLDOWN_NOT_READY,
		INVALID_COMMAND,
		COMMAND_FAILED,
		NORMAL;
	}

	/**
	 * Model in charge of this game
	 */
	private GameModel model;

	/**
	 * public constructor for player
	 * @param model The model the player is interacting with
	 */
	public Player(GameModel model){
		this.model = model;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new GameModel(false, true).play();
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
			PlayStatus currentStatus = play(command);
			// Print the field to string
			System.out.println(model.getLevel().getField().toString());
			//Handle game ending conditions
			if (currentStatus == PlayStatus.GAMEOVER) {
				System.out.println(GameModel.LOSE_MSG);
				break;
			} else if (currentStatus == PlayStatus.VICTORY)  {
				int currentLevelNumber = model.getLevel().getLevelNumber();
				// If we reach max level, print win message and exit
				if (currentLevelNumber >= GameModel.MAX_LEVEL) {
					System.out.println(GameModel.WIN_MSG);
					break;
				} else {
					// Load next level
					System.out.println(GameModel.NEXT_LEVEL_MSG);
					model.loadNextLevel();
				}
			}
		}
	}

	/**
	 * The helper function if using text version that will handle the command
	 * @param command The command to be input
	 * @return The status of the play
	 */
	public synchronized PlayStatus play (PlayerCommand command){
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
			//Return the command if it was not successful
			if (growSuccessful != PlayStatus.NORMAL){
				return growSuccessful;
			}
			//Only increment turn if it is being played turn based
			if (!model.isRealTime()){
				model.getLevel().incrementTurn();
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
			// Save previous state to undo stack before
			// we modify the level state
			try {
				model.writeHistory();
			} catch(Exception e) {
				e.printStackTrace();
			}
			//Player does nothing that turn, just increment to the next turn
			model.getLevel().incrementTurn();

			break;
		default:
		}
		if (model.getLevel().isGameOver()) {
			return PlayStatus.GAMEOVER;
		}else if (model.getLevel().isVictorious())
			return PlayStatus.VICTORY;
		else{
			return PlayStatus.NORMAL;
		}
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
		Cooldown plantTypeCD = model.getLevel().getTriggeredCooldowns().get(plantType);
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
			model.getLevel().getField().useSun(plantCost);
			model.getLevel().addObserver(plant);
			model.getLevel().getTriggeredCooldowns().get(plantType).trigger();
			return Player.PlayStatus.NORMAL;
		}

		return Player.PlayStatus.INVALID_COMMAND;
	}


}
