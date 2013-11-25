package pvz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 
 * The frame and entry point for the PVZ GUI. The frame contains all the panels for selecting 
 * plants and viewing the game itself
 * 
 * @author Christopher Nguyen / Arzaan irani (Implemented wallnut)
 * 
 */
public class GameFrame extends JFrame implements Observer {

	/**
	 * Option for when the level has finished
	 */
	private String [] options = {"Next Level", "Save and Quit"};

	private JPanel commandPanel, seedPanel, consolePanel, statusPanel;
	/**
	 * The pannel that contains all the game object
	 */
	private GamePanel gamePanel;
	/**
	 * The buttons are make package wide so the controller has access to the buttons 
	 * Buttons that allow user interaction
	 */
	JButton plantButton, doNothingButton, undoButton, redoButton,
	sunflowerButton, peashooterButton,wallnutButton, cancelButton;
	/**
	 * Labels display to user
	 */
	private JLabel sunLabel, scoreLabel, userMessage;
	/**
	 * The game model
	 */
	private GameModel model;
	/**
	 * The game controller
	 */
	private GameController controller;
	/**
	 * Which plant is currently being selected
	 */
	private Plant.Type plantMode;

	/**
	 * Public constructor
	 */
	public GameFrame(GameController controller, GameModel model) {
		super("Plants Vs Zombie");
		this.setLayout(new BorderLayout());
		this.controller = controller;
		// Initialize all the objects
		this.model = model;
		gamePanel = new GamePanel(controller, model);

		commandPanel = new JPanel();
		commandPanel.setLayout(new GridLayout(1, 4));

		// initialize all the command buttons
		plantButton = new JButton("Plant");
		plantButton.addActionListener(controller);
		doNothingButton = new JButton("Do Nothing");
		doNothingButton.addActionListener(controller);
		undoButton = new JButton("Undo");
		undoButton.addActionListener(controller);
		redoButton = new JButton("Redo");
		redoButton.addActionListener(controller);
		commandPanel.add(plantButton);
		commandPanel.add(undoButton);
		commandPanel.add(redoButton);
		commandPanel.add(doNothingButton);

		seedPanel = new JPanel();
		seedPanel.setLayout(new GridLayout(1, 0));

		populateSeedPanel();
		userMessage = new JLabel("");
		consolePanel = new JPanel();
		consolePanel.setLayout(new GridLayout(0, 1));

		statusPanel = new JPanel();
		statusPanel.setLayout(new GridLayout(0, 4));
		statusPanel.add(new JLabel("Sun"));
		sunLabel = new JLabel();
		statusPanel.add(sunLabel);
		statusPanel.add(new JLabel("Score"));
		scoreLabel = new JLabel();
		statusPanel.add(scoreLabel);
		consolePanel.add(statusPanel);
		consolePanel.add(userMessage);

		this.add(commandPanel, BorderLayout.NORTH);

		this.add(gamePanel, BorderLayout.CENTER);
		this.add(consolePanel, BorderLayout.SOUTH);
		this.setSize(140*Field.DEFAULT_MAX_POSN, 700);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		updateLevel();
	}

	/**
	 * initialize the panel displaying all the seed information
	 */
	public void populateSeedPanel() {
		// TODO:: change with scrollable
		sunflowerButton = new JButton("<html>Sunflower<br>sun: "
				+ PlantFactory.getCost(Plant.Type.SUNFLOWER) + "</html>");
		sunflowerButton.addActionListener(controller);
		seedPanel.add(sunflowerButton);
		peashooterButton = new JButton("<html>Peashooter<br>sun: "
				+ PlantFactory.getCost(Plant.Type.PEASHOOTER) + "</html>");
		peashooterButton.addActionListener(controller);
		seedPanel.add(peashooterButton);
		wallnutButton = new JButton("<html>Wallnut<br>sun: "
				+ PlantFactory.getCost(Plant.Type.WALLNUT) + "</html>");
		wallnutButton.addActionListener(controller);
		seedPanel.add(wallnutButton);
		JButton button = new JButton("Cancel");
		button.addActionListener(controller);
		seedPanel.add(button);

		seedPanel.add(new JLabel(""));
		seedPanel.add(new JLabel(""));
		seedPanel.add(new JLabel(""));

	}


	/**
	 * Updates the Game board
	 */
	private void updateLevel() {
		// query the model to update the game panel
		userMessage.setText("");
		gamePanel.updateLevel();
		sunLabel.setText("" + model.getLevel().getField().getTotalSun());
	}

	/**
	 * Hide the seed panel
	 */
	public void hideSeedPanel() {
		this.remove(seedPanel);
		plantMode = null;
		this.add(commandPanel, BorderLayout.NORTH);
		revalidate();
		repaint();
	}

	/**
	 * Show the seed panel
	 */
	public void showSeedPanel() {
		this.remove(commandPanel);
		this.add(seedPanel, BorderLayout.NORTH);
		revalidate();
		repaint();
	}



	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		Player.PlayStatus status = (Player.PlayStatus)arg1;

		switch(status){
		case COOLDOWN_NOT_READY:
			JOptionPane.showMessageDialog(this, "Cooldown not ready");
			break;
		case GAMEOVER:
			updateLevel();
			if (plantMode != null){
				hideSeedPanel();

			}
			int choice = JOptionPane.showConfirmDialog(this, "You have lost.\nWould you like to try the level again?");
			if (choice == 0){
				//restart new game here
			}

			break;
		case INVALID_COMMAND:
			JOptionPane.showMessageDialog(this, "Invalid Command");
			break;
		case INVALID_POSITION:
			JOptionPane.showMessageDialog(this, "You cannot place that there!");
			break;
		case NORMAL:
			updateLevel();
			if (plantMode != null){
				hideSeedPanel();
			}
			break;
		case NOT_ENOUGH_SUN:
			JOptionPane.showMessageDialog(this, "You require more sun points.");
			break;
		case VICTORY:
			updateLevel();
			if (plantMode != null){
				hideSeedPanel();
			}
			JOptionPane.showOptionDialog(this, "Congratulation on beating the level!!" , "Victory!!!", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			break;
		case COMMAND_FAILED:
			JOptionPane.showMessageDialog(this, "Command Failed");
			break;
		default:
			break;


		}
		revalidate();
		repaint();
	}


	/**
	 * Returns the Plant mode
	 * @return plant mode
	 */
	public Plant.Type getPlantMode(){
		return this.plantMode;
	}
	
	/**
	 * Sets the plant mode
	 * @param plantMode
	 */
	public void setPlantMode(Plant.Type plantMode){
		this.plantMode = plantMode;
	}


}
