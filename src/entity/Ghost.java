package entity;

import java.awt.Color;

import game.Game;
import game.GameConstant;

public class Ghost extends Character{

	public Ghost(Game game) {
		super(game, Color.red);
		x = 240;
		y = 300;
		size = GameConstant.SQUARE;
		speed = 2;
	}

}
