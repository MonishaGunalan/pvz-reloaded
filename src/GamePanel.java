import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GamePanel extends JPanel {

	SquareLabel [][] grid;
	Level level;

	public GamePanel(MouseListener mouseListener, Level level){
		super();
		this.level = level;
		this.setLayout(new GridLayout(Field.DEFAULT_MAX_ROW,Field.DEFAULT_MAX_POSN));
		grid = new SquareLabel[Field.DEFAULT_MAX_ROW][Field.DEFAULT_MAX_POSN];
		
		for (int i =0; i <Field.DEFAULT_MAX_ROW; i++){
			for (int j =0; j< Field.DEFAULT_MAX_POSN; j++){
				grid[i][j] = new SquareLabel(i,j);
				//Set to null for now
				grid[i][j].addMouseListener(mouseListener);
				this.add(grid[i][j]);
			}
		}
	}

	public void updateLevel(){
		for (int i =0; i < Field.DEFAULT_MAX_ROW; i++){
			Strip strip = level.getField().getStrip()[i];
			for (int j = 0; j < Field.DEFAULT_MAX_ROW; j++){
				grid[i][j].updateSquare(strip.getSquare(j));
				grid[i][j].repaint();
			}
		}
	}
	
	//TODO:: Switch when assets are ready
/*
	public void paintComponent(Graphics g)
	{

		int width = this.getWidth();
		int height = this.getHeight();

		//Draw the field
		for (int j = 0 ; j< Field.DEFAULT_MAX_ROW;j++){
			for (int i = 0; i <Field.DEFAULT_MAX_POSN; i++){
				g.setColor(Color.GREEN);
				g.fillRect(i*100, j*100, 100, 100);
				g.setColor(Color.BLACK);
				g.drawRect(i*100, j*100, 100, 100);

			}

		}
		
		//Draw Plant
		g.drawString("Plant", 30, 50);

		/*		BufferedImage sampleImage = null ;
		try {
			sampleImage = ImageIO.read(new File("grass.jpg"));
		} catch (IOException e) {
		}

		g.drawImage(sampleImage, 0, 0, 100, 100, 0, 0, 100, 100, null);
		 	
	}

*/

}
