package pvz.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.io.File;
import java.util.Scanner;

import javax.swing.*;

import pvz.level.GameModel;
import pvz.level.PlayerCommand;
import pvz.unit.Plant;
/**
 * The controller class in the MVC, it is the entry point for the GUI
 * @author Christopher Nguyen
 *
 */
public class GameController implements ActionListener, MouseListener, WindowListener {
	/**
	 * The game model
	 */
	private GameModel model;
	/**
	 * The game model
	 */
	private GameFrame view;
	public GameController() {
		int value = JOptionPane.showConfirmDialog(null, "Would you like to play in real time?", "How would you like to play", JOptionPane.YES_NO_OPTION);
		boolean realTime = (value == 0);

		Object[] options = {"New Game",
			"Load Last",
			"Cancel"};
		int n = JOptionPane.showOptionDialog(null,
				"Start a new game?",
				"Select Game",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				null);
		boolean startNew = (n == 0);

		model = new GameModel(realTime, startNew);
		view = new GameFrame(this,model);
		model.addObserver(view);
	}

	//public String getPlayerName() {
		//String s = "";
		//// Temporary list to hold names
		//List playerNames = new ArrayList();
		//File f = new File(GameModel.PLAYERS_FILENAME);
		//// Create the file if it doesn't exsit
		//try {
			//f.createNewFile();
		//} catch(Exception e) {
		//}

		//// Read file into list
		//try {
			//Scanner sc = new Scanner(f);
			//while(sc.hasNext()) {
				//// Get a name
				//String currentName = sc.nextLine();
				//// If the current name isn't a duplicate
				//if (!playerNames.contains(currentName)) {
					//playerNames.add(currentName);
				//}
			//}
		//} catch(Exception e) {
			//System.out.println("bleh");
			//e.printStackTrace();
		//}

		//JList nameList = new JList(playerNames);
		//nameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		////nameList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		//nameList.setVisibleRowCount(-1);
		//JScrollPane listScroller = new JScrollPane(nameList);
		//listScroller.setPreferredSize(new Dimension(250, 80));

		//// Panel for buttons
		//JPanel buttonPanel = new JPanel();
		//JButton b1 = new JButton("New");
		//b1.setName("ADD_NEW_PLAYER");
		//JButton b2 = new JButton("Delete");
		//b2.setName("DELETE_PLAYER");
		//JButton b3 = new JButton("Select");
		//b3.setName("SELECT_PLAYER");

		//return s;
	//}



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
			view.hideSeedPanel();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			if (e.getSource() == view.getPlantButton()) {
				// Switch to the seed panel
				view.showSeedPanel();
			} else if (e.getSource() == view.getDoNothingButton()) {
				// Play a turn with DO NOTHING
				play(new PlayerCommand(
						PlayerCommand.CommandType.DO_NOTHING, 0, 0, ""));
			} else if ((e.getSource() == view.getSunflowerButton())) {
				// Set the selected plant to sunflower
				view.setPlantMode(Plant.Type.SUNFLOWER);
			} else if (e.getSource() == view.getPeashooterButton()) {
				// Set the selected plant to peashooter
				view.setPlantMode(Plant.Type.PEASHOOTER);
			} else if (e.getSource() == view.getWallnutButton()) {
				// Set the selected plant to wallnut
				view.setPlantMode(Plant.Type.WALLNUT);
			} else if (((JButton) e.getSource()).getText().equals("Cancel")) {
				view.hideSeedPanel();
			} else if (e.getSource() ==view.getPauseButton()){
				//Pause button has been selected
				model.stopTimer();
				view.getResumeButton().setEnabled(true);
				view.getPauseButton().setEnabled(false);
				view.revalidate();
				view.repaint();
			} else if (e.getSource() ==view.getResumeButton()){
				//Resume button has been selected
				model.startTimer();
				view.getPauseButton().setEnabled(true);
				view.getResumeButton().setEnabled(false);
				view.revalidate();
				view.repaint();
			} else if(e.getSource() == view.getRedoButton()){
				//redo button was selected
				play(new PlayerCommand(
						PlayerCommand.CommandType.REDO, 0, 0, ""));
			} else if(e.getSource() == view.getSaveButton()){
				model.stopTimer();
				int value = JOptionPane.showConfirmDialog(null, "This will overwrite previous saves, are you sure?",
						"Save game", JOptionPane.YES_NO_OPTION);
				if (value == 0) {
					this.model.save();
				}
				model.startTimer();
			} else if(e.getSource() == view.getLoadButton()){
				model.stopTimer();
				int value = JOptionPane.showConfirmDialog(null, "You will lose current progress on load, are you sure?",
						"Load game", JOptionPane.YES_NO_OPTION);
				if (value == 0) {
					this.model.load();
				}
				view.updateLevel();
				model.startTimer();
			} else if(e.getSource() == view.getUndoButton()){
				//undo is selected
				//pause the game if it is being played in real time
				if (model.isRealTime() && view.getPauseButton().isEnabled()){
					view.getPauseButton().doClick();
				}
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

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent arg0) {
		//stop the timer before quitting
		if (arg0.getComponent().getClass() == view.getClass()){
			model.stopTimer();
		} 
	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		//model.save();
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
