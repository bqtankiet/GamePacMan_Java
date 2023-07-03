package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import game.Game;
import game.GameConstant;
import input.KeyBoardInput;

public class GamePanel extends JPanel {

	public Game game;

	public GamePanel(Game game) {
		this.game = game;
		setBackground(Color.gray);
		setPreferredSize(new Dimension(600, 600));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				switch (e.getButton()) {
				case MouseEvent.BUTTON1 -> {
					int col = e.getX() / GameConstant.SQUARE;
					int row = e.getY() / GameConstant.SQUARE;
					if (row >= 0 && row < game.map.length && col >= 0 && col < game.map[0].length) {
						game.map[row][col] = 1;
						repaint();
					}
				}
				case MouseEvent.BUTTON3 -> {
					int col = e.getX() / GameConstant.SQUARE;
					int row = e.getY() / GameConstant.SQUARE;
					if (row >= 0 && row < game.map.length && col >= 0 && col < game.map[0].length) {
						game.map[row][col] = 0;
						repaint();
					}
				}
				case MouseEvent.BUTTON2 -> {
					for (int i = 0; i < game.map.length; i++) {
						System.out.print("map[" + i + "] = {");
						for (int j = 0; j < game.map[0].length; j++) {
							System.out.print(game.map[i][j]);
							if (j < game.map[0].length - 1)
								System.out.print(",");
							if (j == game.map[0].length - 1) {
								System.out.print("};");
							}
						}
						System.out.println();
					}
					System.out.println(e.getX() + " "+ e.getY());
				}
				}
			}
		});
		addKeyListener(new KeyBoardInput(game));
		setFocusable(true);
	}

	public void drawMap(Graphics g) {
		for (int i = 0; i < game.map.length; i++) {
			for (int j = 0; j < game.map[0].length; j++) {
				int square = GameConstant.SQUARE;
				g.setColor(Color.black);
//				if (game.map[i][j] == 0)
//					g.drawRect(j * square, i * square, square, square);
				if (game.map[i][j] == 1)
					g.fillRect(j * square, i * square, square, square);
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawMap(g);
		
		game.pacman.update();
		game.pacman.draw(g);
		game.ghost.update();
		game.ghost.draw(g);
	}

}
