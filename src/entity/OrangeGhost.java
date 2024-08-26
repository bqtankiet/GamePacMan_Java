package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import game.Game;
import game.GameConstant;

public class OrangeGhost extends Ghost {

	public OrangeGhost(Game game) {
		super(game, Color.orange);
		x = 340;
		y = 280;
		loadSprite("/ghost.png", 2, 35, 35, 15,3);
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
			targetX = game.pacman.x;
			targetY = game.pacman.y;
			int dx = Math.abs(this.x - game.pacman.x);
			int dy = Math.abs(this.y - game.pacman.y);
			if (Math.sqrt(dx * dx + dy * dy) <= 5 * GameConstant.SQUARE) {
				step++;
				targetX = -1;
			}
		}
		case 1 -> {
			if (targetX == -1) {
				int randX = -1;
				int randY = -1;
				int radius = 10 * GameConstant.SQUARE;
				Random random = new Random();
				do {
					double randAngle = random.nextDouble() * 2 * Math.PI;

					randX = (game.pacman.x + (int) (radius * Math.cos(randAngle))) / 20 * 20;
					randY = (game.pacman.y + (int) (radius * Math.sin(randAngle))) / 20 * 20;
				} while (!this.canMoveTo(randX, randY));
				targetX = randX;
				targetY = randY;
			}
			if (this.x == targetX && this.y == targetY) {
				step = 0;
			}
		}
		}
		moveTo(targetX, targetY);
//		boolean check = false;
//		if (step == 0) {
//			targetX = game.pacman.x;
//			targetY = game.pacman.y;
//			int dx = Math.abs(this.x - game.pacman.x);
//			int dy = Math.abs(this.y - game.pacman.y);
//			if (Math.sqrt(dx * dx + dy * dy) <= 5 * GameConstant.SQUARE) {
//				step = 1;
//				check = true;
//			}
//		}
//		if (step == 1) {
//			int randCol = 0;
//			int randRow = 0;
//			if (path != null && path.isEmpty()) {
//				step = 1;
//				check = true;
//			}
//			if (check) {
//				do {
//					Random random = new Random();
//					randCol = random.nextInt(24 - 5 + 1) + 5;
//					randRow = random.nextInt(25 - 5 + 1) + 4;
//					targetX = randCol * GameConstant.SQUARE;
//					targetY = randRow * GameConstant.SQUARE;
//					check = false;
//				} while (game.map[randRow][randCol] != 0);
//
//			}
//			if (this.x == targetX && this.y == targetY) {
//				step = 0;
//			}
//		}
//		moveTo(targetX, targetY);
	}

	@Override
	public void scatterMode() {
		switch (step) {
		case 3 -> {
			targetX = 1 * GameConstant.SQUARE;
			targetY = 28 * GameConstant.SQUARE;
			if (this.x == targetX && this.y == targetY)
				step = 0;
		}
		case 2 -> {
			targetX = 1 * GameConstant.SQUARE;
			targetY = 22 * GameConstant.SQUARE;
			if (this.x == targetX && this.y == targetY)
				step++;
		}
		case 0 -> {
			targetX = 1 * GameConstant.SQUARE;
			targetY = 1 * GameConstant.SQUARE;
			if (this.x == targetX && this.y == targetY)
				step++;
		}
		case 1 -> {
			targetX = 7 * GameConstant.SQUARE;
			targetY = 1 * GameConstant.SQUARE;
			if (this.x == targetX && this.y == targetY)
				step++;
		}
		}
		moveTo(targetX, targetY);

	}

}
