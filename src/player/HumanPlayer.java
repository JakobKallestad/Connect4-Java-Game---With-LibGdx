package player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import board.Piece;
import helper.KeyboardButton;

// Takes input from user and passes it on to GameScreen in the update() method.
public class HumanPlayer extends Player {

	public HumanPlayer(Piece piece) {
		super(piece);
	}

	@Override
	// Uses libGdx's input.isKeyPressed() to determine how to move the yellowBorder or drop a piece.
	// Returns a KeyboardButton-enum that will be interpreted in GameScreen to decided:
	// 1. if the player pressed anything.
	// 2. if the player pressed space.
	public KeyboardButton handleInput(YellowBorder yellowBorder, boolean turn) {
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && yellowBorder.getXIndex() > 0) {
			yellowBorder.moveLeft();
			return KeyboardButton.LEFT;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && yellowBorder.getXIndex() < 6) {
			yellowBorder.moveRight();
			return KeyboardButton.RIGHT;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			return KeyboardButton.SPACE;
		}
		return null;
	}
}
