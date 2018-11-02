package player;

import java.util.Random;

import board.Piece;
import helper.KeyboardButton;

/*
 * The "AI" is pure random. It's smartness was not prioritized. Maybe next time :).
 */
public class AIPlayer extends Player {
	private int randomX;
	private boolean moving = false;
	private Random rn = new Random();

	public AIPlayer(Piece piece) {
		super(piece);
	}
	
	// Boolean turn was added so that the AI knows to stop moving after having pressed space
	@Override
	public KeyboardButton handleInput(YellowBorder yellowBorder, boolean turn) {
		
		if (!turn) {
			return null;
		}
		
		// generates a random x-value at which it desires to move and drop a piece.
		if (!moving) {
			randomX = rn.nextInt(7);
			moving = true;
		}
		
		// Moves the yellowBorder in the direction of the randomX
		if (yellowBorder.getXIndex() < randomX) {
			yellowBorder.moveRight();
			return KeyboardButton.RIGHT;
		}
		else if (yellowBorder.getXIndex() > randomX) {
			yellowBorder.moveLeft();
			return KeyboardButton.LEFT;
		}
		
		// If desired x-column is reached, drop piece there and stop moving.
		else {
			moving = false;
			return KeyboardButton.SPACE;	
		}
	}

}
