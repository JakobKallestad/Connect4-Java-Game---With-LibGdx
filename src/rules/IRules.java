package rules;

import java.util.List;

// Interface of rules. This way more rules can be added later if the need arises
public interface IRules<T> {
	
	// CheckWin checks if a certain Piece, (given by the x and y coordinates from input),
	// is identical to its neighbor four times in a row. If something else comes in between,
	// then the counter resets. Reason it works this way is because there are only player1.getPiece(), 
	// player2.getPiece(), and null that are Pieces in play.
	// In other words, all the player1.getPieces are actually references to the same Piece-object.
	public boolean checkWin(int x, int y, List<List<T>> cells);
	
	// Goes through the elements in the x-column and increments dropLength for each non-null value
	public int setRule(List<List<T>> cells, int x);
}
