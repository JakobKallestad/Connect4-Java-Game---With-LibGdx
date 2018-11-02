package board;

import java.util.ArrayList;
import java.util.List;
import board.IBoard;
import rules.IRules;

/**
 * 
 * A Grid contains a set of cells in a square 2D matrix.
 *
 */
public class Board<T> implements IBoard<T> {
	
	/*
	 * 	Important to note that the topmost elements of cells start at cells.get(0).
	 * 	That means that the "y-coordinates" of cells will be inverted from how 
	 * 	the y-coordinates are drawn by the GameScreen. Therefore we reverse them in GameScreen
	 */
	private List<List<T>> cells;
	
	private int height;
	private int width;
	private IRules<T> rules;
	
	//used to determine a draw
	private int availableHoles;

	
	/**
	 * 
	 * Construct a grid with the given dimensions.
	 * 
	 * @param width
	 * @param height
	 * @param rules : a rule-set for board used with set-method for
	 * setting pieces and checking win-condition. 
	 */
	public Board(int width, int height, IRules<T> rules) {
		this.rules = rules;
		if (width <= 0 || height <= 0) {
			throw new IllegalArgumentException();
		}
		this.height = height;
		this.width = width;
		this.availableHoles = height*width;
		cells = new ArrayList<List<T>>();
		
		// Initialize cells with all null values
		for (int i = 0; i < height; ++i) {
			List<T> row = new ArrayList<T>();
			cells.add(row);
			for (int j = 0; j < width; j++) {
				cells.get(i).add(null);
			}
		}
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	
	/*
	 * 	Finds the correct y-position with rules(cells, x)
	 *  Checks to make sure the move is valid (column not filled up)
	 *  Returns true if the move resulted in a win, or false if not.
	 */
	@Override
	public boolean set(int x, T piece) {
		// dropLength is the number of null elements from the top of cells (in the x-column)
		// therefore dropLength - 1 is the first available y-coordinate from the top of cells.
		int dropLength = rules.setRule(cells, x);
		boolean win = false;
		
		// This is where we insert the piece into the board.
		// availableHoles is decremented so we can keep track of when it is a draw.
		if (dropLength > 0) {
			int y = dropLength - 1;
			cells.get(y).set(x, piece);
			availableHoles--;
			win = rules.checkWin(x, y, cells);
		}
		return win;
	}
	
	// returns the first non-null cell from the top of cells in the given x-column.
	public int getNextY(int x) {
		return rules.setRule(cells, x);
	}
	
	// If availableHoles is 0, then the board is completly filled up.
	public boolean isDraw() {
		return availableHoles <= 0;
	}

	
	// Returns individual elements from cell given their x- and y-coordinates within cells.
	@Override
	public T get(int x, int y) {
		if (x < 0 || x >= width)
			throw new IndexOutOfBoundsException();
		if (y < 0 || y >= height)
			throw new IndexOutOfBoundsException();
		return cells.get(y).get(x);
	}

	// Was originally used to display board in the console version, but now only used with TestBoard
	@Override
	public void displayBoard() {
		for (List<T> row : cells) {
			for (T element : row) {
				if (element == null) {
					System.out.print(" | ");
				}
				else {
					System.out.print(" " + element + " ");	
				}
			}
			System.out.println();
		}
	}
	
	// Return cells (basically all the info about the pieces. Used for drawing them to screen in GameScreen
	@Override
	public List<List<T>> getBoard() {
		return cells;
	}
	
	public int getAvailableHoles() {
		return availableHoles;
	}

}
