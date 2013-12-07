package pvz.GUI;
/**
 * controller handles the event from the GUI for Level Builder. 
 * 
 * @author Monisha Gunalan 100871444
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

import pvz.level.BuilderModel;

public class BuilderController implements ActionListener {
	/**
	 * Builder view
	 */
	private BuilderView view;
	
	/**
	 * Builder Model
	 */
	private BuilderModel model;

	
	public BuilderController() {
		view = new BuilderView("Level Builder", this);
		model = new BuilderModel();
		model.addObserver(view);
	}
	
	/**
	 * Handles the action event form View
	 * @param e: action event 
	 */

	public void actionPerformed(ActionEvent e) {

		if (e.getSource().getClass() == JButton.class) {
			if (e.getActionCommand().equals("Terrain Selection")) {
				String terrainList[] = view.getTerrainList();
				model.setTerrainList(terrainList);
			} else if (e.getActionCommand().equals("NumZombie Selection")) {
				int numZombies[] = view.getNumZombie();
				model.setNumZombies(numZombies);
			} else if (e.getActionCommand().equals("1")) {
				performAction(1);
			} else if (e.getActionCommand().equals("2")) {
				performAction(2);
			} else if (e.getActionCommand().equals("3")) {
				performAction(3);
			} else if (e.getActionCommand().equals("4")) {
				performAction(4);
			} else if (e.getActionCommand().equals("5")) {
				performAction(5);
			} else if (e.getActionCommand().equals("level1")) {
				model.writeToFile("level1");
			} else if (e.getActionCommand().equals("level2")) {
				model.writeToFile("level2");
			} else if (e.getActionCommand().equals("level3")) {
				model.writeToFile("level3");
			} else if (e.getActionCommand().equals("level4")) {
				model.writeToFile("level4");
			} else if (e.getActionCommand().equals("level5")) {
				model.writeToFile("level5");
			}else if(e.getActionCommand().equals("Level Selection")){
				String level[] = view.getLevelSelection();
				model.setLevelsToEdit(level);
			}else if(e.getActionCommand().equals("quit")){
				view.exit();
			}else if(e.getActionCommand().equals("reset")){
				System.out.println("Reset");
				model.reset();
			}
		}

	}

	/**
	 * copies the zombie type and zombie turn into an array 
	 * and pass the array to model
	 * @param rowIndex index of the row
	 */
	public void performAction(int rowIndex) {
		int size = view.getZombieType(rowIndex).length;
		String[][] zType = new String[size][2];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < 2; j++) {
				zType[i][j] = view.getZombieType(rowIndex)[i][j];
			}
		}
		model.setZombieType(zType, rowIndex, size);
	}

	public static void main(String[] args) {
		new BuilderController();
	}

	public BuilderModel getModel() {
		return model;
	}

}
