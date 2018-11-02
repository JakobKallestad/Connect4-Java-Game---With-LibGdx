package helper;

// Keeps track of a few constants.
// If the game later needs to modified, less code has to be rewritten because 
// of these static constants.
//
// In MLGFilter it was hard to find a logical system for scaling, so a lot of raw numbers where used there, but its fine.
public class GameHelper {
	
	// Note: not actually the visible height and width, but the height and width of the entire texture-image.
	public static final int PIECE_WIDTH = 90;
	public static final int PIECE_HEIGHT = 80;
	
	// The background-board picture is not completely symmetrical, so there needs to be some extra adjustments.
	public static final int BOARD_START_X = 5;
	public static final int BOARD_START_Y = -1;
	
	// The width and the height of the board. Could in theory make a way to customize the size of the board, 
	// but currently I only have one picture of a 6 by 7 board, so that would need more work to work.
	// (Not a priority at the moment.)
	public static final int BOARD_WIDTH = 7; 
	public static final int BOARD_HEIGHT = 6;
	
	// The initial starting position of the fallPiece.
	public static final int START_FALL_Y = (PIECE_HEIGHT*BOARD_HEIGHT)-40;
	
	// The height of which the buttons in the MenuScreen is separated by.
	public static final int MENU_BUTTONS_HEIGHT = 90;
}
