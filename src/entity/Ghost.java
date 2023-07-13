package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import algorithm.AStarPathfinding;
import algorithm.AStarPathfinding.Node;
import game.Game;
import game.GameConstant;

public abstract class Ghost extends Character {

	public AStarPathfinding pathfinder;
	public List<Node> path;
	public int mode;
	public int targetX, targetY;
	public int step = 0;
	// COLOR INDEX
	public static final int RED = 0;
	public static final int PINK = 1;
	public static final int BLUE = 2;
	public static final int ORANGE = 3;
	public static final int GREEN = 4;
	public static final int PURPLE = 5;

	public Ghost(Game game, Color color) {
		super(game, color);
		size = GameConstant.SQUARE;
		speed = 2;
		pathfinder = new AStarPathfinding();
		targetX = this.x;
		targetY = this.y;
		loadSprite("/ghost.png", 2, 35, 35, 15);
		currentAnimation = animationDown;
	}

	public void loadSprite(String path, int numOfSprites, int spriteWidth, int spriteHeight, int space) {
		int colorIndex = -1;
		if (this.color.equals(Color.red)) {
			colorIndex = RED;
		}
		if (this.color.equals(Color.orange)) {
			colorIndex = ORANGE;
		}
		if (this.color.equals(Color.pink)) {
			colorIndex = PINK;
		}
		if (this.color.equals(Color.blue)) {
			colorIndex = BLUE;
		}
		InputStream is = getClass().getResourceAsStream(path);
		try {
			BufferedImage image = ImageIO.read(is);
			currentAnimation = new BufferedImage[numOfSprites];
			animationDown = new BufferedImage[numOfSprites];
			animationUp = new BufferedImage[numOfSprites];
			animationLeft = new BufferedImage[numOfSprites];
			animationRight = new BufferedImage[numOfSprites];
			for (int i = 0; i < animationDown.length; i++) {
				animationRight[i] = image.getSubimage(colorIndex * (spriteWidth + space), i * (spriteHeight + space),
						spriteWidth, spriteHeight);
				animationDown[i] = image.getSubimage((colorIndex) * (spriteWidth + space),
						(i + 2) * (spriteHeight + space), spriteWidth, spriteHeight);
				animationLeft[i] = image.getSubimage((colorIndex) * (spriteWidth + space),
						(i + 4) * (spriteHeight + space), spriteWidth, spriteHeight);
				animationUp[i] = image.getSubimage((colorIndex) * (spriteWidth + space),
						(i + 6) * (spriteHeight + space), spriteWidth, spriteHeight);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void drawPath(Graphics g) {
//		for (Node node : path) {
//			g.setColor(Color.pink);
//			g.drawRect(node.col * 20, node.row * 20, 20, 20);
//		}
	}

	public void moveTo(int x, int y) {
		path = pathfinder.findPath(game.map, this.y / size, this.x / size, y / size, x / size);
		if (!path.isEmpty()) {
			int pathX = path.get(0).col * size;
			int pathY = path.get(0).row * size;
			int xDir = pathX - this.x;
			int yDir = pathY - this.y;

			if (xDir > 0 && this.y == pathY) {
				nextDirection = GameConstant.RIGHT;
			} else if (xDir < 0 && this.y == pathY) {
				nextDirection = GameConstant.LEFT;
			} else if (yDir > 0 && this.x == pathX) {
				nextDirection = GameConstant.DOWN;
			} else if (yDir < 0 && this.x == pathX) {
				nextDirection = GameConstant.UP;
			}
		}
		super.updatePosition();
	}

	public abstract void chaseMode();

	public abstract void scatterMode();

	public int[] getRandomPosition() {
		int randX = 0;
		int randY = 0;
		do {
			Random random = new Random();
			randX = random.nextInt(30);
			randY = random.nextInt(30);
		} while (game.map[randY][randX] != 0);
		return new int[] { randX * 20, randY * 20 };
	}

	public void moveToRandomPosition() {
		if (this.x == targetX && this.y == targetY) {
			int[] randomPosition = getRandomPosition();
			targetX = randomPosition[0];
			targetY = randomPosition[1];
		}
		moveTo(targetX, targetY);
		if (path.isEmpty()) {
			targetX = this.x;
			targetY = this.y;
		}
	}

	@Override
	public void updatePosition() {
		switch (mode) {
		case GameConstant.SCATTER -> scatterMode();
		case GameConstant.CHASE -> chaseMode();
		}
	}

	public boolean canMoveTo(int x, int y) {
		if (x < 0 || x >= 600 || y < 0 || y >= 600) { // out of range
			return false;
		}
		if (game.map[y / GameConstant.SQUARE][x / GameConstant.SQUARE] == 1) { // is wall
			return false;
		} else { // is not wall but have no way to move
			List<Node> path = this.pathfinder.findPath(game.map, this.y / GameConstant.SQUARE,
					this.x / GameConstant.SQUARE, y / GameConstant.SQUARE, x / GameConstant.SQUARE);
			if (path.isEmpty())
				return false;
		}

		return true;
	}

	public void setMode(int mode) {
		step = 0;
		this.mode = mode;
	}
}
