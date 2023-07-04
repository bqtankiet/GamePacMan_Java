package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import algorithm.AStarPathfinding;
import algorithm.AStarPathfinding.Node;
import game.Game;
import game.GameConstant;

public abstract class Ghost extends Character {

	AStarPathfinding pathfinder;
	List<Node> path;

	public Ghost(Game game, Color color) {
		super(game, color);
		size = GameConstant.SQUARE;
		speed = 2;
		pathfinder = new AStarPathfinding();
	}

	@Override
	public void update() {
//		chaseMode();
		scatterMode();
	}

	public void drawPath(Graphics g) {
//		for (Node node : path) {
//			g.setColor(Color.pink);
//			g.drawRect(node.col * 20, node.row * 20, 20, 20);
//		}
	}

	public abstract void chaseMode();

	public void moveTo(int x, int y) {
		path = pathfinder.findPath(game.map, this.y / size, this.x / size, y / size, x / size);
		if (!path.isEmpty()) {
			int xDir = path.get(0).col * size - this.x;
			int yDir = path.get(0).row * size - this.y;

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

	int step = 0;

	public abstract void scatterMode();

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
