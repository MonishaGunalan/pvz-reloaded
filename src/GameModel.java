
public class GameModel {
	Player player;
	Level level;

	
	public GameModel(){
		//TODO:: reader info here
		level = new Level("",1);
		player = new Player(level.getField());

		player.play();
		
	}
	
	

	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GameModel();
	}

}
