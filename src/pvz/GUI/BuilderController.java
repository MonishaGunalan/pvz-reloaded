package pvz.GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

import pvz.level.BuilderModel;

public class BuilderController implements ActionListener {

	private BuilderView view;
	private BuilderModel model;

	public BuilderController() {
		view = new BuilderView("Level Builder", this);
		model = new BuilderModel();
		model.addObserver(view);
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("Action Performed");
		System.out.println(e.getActionCommand());

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
				System.out.println("level Selection- Action Performed");
				model.setLevelsToEdit(level);
			}else if(e.getActionCommand().equals("quit")){
				view.exit();
			}else if(e.getActionCommand().equals("reset")){
				System.out.println("Reset");
				model.reset();
			}
		}

	}

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
		// TODO Auto-generated method stub
		return model;
	}

}
