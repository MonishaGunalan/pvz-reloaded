/*
 * 
 * @author Chris Nguyen
 * 100793244
 * 
 */
import java.util.Scanner;

public class PlayerCommand{

	/**
	 * The enum Command Type will contain all  
	 * the possible commands the player may input
	 * 
	 */
	public enum CommandType{
		PLANT_SEED("p"),
		UNDO("u"),
		REDO("r"),
		DO_NOTHING("d");
		String commandString;
		private CommandType(String commandString){
			this.commandString = commandString;
		}

		public static CommandType getCommandType(String s){
			for (CommandType c: CommandType.values()){
				if (s.equals(c.commandString)){
					return c;
				}
			}
			return null;
		}

		public String getCommandString(){
			return commandString;
		}

	}

	//The arguments of commandtype
	int x, y;
	String arg;
	CommandType commandType;

	/**
	 * Returns the command type
	 * @return the command type
	 */
	public CommandType getCommandType() {
		return commandType;
	}

	/**
	 * Constructor for when all the data has been parsed
	 * 
	 * @param x			The x coordinate
	 * @param y			The y coordinate
	 * @param arg		The argument
	 */
	public PlayerCommand(int x, int y, String arg){
		this.x = x;
		this.y = y;
		this.arg = arg;

	}

	/**
	 * Constructor for initializing through an inputstream 
	 * 
	 * @param c		The Scanner to get additional information from player
	 */
	public PlayerCommand ( Scanner c){

		//Loop until a valid command has been given
		do{
			System.out.println(PlayerCommand.getCommandOptions());
			String command = c.next();
			commandType = CommandType.getCommandType(command);
		} while (commandType == null);
		
		switch(commandType){
		case PLANT_SEED:
			//Get the arguments for plant creation
			System.out.print("Plant: ");
			System.out.println(PlantFactory.getPlantOptions());
			System.out.println("Please enter plant and the row and col loaction");
			arg = c.next();
			x = getNumber(c);
			y = getNumber(c);

		case DO_NOTHING:
			break;
		case REDO:
			break;
		case UNDO:
			break;
		default:
			break;
		}

	}

	/**
	 * Getting number from the inputsream with errorhandling
	 * @param c
	 * @return the number from the inputstream 
	 */
	private int getNumber(Scanner c){
		boolean isNumber = false;
		int number= -1;
		while (!isNumber){
			try{
				number = Integer.parseInt(c.next());
				isNumber = true;
			}catch (NumberFormatException e){
				System.out.println("Please enter a number");
			}

		}
		return number;
		
	}

	/**
	 * Return the x coordinate
	 * @return the x coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * Return the y coordinate
	 * @return the y coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * Return the arg coordinate
	 * @return the arg coordinate
	 */
	public String getArg() {
		return arg;
	}

	/**
	 * Formatting for printing options
	 * @return The formatted string
	 */
	public static String getCommandOptions(){
		StringBuilder b = new StringBuilder();
		for (CommandType cmd: CommandType.values()){
			b.append(cmd.name()).append("(\"").append(cmd.getCommandString()).append("\") ");
		}

		return b.toString();
	}


}