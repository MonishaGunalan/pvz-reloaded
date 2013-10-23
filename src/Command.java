import java.util.Scanner;

public class Command{
	
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
	
	int x;
	int y;
	String arg;
	CommandType commandType;
	

	public CommandType getCommandType() {
		return commandType;
	}

	public Command(int x, int y, String arg){
		this.x = x;
		this.y = y;
		this.arg = arg;
		
	}
	
	public Command (String s, Scanner c){
		
		commandType = CommandType.getCommandType(s);

		switch(commandType){
		case PLANT_SEED:
			System.out.println("Please enter what plant type and the x and y loaction");
			arg = c.next();
			x = c.nextInt();
			y = c.nextInt();
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
	
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getArg() {
		return arg;
	}

	public static String getCommandOptions(){
		StringBuilder b = new StringBuilder();
		for (CommandType cmd: CommandType.values()){
			b.append(cmd.name()).append("(\"").append(cmd.getCommandString()).append("\") ");
			
		}
		
		return b.toString();
	}
	
	
}