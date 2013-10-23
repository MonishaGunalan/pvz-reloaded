import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;


public class GameModel {
	Player player;
	Level level;

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
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			//If no file exists initialize with level 1
			level = new Level(1);
			player = new Player(level);	
		}
		

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
