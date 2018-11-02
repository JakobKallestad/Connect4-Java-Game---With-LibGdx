package rules;

import java.util.List;

public class ConnectFourRules<T> implements IRules<T> {

	
	// CheckWin checks if a certain Piece, (given by the x and y coordinates from input),
	// is identical to its neighbor four times in a row. If something else comes in between,
	// then the counter resets. Reason it works this way is because there are only player1.getPiece(), 
	// player2.getPiece(), and null that are Pieces in play.
	// In other words, all the player1.getPieces are actually references to the same Piece-object.
	//
	// Need to look not only at the end, but that the win could be in the middle! - FIXED
	// Also need to end the game in a draw in case the board is completely filled up - FIXED (in Board)
	@Override
	public boolean checkWin(int x, int y, List<List<T>> cells) {
		int counter = 0;
		T toCheck = cells.get(y).get(x);
		int width = cells.get(0).size();
		int height = cells.size();
		boolean won = false;

		// check horizontal
		for (int i = -3; i < 4; i++) {
			if (x + i < width && x + i >= 0) {
				if (cells.get(y).get(x + i) == toCheck) {
					counter++;
				} else {
					counter = 0;
				}
			}
			if (counter == 4) {
				won = true;
			}
		}

		// check vertical
		counter = 0;
		for (int i = 1; i < 4; i++) {
			if (y + i < height) {
				if (cells.get(y + i).get(x) == toCheck) {
					counter++;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		if (counter == 3)
			won = true;

		// check downleft and upright
		counter = 0;
		for (int i = -3; i < 4; i++) {
			if (x - i >= 0 && x - i < width && y + i < height && y + i >= 0) {
				if (cells.get(y + i).get(x - i) == toCheck) {
					counter++;
				} else {
					counter = 0;
				}
			}
			if (counter == 4) {
				won = true;
			}
		}

		// check downright and upleft
		counter = 0;
		for (int i = -3; i < 4; i++) {
			if (x + i < width && x + i >= 0 && y + i < height && y + i >= 0) {
				if (cells.get(y + i).get(x + i) == toCheck) {
					counter++;
				} else {
					counter = 0;
				}
			}
			if (counter == 4) {
				won = true;
			}
		}
		
		// check draw was moved to the Board with isDraw()
		
		// If true, then someone won the game
		return won;
	}

	
	// Goes through the elements in the x-column and increments dropLength for each non-null value
	@Override
	public int setRule(List<List<T>> cells, int x) {
		if (x < 0 || x >= cells.get(0).size())
			throw new IndexOutOfBoundsException();
		int dropLength = 0;
		for (int i = 0; i < cells.size(); i++) {
			if (cells.get(i).get(x) == null) {
				dropLength++;
			}
		}
		return dropLength;
	}

}
