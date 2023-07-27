package game;

import java.util.ArrayList;
import java.util.List;

import entity.BlueGhost;
import entity.Character;
import entity.Ghost;
import entity.OrangeGhost;
import entity.PacMan;
import entity.PinkGhost;
import entity.RedGhost;
import gui.GameFrame;
import gui.GamePanel;

public class Game {
	public PacMan pacman;
	public Ghost redGhost, orangeGhost, blueGhost, pinkGhost;
	public GamePanel gamePanel;
	public int[][] map = new int[30][30];
	public int second;
	public boolean checker;
	public boolean isFrightened;
	public int frightenedStartTime;

	public Game() {
		initMap();
		pacman = new PacMan(this);
		redGhost = new RedGhost(this);
		orangeGhost = new OrangeGhost(this);
		blueGhost = new BlueGhost(this);
		pinkGhost = new PinkGhost(this);
	}

	public void initMap() {
//		map[0] = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
//		map[1] = new int[] { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
//		map[2] = new int[] { 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1 };
//		map[3] = new int[] { 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1 };
//		map[4] = new int[] { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
//		map[5] = new int[] { 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1 };
//		map[6] = new int[] { 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1 };
//		map[7] = new int[] { 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1 };
//		map[8] = new int[] { 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0 };
//		map[9] = new int[] { 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0 };
//		map[10] = new int[] { 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0,
//				0 };
//		map[11] = new int[] { 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0,
//				0 };
//		map[12] = new int[] { 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0,
//				0 };
//		map[13] = new int[] { 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1,
//				1 };
//		map[14] = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
//				0 };
//		map[15] = new int[] { 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1,
//				1 };
//		map[16] = new int[] { 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0,
//				0 };
//		map[17] = new int[] { 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0,
//				0 };
//		map[18] = new int[] { 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0,
//				0 };
//		map[19] = new int[] { 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0,
//				0 };
//		map[20] = new int[] { 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0,
//				0 };
//		map[21] = new int[] { 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1,
//				1 };
//		map[22] = new int[] { 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
//				1 };
//		map[23] = new int[] { 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0,
//				1 };
//		map[24] = new int[] { 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0,
//				1 };
//		map[25] = new int[] { 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1,
//				1 };
//		map[26] = new int[] { 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0,
//				1 };
//		map[27] = new int[] { 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0,
//				1 };
//		map[28] = new int[] { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//				1 };
//		map[29] = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
//				1 };
		map[0] = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
		map[1] = new int[] { 1, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 1 };
		map[2] = new int[] { 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1 };
		map[3] = new int[] { 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1 };
		map[4] = new int[] { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
		map[5] = new int[] { 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1 };
		map[6] = new int[] { 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1 };
		map[7] = new int[] { 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1 };
		map[8] = new int[] { 2, 2, 2, 2, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 2, 2, 1, 0, 0, 0, 1, 0, 1, 0, 1, 2, 2, 2, 2 };
		map[9] = new int[] { 2, 2, 2, 2, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 2, 2, 1, 0, 1, 1, 1, 0, 1, 0, 1, 2, 2, 2, 2 };
		map[10] = new int[] { 2, 2, 2, 2, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 2, 2, 1, 0, 1, 0, 0, 0, 1, 0, 1, 2, 2, 2, 2 };
		map[11] = new int[] { 2, 2, 2, 2, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 2, 2, 2, 2 };
		map[12] = new int[] { 2, 2, 2, 2, 1, 0, 1, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 1, 0, 1, 2, 2, 2, 2 };
		map[13] = new int[] { 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 2, 1, 1, 1, 2, 2, 1, 1, 1, 2, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1 };
		map[14] = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 2, 1, 2, 2, 2, 2, 2, 2, 1, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 };
		map[15] = new int[] { 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1 };
		map[16] = new int[] { 2, 2, 2, 2, 1, 0, 1, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 1, 0, 1, 2, 2, 2, 2 };
		map[17] = new int[] { 2, 2, 2, 2, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 2, 2, 2, 2 };
		map[18] = new int[] { 2, 2, 2, 2, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 2, 2, 2, 2 };
		map[19] = new int[] { 2, 2, 2, 2, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 2, 2, 2, 2 };
		map[20] = new int[] { 2, 2, 2, 2, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 2, 2, 2, 2 };
		map[21] = new int[] { 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1 };
		map[22] = new int[] { 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1 };
		map[23] = new int[] { 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1 };
		map[24] = new int[] { 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1 };
		map[25] = new int[] { 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1 };
		map[26] = new int[] { 1, 0, 0, 0, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 0, 0, 0, 1 };
		map[27] = new int[] { 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1 };
		map[28] = new int[] { 1, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 1 };
		map[29] = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
	}

	public void run() {
		this.gamePanel = new GamePanel(this);
		new GameFrame(gamePanel);
		// Start game loop
		new Thread(() -> {
			long nanoSecond = 1_000_000_000;
			long lastTime = System.nanoTime();
			long lastCheckFps = System.currentTimeMillis();
			int fps = 60;
			double timePerFrame = nanoSecond / fps;
			double check = 0;
			int frame = 0;
			while (true) {
				long currentTime = System.nanoTime();
				check = (currentTime - lastTime) / timePerFrame;
				if (check >= 1) {
					gamePanel.repaint();
					check--;
					lastTime = currentTime;
					frame++;
				}
				if (System.currentTimeMillis() - lastCheckFps >= 1000) {
					lastCheckFps = System.currentTimeMillis();
					second++;
					System.out.printf("Time: %d:%02d | FPS: %d \n", second / 60, second % 60, frame);
					frame = 0;
				}
			}
		}).run();
	}

	public void update() {
		System.out.println(redGhost.mode);
		manageGhostMode();
		checkGame();
		pacman.update();
		redGhost.update();
		orangeGhost.update();
		pinkGhost.update();
		blueGhost.update();
	}

	private void checkGame() {
		ArrayList<Ghost> ghosts = new ArrayList<>();
		ghosts.add(redGhost);
		ghosts.add(orangeGhost);
		ghosts.add(blueGhost);
		ghosts.add(pinkGhost);
		for (Ghost ghost : ghosts) {
			if (checkCollision(pacman, ghost)) {
				if (ghost.mode == GameConstant.FRIGHTENED) {
					ghost.die();
				} else {
					pacman.die();
				}
			}
		}
//		if (checkCollision(pacman, redGhost)) {
//			if (redGhost.mode == GameConstant.FRIGHTENED) {
//				redGhost.die();
//			} else {
//				pacman.die();
//			}
//		}
//		if (checkCollision(pacman, blueGhost)) {
//			if (blueGhost.mode == GameConstant.FRIGHTENED) {
//				blueGhost.die();
//			} else {
//				pacman.die();
//
//			}
//		}
//		if (checkCollision(pacman, pinkGhost)) {
//			if (pinkGhost.mode == GameConstant.FRIGHTENED) {
//				pinkGhost.die();
//			} else {
//				pacman.die();
//			}
//		}
//		if (checkCollision(pacman, orangeGhost)) {
//			if (orangeGhost.mode == GameConstant.FRIGHTENED) {
//				orangeGhost.die();
//			} else {
//				pacman.die();
//			}
//		}
	}

	private boolean checkCollision(Character character1, Character character2) {
		return (character1.getCenterPoint()[0] / 20 == character2.getCenterPoint()[0] / 20) && (character1.getCenterPoint()[1] / 20 == character2.getCenterPoint()[1] / 20);
	}

	public void manageGhostMode() {

		if (isFrightened) {
			startFrightened();
		} else {

			if (second < 2) {
				return;

			}
			if (second == 2) {
				redGhost.setMode(GameConstant.SCATTER);
				orangeGhost.setMode(GameConstant.SCATTER);
				blueGhost.setMode(GameConstant.SCATTER);
				pinkGhost.setMode(GameConstant.SCATTER);
			}
			// RedGhost
			if (second % 60 == 10) {
				redGhost.setMode(GameConstant.CHASE);
			}
			if (second % 60 == 30) {
				redGhost.setMode(GameConstant.SCATTER);
			}
			if (second % 60 == 50) {
				redGhost.setMode(GameConstant.CHASE);
			}
			if (second % 60 == 0) {
				redGhost.setMode(GameConstant.SCATTER);
			}
			// OrangeGhost
			if (second % 60 == 15) {
				orangeGhost.setMode(GameConstant.CHASE);
			}
			if (second % 60 == 50) {
				orangeGhost.setMode(GameConstant.SCATTER);
			}
			// BlueGhost
			if (second % 60 == 20) {
				blueGhost.setMode(GameConstant.CHASE);
			}
			if (second % 60 == 40) {
				blueGhost.setMode(GameConstant.SCATTER);
			}
			// PinkGhost
			if (second % 60 == 15) {
				pinkGhost.setMode(GameConstant.CHASE);
			}
			if (second % 60 == 30) {
				pinkGhost.setMode(GameConstant.SCATTER);
			}
			if (second % 60 == 30) {
				pinkGhost.setMode(GameConstant.SCATTER);
			}
			if (second % 60 == 45) {
				pinkGhost.setMode(GameConstant.CHASE);
			}
			if (second % 60 == 0) {
				pinkGhost.setMode(GameConstant.SCATTER);
			}
		}

	}

	public void startFrightened() {
		if (checker) {
			redGhost.setMode(GameConstant.FRIGHTENED);
			orangeGhost.setMode(GameConstant.FRIGHTENED);
			blueGhost.setMode(GameConstant.FRIGHTENED);
			pinkGhost.setMode(GameConstant.FRIGHTENED);
			frightenedStartTime = second;
			checker = false;
		}
		if (second - frightenedStartTime < 5) {
			return;
		} else {
			redGhost.loadSprite("/ghost.png", 2, 35, 35, 15, redGhost.getColorIndex());
			orangeGhost.loadSprite("/ghost.png", 2, 35, 35, 15, orangeGhost.getColorIndex());
			pinkGhost.loadSprite("/ghost.png", 2, 35, 35, 15, pinkGhost.getColorIndex());
			blueGhost.loadSprite("/ghost.png", 2, 35, 35, 15, blueGhost.getColorIndex());
			redGhost.setMode(GameConstant.CHASE);
			orangeGhost.setMode(GameConstant.CHASE);
			blueGhost.setMode(GameConstant.CHASE);
			pinkGhost.setMode(GameConstant.CHASE);
			isFrightened = false;
//			redGhost.setMode(GameConstant.CHASE);
//			orangeGhost.setMode(GameConstant.CHASE);
//			blueGhost.setMode(GameConstant.CHASE);
//			pinkGhost.setMode(GameConstant.CHASE);
		}

	}

	public void respawnCharacter() {
		pacman.x = 20;
		pacman.y = 20;
		redGhost.x = 260;
		redGhost.y = 240;
		pinkGhost.x = 240;
		pinkGhost.y = 280;
		blueGhost.x = 300;
		blueGhost.y = 280;
		orangeGhost.x = 340;
		orangeGhost.y = 280;
		redGhost.setMode(-1);
		orangeGhost.setMode(-1);
		blueGhost.setMode(-1);
		pinkGhost.setMode(-1);
		redGhost.loadSprite("/ghost.png", 2, 35, 35, 15, redGhost.getColorIndex());
		orangeGhost.loadSprite("/ghost.png", 2, 35, 35, 15, orangeGhost.getColorIndex());
		blueGhost.loadSprite("/ghost.png", 2, 35, 35, 15, blueGhost.getColorIndex());
		pinkGhost.loadSprite("/ghost.png", 2, 35, 35, 15, pinkGhost.getColorIndex());
		second = 0;

	}

}
