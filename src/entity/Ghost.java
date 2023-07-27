package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
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

	public Ghost(Game game, Color color) {
		super(game, color);
		size = GameConstant.SQUARE;
		speed = 2;
		pathfinder = new AStarPathfinding();
		targetX = this.x;
		targetY = this.y;
	}

	public int getColorIndex() {
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
		return colorIndex;
	}

	public void loadSprite(String path, int numOfSprites, int spriteWidth, int spriteHeight, int space, int index) {
		InputStream is = getClass().getResourceAsStream(path);
		try {
			BufferedImage image = ImageIO.read(is);
			currentAnimation = new BufferedImage[numOfSprites];
			animationDown = new BufferedImage[numOfSprites];
			animationUp = new BufferedImage[numOfSprites];
			animationLeft = new BufferedImage[numOfSprites];
			animationRight = new BufferedImage[numOfSprites];
			for (int i = 0; i < animationDown.length; i++) {
				animationRight[i] = image.getSubimage(index * (spriteWidth + space), i * (spriteHeight + space), spriteWidth, spriteHeight);
				animationDown[i] = image.getSubimage((index) * (spriteWidth + space), (i + 2) * (spriteHeight + space), spriteWidth, spriteHeight);
				animationLeft[i] = image.getSubimage((index) * (spriteWidth + space), (i + 4) * (spriteHeight + space), spriteWidth, spriteHeight);
				animationUp[i] = image.getSubimage((index) * (spriteWidth + space), (i + 6) * (spriteHeight + space), spriteWidth, spriteHeight);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		currentAnimation = animationDown;
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

	public void frightenedMode() {

		switch (step) {
		case 0 -> {
			int maxDistance = 0;
			int farthestX = 0;
			int farthestY = 0;

			// Iterate over the game board
			for (int row = 0; row < game.map.length; row++) {
				for (int col = 0; col < game.map[0].length; col++) {
					if (game.map[row][col] != 1) {
						// Calculate the distance between the ghost and the current position
						int distance = Math.abs(this.x - col * 20) + Math.abs(this.y - row * 20);
						if (distance > maxDistance) {
							maxDistance = distance;
							farthestX = col * 20;
							farthestY = row * 20;
						}
					}
				}
			}
			targetX = farthestX;
			targetY = farthestY;
			step++;
		}
		case 1 -> {
			if (this.x == targetX && this.y == targetY) {
				step = 0;
			}
		}
		}
		moveTo(targetX, targetY);
	}

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
		case GameConstant.FRIGHTENED -> frightenedMode();

		}
	}

	public void setMode(int mode) {
		step = 0;
		this.mode = mode;
		if (mode == GameConstant.FRIGHTENED) {
			loadSprite("/ghost.png", 2, 35, 35, 15, 6);
		}
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		x = 340;
		y = 280;
		loadSprite("/ghost.png", 2, 35, 35, 15, getColorIndex());
	}

}
