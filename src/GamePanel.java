import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class GamePanel extends JPanel {




	//TODO:: Switch when assets are ready

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
		 */	
	}



}
