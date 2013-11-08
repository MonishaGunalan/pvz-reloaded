import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JLabel;


public class SquareLabel extends JLabel {
	//TODO:: have list of objects here
	int row;
	int col;
	private List<Class<?extends Zombie>> zombieList;
	private List<Class<?extends Bullet>> bulletList;
	Class<? extends Plant> type;
	
	private static final Map<Class<? extends Unit>, Image> unitImageMapping;
	static {
		Map<Class<? extends Unit>, Image> aTable = new HashMap<Class<? extends Unit>, Image>();
		BufferedImage sampleImage = null ;
		try {
			System.out.println(System.getProperty("user.dir"));
			sampleImage = ImageIO.read(new File("sunflower.png"));
			aTable.put(SunflowerPlant.class, sampleImage);
			sampleImage = ImageIO.read(new File("sunflower.png"));
			aTable.put(PeaShooterPlant.class, sampleImage);
			sampleImage = ImageIO.read(new File("sunflower.png"));
			aTable.put(NormalZombie.class, sampleImage);
			sampleImage = ImageIO.read(new File("sunflower.png"));
			aTable.put(PeaBullet.class, sampleImage);
		} catch (IOException e) {
			System.out.println("error trying to create image");
		}


		unitImageMapping = Collections.unmodifiableMap(aTable);
	}

	
	public SquareLabel(int row, int col){
		this.row = row;
		this.col = col;
		zombieList = new ArrayList<Class<? extends Zombie>>();
		bulletList = new ArrayList<Class<? extends Bullet>>();
	}
	
	public void updateSquare(Square square){
		//TODO:: have square return the numbers directly
		if (square != null){
			zombieList.clear();
			bulletList.clear();
			for (Zombie z: square.getZombies()){
				zombieList.add(z.getClass());
			}
			for (Bullet b: square.getBullets()){
				bulletList.add(b.getClass());
			}

			if (square.getPlant() != null){
				//TODO:: have a mapping of class to images
				type = square.getPlant().getClass();
			}else {
				type = null;
			}
		}
	}
	
	
	public void paintComponent(Graphics g)
	{
		//TODO:: figure out if this belongs on instance  scope
		int width = this.getWidth();
		int height = this.getHeight();
		//TODO:: change default of grass
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, this.getWidth(), this.getHeight());
		//draw the plant
		if (type != null){
			g.drawImage(unitImageMapping.get(type), width/8, height/8, null);
		}
		//draw the bullets
		for (int i =0; i< bulletList.size(); i++){
			g.drawImage(unitImageMapping.get(bulletList.get(i)), width/2 - 10 *(bulletList.size() -i), height/2, null);
		}
		//draw the zombies

		for (int i =0; i< zombieList.size(); i++){
			g.drawImage(unitImageMapping.get(zombieList.get(i)), width* 6 /8 - 10 *(zombieList.size() -i), height/8, null);
		}
	}
	
	public int getRow(){
		return row;
	}
	
	public int getCol(){
		return col;
	}

}
