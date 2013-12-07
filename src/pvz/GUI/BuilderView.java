package pvz.GUI;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BuilderView extends JFrame implements Observer {

	public static final int ROWSIZE = 5;
	public static final int NUM_LEVELS = 5;
	private JComboBox[] terrainInput;
	private JComboBox[] zombieTypes;
	private JTextField[] numZombie, zombieTurn;
	private JButton submitLevel, submitTerrain, submitNumZombie, submitZombies,
			submitWrite, quit, reset;
	private JPanel gridPanel1;
	private BuilderController controller;
	private int[] numberOfZombies;
	private String[] editLevels;
	private JCheckBox level1, level2, level3, level4, level5;
	private JCheckBox[] levelList;
	private String currentLevel;
	private int numFrame;
	private JLabel levelName;
	//private BorderLayout borderLayout;

	public BuilderView(String title, BuilderController controller) {
		super(title);
		this.currentLevel = null;
		this.controller = controller;
		gridPanel1 = new JPanel();
		createLevelSelectionFrame();
		numFrame = 0;
		
	}

	public void createLevelSelectionFrame() {

		gridPanel1.setLayout(new GridLayout(6, 1));
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		gridPanel1.add(new JLabel("Choose Levels to Build"));
		levelList = new JCheckBox[NUM_LEVELS];

		for (int i = 0; i < NUM_LEVELS; i++) {
			levelList[i] = new JCheckBox("Level " + (i+1));
			levelList[i].setSelected(false);
			gridPanel1.add(levelList[i]);
		}

		submitLevel = new JButton("Submit");
		submitLevel.addActionListener(controller);
		submitLevel.setActionCommand("Level Selection");

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(gridPanel1, BorderLayout.CENTER);
		this.add(submitLevel, BorderLayout.SOUTH);
		setSize(300, 300);
		setVisible(true);
	}

	public String[] getLevelSelection() {
		String l = "";
		for (int i = 0; i < NUM_LEVELS; i++) {
			if (levelList[i].isSelected()) {
				l += "level" + (i + 1) + " ";
			}
		}
		System.out.println("Edit Levels: " + l);
		editLevels = l.split(" ");
		System.out.println(editLevels.length);
		return editLevels;
	}

	public void createTerrainSelectionFrame(String level) {
		if(numFrame >0){
			this.remove(submitWrite);
			this.remove(levelName);
		}
		numFrame++;
		System.out.println("_________Select Terrian_______ ");
		gridPanel1.setLayout(new GridLayout(5, 2));
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		String[] terrainOptions = { "GRASS", "MUD" };

		terrainInput = new JComboBox[ROWSIZE];

		for (int i = 0; i < ROWSIZE; i++) {
			gridPanel1.add(new JLabel("Row " + (i + 1) + ":"));
			terrainInput[i] = new JComboBox(terrainOptions);
			gridPanel1.add(terrainInput[i]);
			terrainInput[i].setSelectedIndex(1);
		}

		submitTerrain = new JButton("Submit");
		submitTerrain.addActionListener(controller);
		submitTerrain.setActionCommand("Terrain Selection");
		levelName = new JLabel(level);
		this.add(levelName, BorderLayout.NORTH);
		this.add(gridPanel1, BorderLayout.CENTER);
		this.add(submitTerrain, BorderLayout.SOUTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		gridPanel1.setVisible(true);
		setSize(300, 300);
		setVisible(true);
	}

	public String[] getTerrainList() {

		String[] terrainList = { "", "", "", "", "" };
		for (int i = 0; i < ROWSIZE; i++) {
			terrainList[i] = (String) terrainInput[i].getSelectedItem();
		}
		return terrainList;
	}

	public void createnumZombieFrame(String[] terrain) {
		gridPanel1.setLayout(new GridLayout(5, 3));
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		numZombie = new JTextField[ROWSIZE];

		for (int i = 0; i < ROWSIZE; i++) {
			gridPanel1.add(new JLabel("Row " + (i + 1) + ":"));
			gridPanel1.add(new JLabel(terrain[i]));

			if (terrain[i].equals("GRASS")) {
				numZombie[i] = new JTextField();
				gridPanel1.add(numZombie[i]);
			} else {
				gridPanel1.add(new JLabel(""));
			}
		}

		submitNumZombie = new JButton("Submit");
		submitNumZombie.addActionListener(controller);
		submitNumZombie.setActionCommand("NumZombie Selection");

		this.add(gridPanel1, BorderLayout.CENTER);
		this.add(submitNumZombie, BorderLayout.SOUTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		setSize(300, 300);
		setVisible(true);

	}

	public int[] getNumZombie() {

		int numZombieList[] = { 0, 0, 0, 0, 0 };
		for (int i = 0; i < ROWSIZE; i++) {
			if (numZombie[i] != null) {
				try{
					numZombieList[i] = Integer.parseInt(numZombie[i].getText());
				} catch (NumberFormatException e) {
				   System.out.println("Entered Invalid Input. Set to Default value of '0'");
				 }
			} else {
				numZombieList[i] = 0;
			}
		}
		return numZombieList;
	}

	public void createInputZombies(int startIndex) {

		for (int i = startIndex; i < ROWSIZE; i++) {
			if (numberOfZombies[i] != 0) {
				getInputZombies(numberOfZombies[i], i + 1);
				return;
			}
		}
		WriteToFileButton();
		System.out.println("All required input is received");
	}

	private void getInputZombies(int numRows, int rowIndex) {
		gridPanel1.setLayout(new GridLayout(numRows + 2, 3));
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		String[] zombieOptions = { "normal", "flag", "cone" };

		gridPanel1.add(new JLabel("Row " + rowIndex + ":"));
		gridPanel1.add(new JLabel(""));
		gridPanel1.add(new JLabel(""));
		gridPanel1.add(new JLabel("Zombies"));
		gridPanel1.add(new JLabel("Zombie Type"));
		gridPanel1.add(new JLabel("Zombie Turn"));

		zombieTypes = new JComboBox[numRows];
		zombieTurn = new JTextField[numRows];

		for (int i = 0; i < numRows; i++) {
			gridPanel1.add(new JLabel("Zombie " + (i + 1) + ":"));
			zombieTypes[i] = new JComboBox(zombieOptions);
			gridPanel1.add(zombieTypes[i]);
			zombieTypes[i].setSelectedIndex(0);
			zombieTurn[i] = new JTextField();
			gridPanel1.add(zombieTurn[i]);
		}

		submitZombies = new JButton("Submit");
		submitZombies.addActionListener(controller);
		submitZombies.setActionCommand(rowIndex + "");
		this.add(gridPanel1, BorderLayout.CENTER);
		this.add(submitZombies, BorderLayout.SOUTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// setSize(300, 300);
		setVisible(true);
		this.pack();

	}

	public String[][] getZombieType(int rowNum) {
		int numZ = numberOfZombies[rowNum - 1];
		int testTurn = 1;
		String[][] type = new String[numZ][2];
		for (int i = 0; i < numZ; i++) {
			type[i][0] = (String) zombieTypes[i].getSelectedItem();
			try{
				testTurn = Integer.parseInt(zombieTurn[i].getText());				
			} catch (NumberFormatException e) {
			   System.out.println("Entered Invalid Input. Set turn to Default value of '1'");   
			}			
			type[i][1] = ""+testTurn;

		}
		return type;
	}

	public void WriteToFileButton() {
		gridPanel1.setVisible(false);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		submitWrite = new JButton("Write to File");
		submitWrite.addActionListener(controller);
		submitWrite.setActionCommand(currentLevel);
		this.add(submitWrite, BorderLayout.SOUTH);
		setSize(200, 200);
		
		setVisible(true);
	}
	
	public void exit(){
		this.dispose();
	}
	
	public void createEndFrame(){
		quit = new JButton("Quit");
		reset = new JButton("Reset");
		quit.addActionListener(controller);
		quit.setActionCommand("quit");
		reset.addActionListener(controller);
		reset.setActionCommand("reset");
		this.setLayout(new BorderLayout());
		this.add(quit,  BorderLayout.EAST);
		this.add(reset, BorderLayout.WEST);
		this.pack();
		setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("update");
		this.setVisible(false);
		gridPanel1.removeAll();
		String s = (String) arg;
		if (arg.equals("level1")) {
			this.currentLevel = "level1";
			submitLevel.setVisible(false);
			System.out.println("create frame "+ arg);
			createTerrainSelectionFrame("level1");

		} else if (arg.equals("level2")) {
			this.currentLevel = "level2";
			submitLevel.setVisible(false);
			System.out.println("create frame "+ arg);
			createTerrainSelectionFrame("level2");

		} else if (arg.equals("level3")) {
			this.currentLevel = "level3";
			submitLevel.setVisible(false);
			System.out.println("create frame "+ arg);
			createTerrainSelectionFrame("level3");
		
		} else if (arg.equals("level4")) {
			this.currentLevel = "level4";
			submitLevel.setVisible(false);
			System.out.println("create frame "+ arg);
			createTerrainSelectionFrame("level4");
		
		} else if (arg.equals("level5")) {
			this.currentLevel = "level5";
			submitLevel.setVisible(false);
			System.out.println("create frame "+ arg);
			createTerrainSelectionFrame("level5");
		
		} else if (arg.equals("Terrain List")) {
			submitTerrain.setVisible(false);
			String terrains[] = controller.getModel().getTerrianList();
			for (int i = 0; i < terrains.length; i++) {
				System.out.println(terrains[i]);
			}
			createnumZombieFrame(terrains);
		} else if (arg.equals("Num Zombies")) {
			submitNumZombie.setVisible(false);
			numberOfZombies = controller.getModel().getNumZombies();
			createInputZombies(0);

		} else if (arg.equals("row1")) {
			System.out.println(arg);
			System.out.println("row1");
			submitZombies.setVisible(false);
			createInputZombies(1);

		} else if (arg.equals("row2")) {
			System.out.println(arg);
			submitZombies.setVisible(false);
			createInputZombies(2);

		} else if (arg.equals("row3")) {
			System.out.println(arg);
			submitZombies.setVisible(false);
			createInputZombies(3);

		} else if (arg.equals("row4")) {
			System.out.println(arg);
			submitZombies.setVisible(false);
			createInputZombies(4);

		} else if (arg.equals("row5")) {
			System.out.println(arg);
			submitZombies.setVisible(false);
			createInputZombies(5);
		} else if(arg.equals("Done Editing")){
			System.out.println(arg);
			this.remove(submitWrite);
			this.remove(levelName);
			createEndFrame();
		} else if(arg.equals("Reset")){
			this.dispose();
		}
	}
}
