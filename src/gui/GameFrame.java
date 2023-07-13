package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import game.GameConstant;

public class GameFrame extends JFrame {

	public GameFrame(GamePanel gamePanel) {
		setTitle("Pac Man");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.add(gamePanel);
		panel.setBackground(GameConstant.BACKGROUND);
		panel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		add(panel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
