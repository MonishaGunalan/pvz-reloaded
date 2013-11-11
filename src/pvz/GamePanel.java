package pvz;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import javax.swing.JPanel;


public class GamePanel extends JPanel {

	
	private SquareLabel [][] grid;
	private Level level;

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
	

}
