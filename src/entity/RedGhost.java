package entity;

import java.awt.Color;

import game.Game;

public class RedGhost extends Ghost {

	public RedGhost(Game game) {
		super(game, Color.red);
		x = 260;
		y = 240;
	}

	@Override
	public void chaseMode() {
		moveTo(game.pacman.x, game.pacman.y);
	}

	@Override
	public void scatterMode() {
		int targetX = 0;
		int targetY = 0;
		switch (step) {
		case 1 -> {
			targetX = 1 * 20;
			targetY = 1 * 20;
			if (this.x == targetX && this.y == targetY)
				step = 0;
		}
		case 0 -> {
			targetX = 28 * 20;
			targetY = 1 * 20;
			if (this.x == targetX && this.y == targetY)
				step++;
		}

		}
		moveTo(targetX, targetY);
	}

}
