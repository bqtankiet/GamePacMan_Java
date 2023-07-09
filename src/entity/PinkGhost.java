package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import algorithm.AStarPathfinding;
import algorithm.AStarPathfinding.Node;
import game.Game;
import game.GameConstant;

public class PinkGhost extends Ghost {

	public PinkGhost(Game game) {
		super(game, Color.PINK);
		x = 240;
		y = 280;
	}

	@Override
	public void update() {
		chaseMode();
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.drawRect(targetX, targetY, 20, 20);
	}

	@Override
	public void chaseMode() {
		switch (game.pacman.direction) {
		case GameConstant.RIGHT -> {
			targetY = game.pacman.y;
			targetX = game.pacman.x + 4 * GameConstant.SQUARE;
		}
		case GameConstant.LEFT -> {
			targetY = game.pacman.y;
			targetX = game.pacman.x - 4 * GameConstant.SQUARE;

		}
		case GameConstant.UP -> {
			targetX = game.pacman.x;
			targetY = game.pacman.y - 4 * GameConstant.SQUARE;
		}
		case GameConstant.DOWN -> {
			targetX = game.pacman.x;
			targetY = game.pacman.y + 4 * GameConstant.SQUARE;
		}
		}
		moveTo(targetX, targetY);
	}
	
	public boolean canMoveTo(int x, int y) {
		List<Node> path = new ArrayList<>();
		path = new AStarPathfinding().findPath(game.map, this.y / size, this.x / size, y / size, x / size);
		return !path.isEmpty();

	}

	@Override
	public void scatterMode() {
		int targetX = 0;
		int targetY = 0;
		switch (step) {
		case 0 -> {
			targetX = 1 * 20;
			targetY = 28 * 20;
			if (this.x == targetX && this.y == targetY)
				step++;
		}
		case 1 -> {
			targetX = 1 * 20;
			targetY = 22 * 20;
			if (this.x == targetX && this.y == targetY)
				step++;
		}
		case 2 -> {
			targetX = 28 * 20;
			targetY = 22 * 20;
			if (this.x == targetX && this.y == targetY)
				step++;
		}
		case 3 -> {
			targetX = 28 * 20;
			targetY = 28 * 20;
			if (this.x == targetX && this.y == targetY)
				step = 0;
		}
		}
		moveTo(targetX, targetY);

	}

}
