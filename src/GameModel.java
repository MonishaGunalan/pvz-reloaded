
public class GameModel {
	Player player;
	Level level;

	
	public GameModel(){
		loadLevel();
		player = new Player(level.getField());
		loadPlayerInformation();
		player.play();
		
	}
	
	
	private void loadPlayerInformation(){
		
	}
	
	private void loadLevel(){
		
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GameModel();
	}

}
