package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.Random;

import algorithm.AStarPathfinding;
import algorithm.AStarPathfinding.Node;
import game.Game;
import game.GameConstant;

public abstract class Ghost extends Character {

	AStarPathfinding pathfinder;
	List<Node> path;
	int mode;
	int targetX, targetY;

	public Ghost(Game game, Color color) {
		super(game, color);
		size = GameConstant.SQUARE;
		speed = 2;
		pathfinder = new AStarPathfinding();
		targetX = this.x;
		targetY = this.y;
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
		super.update();
	}

	int step = 0;

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
	public void update() {
		if (mode == GameConstant.CHASE) {
			chaseMode();
		} else if (mode == GameConstant.SCATTER) {
			scatterMode();
		}
	}

}
