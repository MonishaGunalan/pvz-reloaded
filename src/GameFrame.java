import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GameFrame extends JFrame implements ActionListener, MouseListener {
	JPanel commandPanel, seedPanel, consolePanel, statusPanel;
	GamePanel gamePanel;
	JButton plantButton, doNothingButton;
    JLabel sunLabel, scoreLabel;
	GameModel model;
	int plantMode;

	public GameFrame(String title){
		super(title);
		this.setLayout(new BorderLayout());

		model = new GameModel();
		gamePanel = new GamePanel(this,model.getLevel());

		commandPanel = new JPanel();
		commandPanel.setLayout(new GridLayout(1,4));

		plantButton = new JButton("Plant");
		plantButton.addActionListener(this);
		doNothingButton = new JButton("Do Nothing");
		doNothingButton.addActionListener(this);

		commandPanel.add(plantButton);
		commandPanel.add(new JButton("Undo"));
		commandPanel.add(new JButton("Redo"));
		commandPanel.add(doNothingButton);

		seedPanel = new JPanel();
		seedPanel.setLayout(new GridLayout(1,0));

		populateSeedPanel();
		
		
		statusPanel = new JPanel();
		statusPanel.setLayout(new GridLayout(0,4));
		statusPanel.add(new JLabel("Sun"));
		sunLabel = new JLabel();
		statusPanel.add(sunLabel);
		statusPanel.add(new JLabel("Score"));
		scoreLabel = new JLabel();
		statusPanel.add(scoreLabel);
		this.add(commandPanel,BorderLayout.NORTH);

		this.add(gamePanel, BorderLayout.CENTER);
		this.add(statusPanel, BorderLayout.SOUTH);
		this.setSize(700,700);
		//	this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		updateLevel();
	}

	public void populateSeedPanel(){
		//TODO:: change with scrollable
		JButton sunButton = new JButton("Sunflower");
		sunButton.addActionListener(this);
		seedPanel.add(sunButton);
		JButton button =new JButton("Cancel");
		button.addActionListener(this);
		seedPanel.add(new JButton("Peashooter"));
		seedPanel.add(button);
		seedPanel.add(new JLabel(""));
		seedPanel.add(new JLabel(""));

	}




	public static void main (String [] args){
		new GameFrame("ABC");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() instanceof JButton){
			if (e.getSource() == plantButton){
				this.remove(commandPanel);
				this.add(seedPanel,BorderLayout.NORTH);
				revalidate();
				repaint();

			} else if (e.getSource() == doNothingButton) {
				model.play(new PlayerCommand(PlayerCommand.CommandType.DO_NOTHING,0,0,""));
				updateLevel();
				
			} else if (((JButton)e.getSource()).getText().equals("Sunflower") ){
				plantMode = 1;

			} else if (((JButton)e.getSource()).getText().equals("Cancel") ) {
				hideSeedPanel();
			}
		}
		revalidate();
		repaint();

	}
	

	private void updateLevel() {
		
		gamePanel.updateLevel();
		sunLabel.setText(""+ model.getLevel().getField().getTotalSun());
	}

	public void hideSeedPanel(){
		this.remove(seedPanel);
		this.add(commandPanel,BorderLayout.NORTH);
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
		// TODO Auto-generated method stub

		if (e.getComponent() instanceof SquareLabel){
			SquareLabel squareLabel = (SquareLabel)e.getComponent();
			System.out.println(squareLabel.getRow() + " " + squareLabel.getCol());
			if (plantMode != 0){
				hideSeedPanel();
			}
			if (plantMode == 1){
				if (model.play(new PlayerCommand(PlayerCommand.CommandType.PLANT_SEED,squareLabel.getRow(),squareLabel.getCol(),"SUNFLOWER"))){
					updateLevel();
				}
			}
			revalidate();
			repaint();

		}
	}


}
