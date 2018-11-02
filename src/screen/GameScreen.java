package screen;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import board.Board;
import board.IBoard;
import board.Piece;

// RedPiece2 has been scaled to be 72^2 pixels red, and an extra 4 right and left, and an extra 9 up and down.
// The Board has holes that are scaled to 90*80 pixels, but the far left side is 5 pixels too wide,
// and the far bottom side is one pixel too low. Therefore we scale it when drawing to batch.

import game.GameApp;
import helper.GameHelper;
import helper.KeyboardButton;
import player.AIPlayer;
import player.HumanPlayer;
import player.Player;
import player.YellowBorder;
import rules.ConnectFourRules;
import rules.IRules;

/* 
 * This is where the normal and mlg mode games are rendered and played out. 
 * Render() method is called approximately 60 times per second from Game.
 */
public class GameScreen implements Screen {

	// Pre-defined variables for GameScreen. Most of which are self-explanatory.
	private GameApp game;
	private SpriteBatch batch;
	// labels shown in win/draw state.
	private Texture winnerRed = new Texture("Assets/WinnerRed.png");
	private Texture winnerYellow = new Texture("Assets/WinnerYellow.png");
	private Texture pressSpace = new Texture("Assets/Press_space.png");
	private Texture drawlabel = new Texture("Assets/Draw.png");
	// Background, Pieces and Music.
	private Texture bg = new Texture("Assets/Connect4Board.png");

	// RedPiece2 and YellowPiece2 has been set to be 71^2 pixels color, and an extra
	// 4 right and
	// left, and an extra 9 up and down.
	// The Board has holes that are 70*70, but 90*80 pixels counting the distance
	// from on center to the next
	// The far left side is 5 pixels wider than the rest, and the far bottom side is
	// one pixel lower.
	// Therefore we add to it GameHelper.BoardStart-values when drawing to batch.
	private Piece redPiece = new Piece(new Texture("Assets/RedPiece2.png"));
	private Piece yellowPiece = new Piece(new Texture("Assets/YellowPiece2.png"));

	private YellowBorder yellowBorder = new YellowBorder(new Texture("Assets/YellowBorder2.png"));
	private Music music;
	private Sound impactSound;
	private Sound droppingSound;

	// Defined in constructor by values passed on from MenuScreen.
	private Player currentPlayer;
	private Player player1;
	private Player player2;
	private IBoard<Piece> theBoard;
	private IRules<Piece> theRules;

	// variables used by GameScreen for various purposes.
	private double timeRemaining = 0.5;
	private Player winner = null;
	private int dropPositionY;
	private int dropPositionX;
	private Piece fallPiece = null;

	// test MLG Filter
	private MLGFilter mlg;

	public GameScreen(GameApp game, String gameMode, String playerTypes, Music music, Sound impactSound) {
		this.game = game;
		this.batch = game.getBatch();
		this.yellowBorder.setPosition(GameHelper.BOARD_START_X, GameHelper.BOARD_START_Y);
		this.music = music;
		if (music != null) {
			music.play();
		}
		this.impactSound = impactSound;

		// sets DuckPieces and duck sound effect
		if (gameMode.equals("MLG")) {
			
			// only loads the animations if MLGFilter(true)
			this.mlg = new MLGFilter(true);
			this.redPiece.getTexture().dispose();
			this.yellowPiece.getTexture().dispose();
			this.redPiece = mlg.duckMalePiece;
			this.yellowPiece = mlg.duckFemalePiece;
			//
			this.impactSound.dispose();
			this.droppingSound = mlg.screams;
			this.impactSound = mlg.quack;

		} else {
			this.mlg = new MLGFilter(false);
			this.droppingSound = Gdx.audio.newSound(Gdx.files.internal("Sound/Bomb_Drop.wav"));
		}

		// Determines the player1/player2's types.
		if (playerTypes.equals("HvH")) {
			this.player1 = new HumanPlayer(redPiece);
			this.player2 = new HumanPlayer(yellowPiece);
		} else if (playerTypes.equals("HvA")) {
			this.player1 = new HumanPlayer(redPiece);
			this.player2 = new AIPlayer(yellowPiece);
		} else {
			this.player1 = new AIPlayer(redPiece);
			this.player2 = new AIPlayer(yellowPiece);
		}

		this.currentPlayer = player1;
		this.theRules = new ConnectFourRules<Piece>();
		this.theBoard = new Board<Piece>(GameHelper.BOARD_WIDTH, GameHelper.BOARD_HEIGHT, theRules);

	}

	/**
	 * This method handles the input from the player/computer and uses it to move
	 * yellowBorder or create a fallPiece. delta = 1/FPS = gives time in seconds per
	 * frame/render(). timeRemaining - makes it so that there is a 0.15 second delay
	 * between each input. (Adjustable). It is only possible to drop a Piece if
	 * fallPiece is null. In other words there is not currently a piece falling
	 * down.
	 */
	public void update(float delta) {

		// only looks for input if there has not been another input in the previous 0.15
		// seconds.
		if (timeRemaining <= 0) {
			
			// fallPiece == null --> currentPlayer has not yet placed a piece on the board.
			KeyboardButton pressed = currentPlayer.handleInput(yellowBorder, fallPiece == null);
			if (pressed != null) {
				timeRemaining = 0.15;
			}
			if (pressed == KeyboardButton.SPACE) {
				if (fallPiece == null) {

					// dropPosition is the y-coordinate in pixels that is the threshold for when to
					// input the fallPiece into cells.
					dropPositionY = GameHelper.PIECE_HEIGHT
							* (GameHelper.BOARD_HEIGHT - theBoard.getNextY(yellowBorder.getXIndex()));

					// if the column is already full.
					if (dropPositionY > 400) {
						return;
					}

					droppingSound.play();

					// dropPosition is the x-coordinate in pixels used to determine where to put the
					// fallPiece into cells.
					dropPositionX = yellowBorder.getXIndex();
					fallPiece = currentPlayer.getPiece();
					fallPiece.setPosition((GameHelper.PIECE_WIDTH * dropPositionX) + GameHelper.BOARD_START_X,
							GameHelper.START_FALL_Y);
					mlg.effect(dropPositionX, dropPositionY);
				}
			}
		} else {
			timeRemaining -= delta;
		}
	}

	public void updateFallPiece() {

		// moves fallPiece downwards
		if (fallPiece.getY() > dropPositionY) {
			fallPiece.setY(fallPiece.getY() - 10);
		}

		// Inserts fallPiece to theBoard - into cells.
		// also sets winner = currentPlayer if that is the case.
		// additional mlg effect if mlg-mode and draw. (should try ;)
		else {
			fallPiece.setY(dropPositionY);
			boolean win = theBoard.set(dropPositionX, fallPiece);
			droppingSound.stop();
			impactSound.play(0.2f);
			if (win) {
				winner = currentPlayer;
			} else if (theBoard.isDraw()) {
				mlg.drawSound();
				music.stop();
			} else {
				changePlayer();
			}
			fallPiece = null;
		}
	}

	/**
	 * This method draws to screen and is called 60 times per second (may vary
	 * depending on hardware.) First it clears screen Second it looks for
	 * user/computer input and updates accordingly. Third it updates a potential
	 * falling piece. Either moving it down or inserting it into cells. Fourth draws
	 * every piece inside cells at their given location, but scaled a bit to
	 * transform it to pixel grid. Fifth draws background-connect4 board over
	 * everything else. Sixth draws the yellow border over that. Lastly checks for
	 * win or draw.
	 */
	@Override
	public void render(float delta) {
		// Cleaning screen
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		// Taking in input from user
		if (winner == null && !theBoard.isDraw()) {
			update(delta);
		}

		// Shrek joins the action in mlg-mode when the suspense kicks in.
		if (theBoard.getAvailableHoles() < 15) {
			mlg.shrek(batch, delta);
		}

		// Drawing the falling Piece
		if (fallPiece != null) {
			fallPiece.draw(batch);
			updateFallPiece();
		}

		// Drawing the board
		List<List<Piece>> cells = theBoard.getBoard();
		for (int y = 0; y < cells.size(); y++) {
			for (int x = 0; x < cells.get(0).size(); x++) {
				Piece piece = cells.get(y).get(x);
				if (piece != null) {
					batch.draw(piece, (GameHelper.PIECE_WIDTH * x) + GameHelper.BOARD_START_X,
							(GameHelper.PIECE_HEIGHT * (GameHelper.BOARD_HEIGHT - y - 1) + GameHelper.BOARD_START_Y));
				}
			}
		}

		// Drawing backGround and the yellow border
		batch.draw(bg, 0, 0);
		yellowBorder.draw(batch);

		// Checking for win and draw
		checkWin();
		checkDraw();

		// mlg filter for extra effects if in mlg-mode.
		mlg.mlgRender(batch, delta);

		batch.end();
	}

	// Simple method to change currentPlayer.
	public void changePlayer() {
		if (currentPlayer == player1) {
			currentPlayer = player2;
		} else {
			currentPlayer = player1;
		}
	}

	// checks for a winner.
	public void checkWin() {
		if (winner != null) {
			if (winner == player1) {
				batch.draw(winnerRed, GameHelper.BOARD_START_X, GameHelper.BOARD_START_Y);
			} else {
				batch.draw(winnerYellow, GameHelper.BOARD_START_X, GameHelper.BOARD_START_Y);
			}
			endGame();
		}
	}

	// checks for draw.
	public void checkDraw() {
		if (theBoard.isDraw()) {
			batch.draw(drawlabel, GameHelper.BOARD_START_X, GameHelper.BOARD_START_Y);
			endGame();
		}
	}

	// makes it so that the game goes back to MenuScreen if SPACE is pressed.
	public void endGame() {
		batch.draw(pressSpace, 50, 75);
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			dispose();
			game.setScreen(new MenuScreen(game));
		}
	}

	// Dispose of all the resources that are no longer in use to free up memory. important.
	@Override
	public void dispose() {
		bg.dispose();
		winnerRed.dispose();
		winnerYellow.dispose();
		pressSpace.dispose();
		drawlabel.dispose();
		redPiece.getTexture().dispose();
		yellowPiece.getTexture().dispose();
		yellowBorder.getTexture().dispose();
		if (music != null) {
			music.dispose();
		}
		impactSound.dispose();
		droppingSound.dispose();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

}
