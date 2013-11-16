package pvz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This is the main entry point for the PVZ GUI
 * 
 * @author Christopher Nguyen
 * 
 */
public class GameFrame extends JFrame implements ActionListener, MouseListener, Observer {
	private JPanel commandPanel, seedPanel, consolePanel, statusPanel;
	/**
	 * The panel that contains all the game object
	 */
	private GamePanel gamePanel;
	/**
	 * Buttons that allow user intraction
	 */
	private JButton plantButton, doNothingButton, undoButton, redoButton,
			sunflowerButton, peashooterButton, cancelButton;
	/**
	 * Labels display to user
	 */
	private JLabel sunLabel, scoreLabel, userMessage;
	/*
	 * The game model
	 */
	private GameModel model;
	/*
	 * Which plant is currently being selected
	 */
	private Plant.Type plantMode;

	/**
	 * Public constructor
	 */
	public GameFrame() {
		super("Plants Vs Zombie");
		this.setLayout(new BorderLayout());

		// Initialize all the objects
		model = new GameModel();
		gamePanel = new GamePanel(this, model.getLevel());
		model.addObserver(this);
		commandPanel = new JPanel();
		commandPanel.setLayout(new GridLayout(1, 4));

		// initialize all the command buttons
		plantButton = new JButton("Plant");
		plantButton.addActionListener(this);
		doNothingButton = new JButton("Do Nothing");
		doNothingButton.addActionListener(this);
		undoButton = new JButton("Undo");
		undoButton.setEnabled(false);
		redoButton = new JButton("Redo");
		redoButton.setEnabled(false);
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
		this.setSize(700, 700);
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
		sunflowerButton.addActionListener(this);
		seedPanel.add(sunflowerButton);
		peashooterButton = new JButton("<html>Peashooter<br>sun: "
				+ PlantFactory.getCost(Plant.Type.PEASHOOTER) + "</html>");
		peashooterButton.addActionListener(this);
		seedPanel.add(peashooterButton);
		JButton button = new JButton("Cancel");
		button.addActionListener(this);
		seedPanel.add(button);

		seedPanel.add(new JLabel(""));
		seedPanel.add(new JLabel(""));

	}

	public static void main(String[] args) {
		new GameFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() instanceof JButton) {
			if (e.getSource() == plantButton) {
				// Switch to the seed panel
				this.remove(commandPanel);
				this.add(seedPanel, BorderLayout.NORTH);
			} else if (e.getSource() == doNothingButton) {
				// Play a turn with DO NOTHING
				model.play(new PlayerCommand(
						PlayerCommand.CommandType.DO_NOTHING, 0, 0, ""));
				//updateLevel();
			} else if ((e.getSource() == sunflowerButton)) {
				// Set the selected plant to sunflower
				plantMode = Plant.Type.SUNFLOWER;
			} else if (e.getSource() == peashooterButton) {
				// Set the selected plant to peashooter
				plantMode = Plant.Type.PEASHOOTER;
			} else if (((JButton) e.getSource()).getText().equals("Cancel")) {
				plantMode = null;
				hideSeedPanel();
			}
		}
		revalidate();
		repaint();

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
		this.add(commandPanel, BorderLayout.NORTH);
		revalidate();
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		if (e.getComponent() instanceof SquareLabel) {
			SquareLabel squareLabel = (SquareLabel) e.getComponent();
			System.out.println(squareLabel.getRow() + " "
					+ squareLabel.getCol());
			String s = "";
			// Do nothing if not in plant mode
			if (plantMode == null) {
				return;
			} else if (plantMode == Plant.Type.SUNFLOWER) {
				s = "SUNFLOWER";

			} else if (plantMode == Plant.Type.PEASHOOTER) {
				s = "PEASHOOTER";

			}
			// Get the coordinates of the square and tell the model to plant in
			// the location
			if (model.play(new PlayerCommand(
					PlayerCommand.CommandType.PLANT_SEED, squareLabel.getRow(),
					squareLabel.getCol(), s))) {
				// if plant is successful update the board
			//	updateLevel();
				hideSeedPanel();
				plantMode = null;
			} else {
				// TODO::Update the model with more meaningful error message
				userMessage.setText("Error");
			}
			revalidate();
			repaint();

		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		updateLevel();
	}

}
