package entity;

import java.awt.Color;

import game.Game;

public class OrangeGhost extends Ghost {

	public OrangeGhost(Game game) {
		super(game,Color.orange);
		x = 340;
		y = 280;
		step =2;
	}

	@Override
	public void chaseMode() {
//		if(step == 1) {
//			moveTo(20,28*20);
//			if (this.x == 20 && this.y == 28*20)
//				step=0;
//		} else {
//		moveTo(game.pacman.x, game.pacman.y);
//		}
//		if (Math.abs(this.x - game.pacman.x) <= 5 || Math.abs(this.y - game.pacman.y) <= 5) {
//			step = 1;
//		}
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
			targetX = 1 * 20;
			targetY = 1 * 20;
			if (this.x == targetX && this.y == targetY)
				step++;
		}
		case 3 -> {
			targetX = 7 * 20;
			targetY = 1 * 20;
			if (this.x == targetX && this.y == targetY)
				step = 0;
		}
		}
		moveTo(targetX, targetY);

	}

}
