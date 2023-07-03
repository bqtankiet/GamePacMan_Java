package entity;

import java.awt.Color;
import java.awt.Graphics;

import game.Game;
import game.GameConstant;

public abstract class Character {
	public int size = GameConstant.SQUARE;
	public final Color color;
	public int x, y;
	public int direction = -1, nextDirection = -1;
	public int speed;
	public Game game;

	public Character(Game game, Color color) {
		this.game = game;
		this.color = color;
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

	public void update() {

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
