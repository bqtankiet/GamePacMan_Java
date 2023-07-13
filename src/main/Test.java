package main;

import game.Game;
import game.GameConstant;

public class Test {

	public static void main(String[] args) {
		Game game = new Game();
		game.blueGhost.setMode(GameConstant.CHASE);
		game.redGhost.setMode(GameConstant.SCATTER);
		game.orangeGhost.setMode(GameConstant.CHASE);
		game.pinkGhost.setMode(GameConstant.CHASE);
		game.run();
	}

}
