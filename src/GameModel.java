import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;


public class GameModel {
	Player player;
	Level level;

	
	public GameModel(){
		//TODO:: reader info here
		File f = new File("playerData.txt");
		if (f.exists()){
			// load files here
			try {
				BufferedReader reader = new BufferedReader(new FileReader(f));
				int levelNum = Integer.parseInt(reader.readLine());
				int score =  Integer.parseInt(reader.readLine());
				level = new Level("", levelNum);
				player = new Player(level);
				player.setScore(score);
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			level = new Level("",1);
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
