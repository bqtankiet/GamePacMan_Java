package input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import game.Game;
import game.GameConstant;

public class KeyBoardInput extends KeyAdapter {

	Game game;

	public KeyBoardInput(Game game) {
		this.game = game;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP -> {
			game.pacman.nextDirection = GameConstant.UP;
		}
		case KeyEvent.VK_DOWN -> {
			game.pacman.nextDirection = GameConstant.DOWN;
		}
		case KeyEvent.VK_RIGHT -> {
			game.pacman.nextDirection = GameConstant.RIGHT;
		}
		case KeyEvent.VK_LEFT -> {
			game.pacman.nextDirection = GameConstant.LEFT;
		}
		case KeyEvent.VK_SPACE -> {
			if(game.gamePaused) {
				System.out.println("TEST");
				game.continueGame();
				game.respawnCharacter();

			}
		}
		}
	}

}
