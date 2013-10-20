public class Command{
	
	public enum CommandType{
		PLANT_SEED("p"),
		UNDO("u"),
		REDO("r"),
		DO_NOTHING("d");
		String command;
		private CommandType(String command){
			this.command = command;
		}
		
		public static CommandType getCommandType(String s){
			for (CommandType c: CommandType.values()){
				if (s.equals(c.command)){
					return c;
				}
			}
			return null;
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
	
	public Command (String s){
		
		String [] argv = s.split(" ");
		commandType = CommandType.getCommandType(argv[0]);
		
		switch(commandType){
		case PLANT_SEED:
			arg = argv[1];
			x = Integer.parseInt(argv[2]);
			y = Integer.parseInt(argv[3]);
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

	
	
	
	
	
	
}