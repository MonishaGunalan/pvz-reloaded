package pvz.GUI;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

import pvz.level.Field;
import pvz.level.GameModel;
import pvz.level.Strip;

/**
 * The Panel displaying the contents of the level
 * @author Christopher Nguyen
 *
 */
public class GamePanel extends JPanel {
	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The grid of square label
	 */
	private SquareLabel [][] grid;
	/**
	 * The model of the panel
	 */
	private GameModel model;

	/**
	 * Creates the grid and assigns a mouseListener to the labels
	 * @param mouseListener
	 * @param level
	 */
	public GamePanel(MouseListener mouseListener, GameModel model){
		super();
		this.model = model;
		this.setLayout(new GridLayout(Field.DEFAULT_MAX_ROW,Field.DEFAULT_MAX_POSN));
		grid = new SquareLabel[Field.DEFAULT_MAX_ROW][Field.DEFAULT_MAX_POSN];
		
		for (int i =0; i <Field.DEFAULT_MAX_ROW; i++){
			Strip strip = model.getLevel().getField().getStrip()[i];
			for (int j =0; j< Field.DEFAULT_MAX_POSN; j++){
				grid[i][j] = new SquareLabel(i,j,strip.getSquare(j));
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
			Strip strip = model.getLevel().getField().getStrip()[i];
			for (int j = 0; j < Field.DEFAULT_MAX_POSN; j++){
				grid[i][j].updateSquare(strip.getSquare(j));
				grid[i][j].repaint();
			}
		}
	}
	

}
