import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;


public class SquareLabel extends JLabel {
	//TODO:: have list of objects here
	int row;
	int col;
	
	public SquareLabel(int row, int col){
		this.row = row;
		this.col = col;
	}
	
	
	public void paintComponent(Graphics g)
	{
		//TODO:: change default of grass
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, this.getWidth(), this.getHeight());
	}
	
	public int getRow(){
		return row;
	}
	
	public int getCol(){
		return col;
	}

}
