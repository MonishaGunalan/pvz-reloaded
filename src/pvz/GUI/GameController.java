package pvz.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

import pvz.level.GameModel;
import pvz.level.PlayerCommand;
import pvz.level.PlayerCommand.CommandType;
import pvz.unit.Plant;
/**
 * The controller class in the MVC, it is the entry point for the GUI
 * @author Christopher Nguyen
 *
 */
public class GameController implements ActionListener, MouseListener {
	/**
	 * The game model
	 */
	private GameModel model;
	/**
	 * The game model
	 */
	private GameFrame view;
	public GameController() {
		model = new GameModel();
		view = new GameFrame(this,model);
		model.addObserver(view);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getComponent() instanceof SquareLabel) {
			SquareLabel squareLabel = (SquareLabel) e.getComponent();
			//System.out.println(squareLabel.getRow() + " "
				//	+ squareLabel.getCol());
			//Plant the plant on the square if a plant has been selected
			String s = "";
			Plant.Type plantMode = view.getPlantMode();
			// Do nothing if not in plant mode
			if (plantMode == null) {
				return;
			} else if (plantMode == Plant.Type.SUNFLOWER) {
				s = "SUNFLOWER";

			} else if (plantMode == Plant.Type.PEASHOOTER) {
				s = "PEASHOOTER";
				
			} else if (plantMode == Plant.Type.WALLNUT) {
				s = "WALLNUT";

			}
			// Get the coordinates of the square and tell the model to plant in
			// the location
			play(new PlayerCommand(	PlayerCommand.CommandType.PLANT_SEED, squareLabel.getRow(),	squareLabel.getCol(), s)); 
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			if (e.getSource() == view.plantButton) {
				// Switch to the seed panel
				view.showSeedPanel();
			} else if (e.getSource() == view.doNothingButton) {
				// Play a turn with DO NOTHING
				play(new PlayerCommand(
						PlayerCommand.CommandType.DO_NOTHING, 0, 0, ""));
			} else if ((e.getSource() == view.sunflowerButton)) {
				// Set the selected plant to sunflower
				view.setPlantMode(Plant.Type.SUNFLOWER);
			} else if (e.getSource() == view.peashooterButton) {
				// Set the selected plant to peashooter
				view.setPlantMode(Plant.Type.PEASHOOTER);
			} else if (e.getSource() == view.wallnutButton) {
				// Set the selected plant to wallnut
				view.setPlantMode(Plant.Type.WALLNUT);
			} else if (((JButton) e.getSource()).getText().equals("Cancel")) {
				view.hideSeedPanel();
			} else if(e.getSource() == view.redoButton){
				play(new PlayerCommand(
						PlayerCommand.CommandType.REDO, 0, 0, ""));
			} else if(e.getSource() == view.undoButton){
				play(new PlayerCommand(
						PlayerCommand.CommandType.UNDO, 0, 0, ""));
			}
		}

	}
	
	/**
	 * Play method
	 * @param playerCommand
	 */
	private void play(PlayerCommand playerCommand){
		model.play(playerCommand);
	}


	public static void main (String [] args){
		new GameController();
	}

}
