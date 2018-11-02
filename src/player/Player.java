package player;

import board.Piece;
import helper.KeyboardButton;

// Abstract Player class. I did not want an interface because I wanted a field variable, Piece, in every Player.
public abstract class Player {
	private Piece piece;

	public Player(Piece piece) {
		this.piece = piece;
	}

	public Piece getPiece() {
		return piece;
	}

	public KeyboardButton handleInput(YellowBorder yellowBorder, boolean turn) {
		return null;
	}

}
