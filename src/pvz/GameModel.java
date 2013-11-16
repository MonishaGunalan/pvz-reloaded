package pvz;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Observable;

/**
 * The GameModel is the model for the Plant vs Zombie game
 * it is also the entry point for playing the game on text based
 *
 * @author Christopher Nguyen
 * @version 1.0
 * @since 1.7
 */
public class GameModel extends Observable {
	/**
	 * The Player playing the game
	 */
	private Player player;
	/**
	 * The level that is loaded
	 */
	private Level level;

	/**
	 * The location where the player data will be stored (Not needed)
	 */
	public static final String playerDataFileLocation = "../rsrc/PlayerData.txt";
	
	
	/**
	 * The public constructor for Game Model
	 * If player data exists it is loaded and level and player is initialized
	 * 
	 */
	public GameModel(){
		
		File f = new File(playerDataFileLocation);
		
		//if the file exists then read the file and initialize based on the file
		if (f.exists()){
			try {
				BufferedReader reader = new BufferedReader(new FileReader(f));
				int levelNum = Integer.parseInt(reader.readLine());
				int score =  Integer.parseInt(reader.readLine());
				level = new Level(levelNum);
				player = new Player(level);
				player.setScore(score);
			} catch (NumberFormatException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch(IOException e){
				e.printStackTrace();
			}
		} else {
			//If no file exists initialize with level 1
			level = new Level(1);
			player = new Player(level);	
		}
		
		
	}	
	
	/**
	 * Play the game
	 */
	public void play(){
		player.play();
	}
	
	/**
	 * Play the game with a given a command
	 * @param command
	 * @return
	 */
	public boolean play(PlayerCommand command){
		boolean result = player.play(command);
		if (result){
			setChanged();
			this.notifyObservers();
		}
		return result;
	}
	
	/**
	 * Get the player
	 * @return the player
	 */
	public Player getPlayer(){
		return player;
	}
	
	/**
	 * Get the level
	 * @return return the level
	 */
	public Level getLevel(){
		return level;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GameModel().play();
	}

}
