package pvz;

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

import pvz.Strip.Terrain;

/**
 * 
 * @author Christopher Nguyen
 *
 */
public class SquareLabel extends JLabel {
	/**
	 * row of square
	 */
	private int row;

	/**
	 * column of square
	 */
	private int col;
	/**
	 * List of zombie classes currently in the square
	 */
	private List<Class<? extends Zombie>> zombieList;
	/**
	 * List of bullet classes currently in the square
	 */
	private List<Class<? extends Bullet>> bulletList;
	/**
	 * The type of plant in the square
	 */
	private Class<? extends Plant> type;
	/**
	 * The terrain of the square
	 */
	private Strip.Terrain terrainType;
	/**
	 * Mapping of classes to images
	 */
	private static final Map<Class<? extends Unit>, Image> unitImageMapping;
	static {
		Map<Class<? extends Unit>, Image> aTable = new HashMap<Class<? extends Unit>, Image>();
		BufferedImage sampleImage = null;
		try {
			System.out.println(System.getProperty("user.dir"));
			sampleImage = ImageIO.read(new File("rsrc/sunflower.png"));
			aTable.put(SunflowerPlant.class, sampleImage);
			sampleImage = ImageIO.read(new File("rsrc/peashooter.png"));
			aTable.put(PeaShooterPlant.class, sampleImage);
			sampleImage = ImageIO.read(new File("rsrc/wallnut.png"));
			aTable.put(WallnutPlant.class, sampleImage);
			sampleImage = ImageIO.read(new File("rsrc/Zombie.png"));
			aTable.put(NormalZombie.class, sampleImage);
			sampleImage = ImageIO.read(new File("rsrc/ConeZombie.png"));
			aTable.put(ConeZombie.class, sampleImage);
			sampleImage = ImageIO.read(new File("rsrc/FlagZombie.png"));
			aTable.put(FlagZombie.class, sampleImage);
			sampleImage = ImageIO.read(new File("rsrc/PeashooterBullet.png"));
			aTable.put(PeaBullet.class, sampleImage);
		} catch (IOException e) {
			System.out.println("error trying to create image");
		}

		unitImageMapping = Collections.unmodifiableMap(aTable);
	}

	/**
	 * SquareLabel constructor
	 * 
	 * @param row
	 * @param col
	 */
	public SquareLabel(int row, int col, Square square) {
		this.row = row;
		this.col = col;
		zombieList = new ArrayList<Class<? extends Zombie>>();
		bulletList = new ArrayList<Class<? extends Bullet>>();
		updateSquare(square);
		this.terrainType = square.getStrip().getTerrain();
	}

	/**
	 * Update the square GUI with the model
	 * 
	 * @param square
	 */
	public void updateSquare(Square square) {
		// TODO:: have square return the numbers directly?
		if (square != null) {
			zombieList.clear();
			bulletList.clear();
			for (Zombie z : square.getZombies()) {
				zombieList.add(z.getClass());
			}
			for (Bullet b : square.getBullets()) {
				bulletList.add(b.getClass());
			}

			if (square.getPlant() != null) {
				type = square.getPlant().getClass();
			} else {
				type = null;
			}
		}
	}

	/**
	 * Paint the square with the appropriate images
	 */
	public void paintComponent(Graphics g) {
		int width = this.getWidth();
		int height = this.getHeight();
		
		//Check what type of terrain it is here then draws it
		
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, this.getWidth(), this.getHeight());
		// draw the plant
		if (type != null) {
			g.drawImage(unitImageMapping.get(type), width / 20, height / 4,
					null);
		}
		// draw the bullets
		for (int i = 0; i < bulletList.size(); i++) {
			g.drawImage(unitImageMapping.get(bulletList.get(i)), width / 2 + 3
					* (i), height / 4, null);
		}
		// draw the zombies

		for (int i = 0; i < zombieList.size(); i++) {
			g.drawImage(unitImageMapping.get(zombieList.get(i)), width * 5 / 8
					+ 5 * (i), height / 8, null);
		}
	}

	/**
	 * Getter for Row
	 * 
	 * @return
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Getter for Column
	 * 
	 * @return
	 */
	public int getCol() {
		return col;
	}
	
	public SquareLabel getStrip(Strip.Terrain ter) {
		SquareLabel s = null;
		Graphics g = null;
		switch (ter) {
		case MUD:
			g.setColor(Color.getHSBColor(156, 93, 82));//mud
			g.drawRect(0, 0, this.getWidth(), this.getHeight());
			break;
		case GRASS:
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			break;
		}
		return s;
	}

}
