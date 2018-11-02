package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import board.Board;
import board.IBoard;
import rules.ConnectFourRules;
import rules.IRules;

// I can't use actual Piece objects to test with because I can't use them unless libGdx is initialized (LwjglApplication is called).
// But because my checkWin method is generic and uses identity (==) between objects I can instead use String-objects instead for same result.
//
// Some of the tests may be hard to read in the code what is actually happening, but if in doubt just check out the printed versions in console.
// The tests asserts false all until the last move where it asserts true. Example: upRight means that the last piece scores a win in the upRight direction. 
public class TestBoard {
	private String redPiece = "R";
	private String yellowPiece = "Y";
	public static IRules<String> theRules = new ConnectFourRules<String>();

	@Test
	public void nullTest() {
		System.out.println("Assert that starting element of Board is null");
		IBoard<String> board = new Board<String>(7, 6, theRules);
		board.displayBoard();
		assertNull(board.get(3, 5));
		System.out.println();
	}

	@Test
	public void downWin() {
		IBoard<String> board = new Board<String>(7, 6, theRules);
		List<Boolean> truthList = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			boolean turn = board.set(0, redPiece);
			truthList.add(turn);
		}

		System.out.println("DownWin");
		board.displayBoard();
		System.out.println(truthList);
		System.out.println();

		assertFalse(truthList.get(0));
		assertFalse(truthList.get(1));
		assertFalse(truthList.get(2));
		assertTrue(truthList.get(3));
		assertTrue(truthList.get(4));
	}

	@Test
	public void rightWin() {
		IBoard<String> board = new Board<String>(7, 6, theRules);
		for (int i = 0; i < 4; i++) {
			boolean turn = board.set(i, redPiece);
			if (i == 3) {
				assertTrue(turn);
			} else {
				assertFalse(turn);
			}
		}
		System.out.println("rightWin");
		board.displayBoard(); // Test
		System.out.println(); // Test
	}

	@Test
	public void leftWin() {
		IBoard<String> board = new Board<String>(7, 6, theRules);
		for (int i = 3; i >= 0; i--) {
			boolean turn = board.set(i, redPiece);
			if (i == 0) {
				assertTrue(turn);
			} else {
				assertFalse(turn);
			}
		}
		System.out.println("leftWin");
		board.displayBoard(); // Test
		System.out.println(); // Test
	}

	@Test
	public void upRightWin() {
		IBoard<String> board = new Board<String>(7, 6, theRules);
		board.set(3, yellowPiece);

		boolean won = false;
		for (int i = 3; i >= 0; i--) {
			for (int j = 0; j <= i; j++) {
				if (i == 3 && j == 3) {
					break;
				}
				won = board.set(i, redPiece);

				if (i == 0 && j == 0) {
					assertTrue(won);
					break;
				} else {
					assertFalse(won);
				}
			}
		}
		System.out.println("upRightWin");
		board.displayBoard(); // Test
		System.out.println(); // Test
	}

	@Test
	public void downRightWin() {
		IBoard<String> board = new Board<String>(7, 6, theRules);
		board.set(0, yellowPiece);

		boolean won = false;
		for (int i = 3; i >= 0; i--) {
			for (int j = 4; j > i; j--) {
				if (i == 0 && j == 1) {
					break;
				}
				won = board.set(i, redPiece);

				if (i == 0 && j == 2) {
					assertTrue(won);
				} else {
					assertFalse(won);
				}
			}
		}
		System.out.println("downRightWin");
		board.displayBoard(); // Test
		System.out.println(); // Test
	}

	@Test
	public void upLeftWin() {
		IBoard<String> board = new Board<String>(7, 6, theRules);
		board.set(0, yellowPiece);

		boolean won = false;
		for (int i = 0; i < 4; i++) {
			for (int j = 4; j > i; j--) {
				if (i == 0 && j == 1) {
					break;
				}
				won = board.set(i, redPiece);

				if (i == 3 && j == 4) {
					assertTrue(won);
				} else {
					assertFalse(won);
				}
			}
		}
		System.out.println("upLeftWin");
		board.displayBoard(); // Test
		System.out.println(); // Test
	}

	@Test
	public void downLeftWin() {
		IBoard<String> board = new Board<String>(7, 6, theRules);
		board.set(3, yellowPiece);

		boolean won = false;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j <= i; j++) {
				won = board.set(i, redPiece);
				if (i == 3 && j == 2) {
					assertTrue(won);
					break;
				}
				assertFalse(won);
			}
		}
		System.out.println("downLeftWin");
		board.displayBoard(); // Test
		System.out.println(); // Test
	}

	@Test
	public void drawTest() {
		IBoard<String> board = new Board<String>(7, 6, theRules);
		for (int x = 0; x < 7; x++) {
			for (int y = 0; y < 6; y++) {
				if (x == 6 && y == 5) {
					break;
				}
				board.set(x, "X");
				assertFalse(board.isDraw());
			}
		}
		board.set(6, "X");
		assertTrue(board.isDraw());
		System.out.println("Assert Draw");
		board.displayBoard();
		System.out.println();
	}

}
