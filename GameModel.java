
public class GameModel {
	Player player;
	//Level level; 	
	
	
	public GameModel(){
		player = new Player();
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
