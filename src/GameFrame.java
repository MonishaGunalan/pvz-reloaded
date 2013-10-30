import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GameFrame extends JFrame implements ActionListener {
	JPanel commandPanel, seedPanel, consolePanel;
	GamePanel gamePanel;
	JButton plantButton, doNothingButton;
	
	public GameFrame(String title){
		super(title);
		this.setLayout(new BorderLayout());
		gamePanel = new GamePanel();
		
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

		this.add(commandPanel,BorderLayout.NORTH);

		this.add(gamePanel, BorderLayout.CENTER);
		this.setSize(700,700);
	//	this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	

		//TODO:: done here since it is just an empty shell

	}
	
	public void populateSeedPanel(){
		//TODO:: change with scrollable
		JButton sunButton = new JButton("Sunflower");
		sunButton.addActionListener(this);
		seedPanel.add(sunButton);
		seedPanel.add(new JButton("Peashooter"));
		seedPanel.add(new JLabel(""));
		seedPanel.add(new JLabel(""));
		seedPanel.add(new JLabel(""));
		
	}
	

	
	
	public static void main (String [] args){
		new GameFrame("ABC");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == plantButton){
			this.remove(commandPanel);
			this.add(seedPanel,BorderLayout.NORTH);
			revalidate();
			repaint();

		} else{
			this.remove(seedPanel);
			this.add(commandPanel,BorderLayout.NORTH);
			revalidate();
			repaint();
		}
		
	}
	

}
