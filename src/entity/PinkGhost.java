package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algorithm.AStarPathfinding;
import algorithm.AStarPathfinding.Node;
import game.Game;
import game.GameConstant;

public class PinkGhost extends Ghost {

	public PinkGhost(Game game) {
		super(game, Color.PINK);
		x = 240;
		y = 280;
		loadSprite("/ghost.png", 2, 35, 35, 15,1);
		currentAnimation = animationDown;

	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.drawRect(targetX, targetY, 20, 20);
	}

	@Override
	public void chaseMode() {
		switch (step) {
		case 0 -> {
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
			if ((this.x == targetX && this.y == targetY) || !this.canMoveTo(targetX, targetY)) {
				step++;
				targetX = -1;
			}
		}
		case 1 -> {
			if (targetX == -1) {
				int randX = -1;
				int randY = -1;
				int radiusRange = 10 * GameConstant.SQUARE;
				Random random = new Random();
				do {
					double randAngle = random.nextDouble() * 2 * Math.PI;
					int randRadius = random.nextInt(radiusRange);

					randX = (game.pacman.x + (int) (randRadius * Math.cos(randAngle))) / 20 * 20;
					randY = (game.pacman.y + (int) (randRadius * Math.sin(randAngle))) / 20 * 20;
				} while (!this.canMoveTo(randX, randY));
				targetX = randX;
				targetY = randY;
			}
			if ((this.x == targetX && this.y == targetY) || !this.canMoveTo(targetX, targetY)) {
				step = 0;
			}
		}
		}
		moveTo(targetX, targetY);
	}

	@Override
	public void scatterMode() {
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
