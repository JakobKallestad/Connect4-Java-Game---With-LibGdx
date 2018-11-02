package board;

import java.util.List;

public interface IBoard<T> {


	/**
	 * @return The height of the grid.
	 */
	int getHeight();

	/**
	 * @return The width of the grid.
	 */
	int getWidth();

	/**
	 * Changes one of the elements from the cells list based on 
	 * the setRule of Board´s rules. (Rules is a field variable
	 * passed into Board in constructor.)
	 * @param x The column of the cell to change the contents of.
	 * @param element The contents the cell is to have.
	 */
	boolean set(int x, T element);

	/**
	 * 
	 * Get the contents of the cell in the given x,y location. 
	 * 
	 * y must be greater than or equal to 0 and less than getHeight().
	 * x must be greater than or equal to 0 and less than getWidth().
	 * 
	 * @param x The column of the cell to get the contents of.
	 * @param y The row of the cell to get contents of.
	 */
	T get(int x, int y);

	
	/**
	 * display the board to screen
	 *
	 */
	void displayBoard();

	/**
	 * Returns cells
	 */
	List<List<T>> getBoard();

	/**
	 * get next non-null y-coordinate of cells x-column
	 */
	int getNextY(int x);

	/**
	 * Returns true if no available holes left
	 */
	boolean isDraw();

	/**
	 * Returns the number of holes with non-null values.
	 */
	int getAvailableHoles();

}