package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import algorithm.AStarPathfinding;
import algorithm.AStarPathfinding.Node;
import game.Game;
import game.GameConstant;

public class Ghost extends Character {

	AStarPathfinding pathfinder;
	List<Node> path;

	public Ghost(Game game) {
		super(game, Color.red);
		x = 240;
		y = 280;
		size = GameConstant.SQUARE;
		speed = 2;
		pathfinder = new AStarPathfinding();
	}

	@Override
	public void update() {
//		super.update();
//		scatterMode();
		chaseMode();
	}

	public void drawPath(Graphics g) {
//		for (Node node : path) {
//			g.setColor(Color.pink);
//			g.drawRect(node.col * 20, node.row * 20, 20, 20);
//		}
	}

	public void chaseMode() {
		path = pathfinder.findPath(game.map, y / size, x / size, game.pacman.y / size, game.pacman.x / size);
		if (!path.isEmpty()) {
			int xDir = path.get(0).col * size - x;
			int yDir = path.get(0).row * size - y;

			if (xDir > 0) {
				nextDirection = GameConstant.RIGHT;
			} else if (xDir < 0) {
				nextDirection = GameConstant.LEFT;
			} else if (yDir > 0) {
				nextDirection = GameConstant.DOWN;
			} else if (yDir < 0) {
				nextDirection = GameConstant.UP;
			}
		}
		super.update();
	}

	public void scatterMode() {
			path = pathfinder.findPath(game.map, y / size, x / size, 28, 1);
		if (!path.isEmpty()) {
			int xDir = path.get(0).col * size - x;
			int yDir = path.get(0).row * size - y;

			if (xDir > 0) {
				nextDirection = GameConstant.RIGHT;
			} else if (xDir < 0) {
				nextDirection = GameConstant.LEFT;
			} else if (yDir > 0) {
				nextDirection = GameConstant.DOWN;
			} else if (yDir < 0) {
				nextDirection = GameConstant.UP;
			}
		}
		super.update();

	}

	private int getRandomDirection() {
		int randomDirection = -1;
		int randomNumber = (int) (Math.random() * 100);
		if (randomNumber >= 0 && randomNumber < 25) {
			randomDirection = GameConstant.UP;
		}
		if (randomNumber >= 25 && randomNumber < 50) {
			randomDirection = GameConstant.LEFT;
		}
		if (randomNumber >= 50 && randomNumber < 75) {
			randomDirection = GameConstant.RIGHT;
		}
		if (randomNumber >= 75 && randomNumber < 100) {
			randomDirection = GameConstant.DOWN;
		}
		return randomDirection;
	}

}
