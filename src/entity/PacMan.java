package entity;

import java.awt.Color;

import game.Game;
import game.GameConstant;

public class PacMan extends Character {
	public PacMan(Game game) {
		super(game, Color.yellow);
		x = 20;
		y = 20;
		size = GameConstant.SQUARE;
		speed = 2;
		direction = GameConstant.UP;
		loadSprite("/pacman.png", 3, 33, 33, 17);
	}

	public void eat() {
		int[][] map = game.map;
		int centerX = this.x + 10;
		int centerY = this.y + 10;
		try {
			if (map[centerY / 20][centerX / 20] == 3) {
				System.out.println("Frightened");
				game.isFrightened = true;
				game.checker = true;
			}
			map[centerY / 20][centerX / 20] = 2;
		} catch (ArrayIndexOutOfBoundsException e) {
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		eat();
	}

	@Override
	public void die() {
		System.out.println("Died! Press SPACE to continue");
		game.pauseGame();
//		x = 20;
//		y = 20;
	}
	
}