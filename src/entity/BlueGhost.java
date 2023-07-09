package entity;

import java.awt.Color;
import java.awt.Graphics;

import game.Game;

public class BlueGhost extends Ghost {

	public BlueGhost(Game game) {
		super(game, Color.blue);
		x = 300;
		y = 280;
		targetX = this.x;
		targetY = this.y;
	}

	@Override
	public void chaseMode() {
		targetX = game.redGhost.x;
		targetY = game.pacman.y;
		moveTo(targetX, targetY);
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
	public void scatterMode() {
		int targetX = 0;
		int targetY = 0;
		switch (step) {
		case 0 -> {
			targetX = 28 * 20;
			targetY = 28 * 20;
			if (this.x == targetX && this.y == targetY)
				step++;
		}
		case 1 -> {
			targetX = 28 * 20;
			targetY = 22 * 20;
			if (this.x == targetX && this.y == targetY)
				step++;
		}
		case 2 -> {
			targetX = 22 * 20;
			targetY = 1 * 20;
			if (this.x == targetX && this.y == targetY)
				step++;
		}
		case 3 -> {
			targetX = 28 * 20;
			targetY = 1 * 20;
			if (this.x == targetX && this.y == targetY)
				step = 0;
		}
		}
		moveTo(targetX, targetY);
	}

}
