package entity;

import java.awt.Color;
import java.awt.Graphics;

import game.Game;
import game.GameConstant;

public class RedGhost extends Ghost {

	public RedGhost(Game game) {
		super(game, Color.red);
		x = 260;
		y = 240;
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.drawRect(targetX, targetY, 20, 20);

	}

	@Override
	public void chaseMode() {
		targetX = game.pacman.x;
		targetY = game.pacman.y;
		moveTo(targetX, targetY);
	}

	@Override
	public void scatterMode() {
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
