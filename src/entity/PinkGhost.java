package entity;

import java.awt.Color;

import game.Game;

public class PinkGhost extends Ghost{

	public PinkGhost(Game game) {
		super(game, Color.PINK);
		x = 240;
		y = 280;
	}

	@Override
	public void chaseMode() {
		
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
