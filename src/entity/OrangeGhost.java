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
		step = 0;
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.drawRect(targetX, targetY, 20, 20);
	}

	@Override
	public void update() {
		chaseMode();
	}

	@Override
	public void chaseMode() {

		boolean check = false;
		if (step == 0) {
			targetX = game.pacman.x;
			targetY = game.pacman.y;
			int dx = Math.abs(this.x - game.pacman.x);
			int dy = Math.abs(this.y - game.pacman.y);
			if (Math.sqrt(dx * dx + dy * dy) <= 5 * GameConstant.SQUARE) {
				step = 1;
				check = true;
			}
		}
		if (step == 1) {
			int randCol = 0;
			int randRow = 0;
			if (path != null && path.isEmpty()) {
				step = 1;
				check = true;
			}
			if (check) {
				do {
					Random random = new Random();
					randCol = random.nextInt(24 - 5 + 1) + 5;
					randRow = random.nextInt(25 - 5 + 1) + 4;
					targetX = randCol * GameConstant.SQUARE;
					targetY = randRow * GameConstant.SQUARE;
					check = false;
				} while (game.map[randRow][randCol] != 0);

			}
			if (this.x == targetX && this.y == targetY) {
				step = 0;
			}
		}
		moveTo(targetX, targetY);
	}

	@Override
	public void scatterMode() {
		int targetX = 0;
		int targetY = 0;
		switch (step) {
		case 0 -> {
			targetX = 1 * GameConstant.SQUARE;
			targetY = 28 * GameConstant.SQUARE;
			if (this.x == targetX && this.y == targetY)
				step++;
		}
		case 1 -> {
			targetX = 1 * GameConstant.SQUARE;
			targetY = 22 * GameConstant.SQUARE;
			if (this.x == targetX && this.y == targetY)
				step++;
		}
		case 2 -> {
			targetX = 1 * GameConstant.SQUARE;
			targetY = 1 * GameConstant.SQUARE;
			if (this.x == targetX && this.y == targetY)
				step++;
		}
		case 3 -> {
			targetX = 7 * GameConstant.SQUARE;
			targetY = 1 * GameConstant.SQUARE;
			if (this.x == targetX && this.y == targetY)
				step = 0;
		}
		}
		moveTo(targetX, targetY);

	}

}
