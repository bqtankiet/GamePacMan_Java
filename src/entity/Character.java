package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;

import algorithm.AStarPathfinding;
import algorithm.AStarPathfinding.Node;
import game.Game;
import game.GameConstant;

public abstract class Character {
	// SETTING
	public int size = GameConstant.SQUARE;
	public final Color color;
	public int x, y;
	public int direction = -1, nextDirection = -1;
	public int speed;
	public Game game;

	// ANIMATION
	public BufferedImage[] currentAnimation;
	public BufferedImage[] animationDown;
	public BufferedImage[] animationLeft;
	public BufferedImage[] animationRight;
	public BufferedImage[] animationUp;
	public int spriteIndex, animationTick, animationSpeed = 10;

	public Character(Game game, Color color) {
		this.game = game;
		this.color = color;
	}

	public void update() {
		this.updatePosition();
		this.updateAnimation();
	}

	public boolean canMoveTo(int x, int y) {
		if (x < 0 || x >= 600 || y < 0 || y >= 600) { // out of range
			return false;
		}
		if (game.map[y / GameConstant.SQUARE][x / GameConstant.SQUARE] == 1) { // is wall
			return false;
		} else { // is not wall but have no way to move
			List<Node> path = new AStarPathfinding().findPath(game.map, this.y / GameConstant.SQUARE,
					this.x / GameConstant.SQUARE, y / GameConstant.SQUARE, x / GameConstant.SQUARE);
			if (path.isEmpty())
				return false;
		}

		return true;
	}

	public void setAnimaitonDirection(int direction) {
		switch (direction) {
		case GameConstant.UP: {
			currentAnimation = animationUp;
			break;
		}
		case GameConstant.DOWN: {
			currentAnimation = animationDown;
			break;
		}
		case GameConstant.RIGHT: {
			currentAnimation = animationRight;
			break;
		}
		case GameConstant.LEFT: {
			currentAnimation = animationLeft;
			break;
		}
		}
	}

	public void loadSprite(String path, int numOfSprites, int spriteWidth, int spriteHeight, int space) {
		InputStream is = getClass().getResourceAsStream(path);
		try {
			BufferedImage image = ImageIO.read(is);
			currentAnimation = new BufferedImage[numOfSprites];
			animationDown = new BufferedImage[numOfSprites];
			animationUp = new BufferedImage[numOfSprites];
			animationLeft = new BufferedImage[numOfSprites];
			animationRight = new BufferedImage[numOfSprites];
			for (int i = 0; i < animationDown.length; i++) {
				animationDown[i] = image.getSubimage(0, i * spriteHeight + space * i, spriteWidth, spriteHeight);
				animationUp[i] = image.getSubimage(0, (i + 6) * spriteHeight + space * (i + 6), spriteWidth,
						spriteHeight);
				animationRight[i] = image.getSubimage(0, (i + 9) * spriteHeight + space * (i + 9), spriteWidth,
						spriteHeight);
				animationLeft[i] = image.getSubimage(0, (i + 3) * spriteHeight + space * (i + 3), spriteWidth,
						spriteHeight);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateAnimation() {
		if (currentAnimation == null)
			return;
		animationTick++;
		if (animationTick >= animationSpeed) {
			animationTick = 0;
			spriteIndex++;
			if (spriteIndex >= currentAnimation.length) {
				spriteIndex = 0;
			}
		}
	}

	public void move(int direction) {
		switch (direction) {
		case GameConstant.UP -> {
			y -= speed;
		}
		case GameConstant.DOWN -> {
			y += speed;
		}
		case GameConstant.LEFT -> {
			x -= speed;
		}
		case GameConstant.RIGHT -> {
			x += speed;
		}
		}
	}

	public boolean canMove(int direction) {
		int newX = x, newY = y;
		switch (direction) {
		case GameConstant.UP -> {
			newY -= speed;
		}
		case GameConstant.DOWN -> {
			newY += speed;
		}
		case GameConstant.LEFT -> {
			newX -= speed;
		}
		case GameConstant.RIGHT -> {
			newX += speed;
		}
		default -> {
			return false;
		}
		}
		int[][] map = game.map;
		int top = newY / size;
		int bottom = (newY + size - 1) / size;
		int right = (newX + size - 1) / size;
		int left = (newX) / size;
		return (map[top][right] != 1 && map[top][left] != 1 && map[bottom][left] != 1 && map[bottom][right] != 1);
	}

	public void updatePosition() {
		setAnimaitonDirection(this.direction);
		try {
			if (canMove(nextDirection))
				direction = nextDirection;
			if (canMove(direction))
				move(direction);
		} catch (ArrayIndexOutOfBoundsException e) {
			move(direction);
			if (canTeleport()) {
				teleport(direction);
			}
		}
	}

	public boolean canTeleport() {
		int top = y / size;
		int bottom = (y + size - 1) / size;
		int right = (x + size - 1) / size;
		int left = (x) / size;
		return ((left < 0 || left >= game.map[0].length) && (right < 0 || right >= game.map[0].length)
				|| (top < 0 || top >= game.map[0].length) && (bottom < 0 || bottom >= game.map[0].length));
	}

	public void teleport(int direction) {
		switch (direction) {
		case GameConstant.UP -> {
			y = game.map.length * GameConstant.SQUARE;
		}
		case GameConstant.DOWN -> {
			y = -1 * GameConstant.SQUARE;
		}
		case GameConstant.LEFT -> {
			x = game.map[0].length * 20;
		}
		case GameConstant.RIGHT -> {
			x = -1 * GameConstant.SQUARE;
		}
		}
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, size, size);
	}
}
