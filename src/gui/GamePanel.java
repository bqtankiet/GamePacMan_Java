package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import entity.Ghost;
import entity.PacMan;
import game.Game;
import game.GameConstant;
import input.KeyBoardInput;

public class GamePanel extends JPanel {

	public Game game;

	public GamePanel(Game game) {
		this.game = game;
		setBackground(GameConstant.BACKGROUND);
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
					System.out.println(e.getX() + " " + e.getY());
				}
				}
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				System.out.println("x = " + e.getX() / GameConstant.SQUARE + ", y = " + e.getY() / GameConstant.SQUARE);
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
		drawWall(g);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.update();
		drawMap(g);
		drawCharacter(g);
	}

	public void drawCharacter(Graphics g) {
		drawPacman(g);
		drawRedGhost(g);
		drawOrangeGhost(g);
		drawPinkGhost(g);
		drawBlueGhost(g);
//		game.pacman.draw(g);
//		game.redGhost.draw(g);
//		game.orangeGhost.draw(g);
//		game.pinkGhost.draw(g);
//		game.blueGhost.draw(g);
	}

	public void drawWall(Graphics g) {
		int blockSize = GameConstant.SQUARE;
		int wallStroke = GameConstant.WALL_STROKE;

		for (int i = 0; i < game.map.length; i++) {
			for (int j = 0; j < game.map[i].length; j++) {
				// draw walls stroke
				if (game.map[i][j] == 1) {
//					g.setColor(background);
//					g.fillRect(j * blockSize, i * blockSize, blockSize, blockSize);

					// Right side
					if (j + 1 >= game.map[i].length || game.map[i][j + 1] != 1) {
						g.setColor(GameConstant.WALL_COLOR);
						g.fillRect((j + 1) * blockSize - wallStroke, i * blockSize, wallStroke, blockSize);
					}

					// Left side
					if (j - 1 < 0 || game.map[i][j - 1] != 1) {
						g.setColor(GameConstant.WALL_COLOR);
						g.fillRect(j * blockSize, i * blockSize, wallStroke, blockSize);
					}

					// Bottom side
					if (i + 1 >= game.map.length || game.map[i + 1][j] != 1) {
						g.setColor(GameConstant.WALL_COLOR);
						g.fillRect(j * blockSize, (i + 1) * blockSize - wallStroke, blockSize, wallStroke);
					}

					// Top side
					if (i - 1 < 0 || game.map[i - 1][j] != 1) {
						g.setColor(GameConstant.WALL_COLOR);
						g.fillRect(j * blockSize, i * blockSize, blockSize, wallStroke);
					}
				}
			}
		}
	}

	private void drawPacman(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		PacMan pacman = game.pacman;
		BufferedImage image = pacman.currentAnimation[pacman.spriteIndex];

		g2d.drawImage(image, pacman.x, pacman.y, GameConstant.SQUARE, GameConstant.SQUARE, this);
	}

	private void drawRedGhost(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Ghost redGhost = game.redGhost;
		BufferedImage image = redGhost.currentAnimation[redGhost.spriteIndex];

		g2d.drawImage(image, redGhost.x, redGhost.y, GameConstant.SQUARE, GameConstant.SQUARE, this);
	}

	private void drawOrangeGhost(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Ghost orangeGhost = game.orangeGhost;
		BufferedImage image = orangeGhost.currentAnimation[orangeGhost.spriteIndex];

		g2d.drawImage(image, orangeGhost.x, orangeGhost.y, GameConstant.SQUARE, GameConstant.SQUARE, this);
	}

	private void drawPinkGhost(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Ghost pinkGhost = game.pinkGhost;
		BufferedImage image = pinkGhost.currentAnimation[pinkGhost.spriteIndex];

		g2d.drawImage(image, pinkGhost.x, pinkGhost.y, GameConstant.SQUARE, GameConstant.SQUARE, this);
	}

	private void drawBlueGhost(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Ghost blueGhost = game.blueGhost;
		BufferedImage image = blueGhost.currentAnimation[blueGhost.spriteIndex];

		g2d.drawImage(image, blueGhost.x, blueGhost.y, GameConstant.SQUARE, GameConstant.SQUARE, this);
	}
}
