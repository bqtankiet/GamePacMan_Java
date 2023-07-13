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

}