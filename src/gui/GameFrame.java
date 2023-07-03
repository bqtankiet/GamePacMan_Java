package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {

	public GameFrame(GamePanel gamePanel) {
		setTitle("Pac Man");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.add(gamePanel);
		panel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		add(panel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
