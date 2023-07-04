package game;

import entity.BlueGhost;
import entity.Ghost;
import entity.OrangeGhost;
import entity.PacMan;
import entity.PinkGhost;
import entity.RedGhost;
import gui.GameFrame;
import gui.GamePanel;

public class Game {
	public PacMan pacman;
	public Ghost redGhost,orangeGhost,blueGhost,pinkGhost;
	public GamePanel gamePanel;
	public int[][] map = new int[30][30];

	public Game() {
		initMap();
		pacman = new PacMan(this);
		redGhost = new RedGhost(this);
		orangeGhost = new OrangeGhost(this);
		blueGhost = new BlueGhost(this);
		pinkGhost = new PinkGhost(this);
	}

	public void initMap() {
		map[0] = new int []  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		map[1] = new int []  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1};
		map[2] = new int []  {1,0,1,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,1,0,1};
		map[3] = new int []  {1,0,1,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,1,0,1};
		map[4] = new int []  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1};
		map[5] = new int []  {1,0,1,1,1,0,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,0,1,1,1,0,1};
		map[6] = new int []  {1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1};
		map[7] = new int []  {1,1,1,1,1,0,1,0,1,0,1,1,0,0,0,1,1,0,0,1,1,1,0,1,0,1,1,1,1,1};
		map[8] = new int []  {0,0,0,0,1,0,1,0,1,0,0,1,0,0,1,0,0,1,0,0,0,1,0,1,0,1,0,0,0,0};
		map[9] = new int []  {0,0,0,0,1,0,1,0,1,0,1,1,1,0,1,0,0,1,0,1,1,1,0,1,0,1,0,0,0,0};
		map[10] = new int []  {0,0,0,0,1,0,1,0,1,0,0,1,0,0,1,0,0,1,0,1,0,0,0,1,0,1,0,0,0,0};
		map[11] = new int []  {0,0,0,0,1,0,1,0,1,0,0,1,0,0,0,1,1,0,0,1,1,1,0,1,0,1,0,0,0,0};
		map[12] = new int []  {0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0};
		map[13] = new int []  {1,1,1,1,1,0,1,0,1,1,0,1,1,1,0,0,1,1,1,0,1,1,0,1,0,1,1,1,1,1};
		map[14] = new int []  {0,0,0,0,0,0,0,0,1,1,0,1,0,0,0,0,0,0,1,0,1,1,0,0,0,0,0,0,0,0};
		map[15] = new int []  {1,1,1,1,1,0,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,0,1,1,1,1,1};
		map[16] = new int []  {0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0};
		map[17] = new int []  {0,0,0,0,1,0,1,0,1,0,0,1,0,1,0,1,1,1,0,1,1,1,0,1,0,1,0,0,0,0};
		map[18] = new int []  {0,0,0,0,1,0,1,0,1,0,1,0,0,1,0,1,0,0,0,0,1,0,0,1,0,1,0,0,0,0};
		map[19] = new int []  {0,0,0,0,1,0,1,0,1,1,0,0,0,1,0,1,1,0,0,0,1,0,0,1,0,1,0,0,0,0};
		map[20] = new int []  {0,0,0,0,1,0,1,0,1,0,1,0,0,1,0,1,0,0,0,0,1,0,0,1,0,1,0,0,0,0};
		map[21] = new int []  {1,1,1,1,1,0,1,0,1,0,0,1,0,1,0,1,1,1,0,0,1,0,0,1,0,1,1,1,1,1};
		map[22] = new int []  {1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1};
		map[23] = new int []  {1,0,1,1,1,0,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,0,1,1,1,0,1};
		map[24] = new int []  {1,0,0,0,1,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,1,0,0,0,1};
		map[25] = new int []  {1,1,1,0,1,0,1,1,1,0,1,1,1,0,1,1,0,1,1,1,0,1,1,1,0,1,0,1,1,1};
		map[26] = new int []  {1,0,0,0,1,0,1,0,1,0,1,0,1,0,1,1,0,1,0,1,0,1,0,1,0,1,0,0,0,1};
		map[27] = new int []  {1,0,1,1,1,0,1,1,1,0,1,1,1,0,1,1,0,1,1,1,0,1,1,1,0,1,1,1,0,1};
		map[28] = new int []  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1};
		map[29] = new int []  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
	}

	public void run() {
		this.gamePanel = new GamePanel(this);
		new GameFrame(gamePanel);
		// Start game loop
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
				System.out.println(frame);
				frame = 0;
			}
		}
	}
}
