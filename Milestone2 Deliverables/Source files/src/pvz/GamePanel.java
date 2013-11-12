package pvz;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 * The Panel displaying the contents of the level
 * @author Christopher Nguyen
 *
 */
public class GamePanel extends JPanel {

	/**
	 * The grid of square label
	 */
	private SquareLabel [][] grid;
	/**
	 * The model of the panel
	 */
	private Level level;

	/**
	 * Creates the grid and assigns a mouseListener to the labels
	 * @param mouseListener
	 * @param level
	 */
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

	/**
	 * Update the grid based on the model
	 */
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
