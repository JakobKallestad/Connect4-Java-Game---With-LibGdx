package player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import helper.GameHelper;

// A class for determining where the user want to interact with on the Screen
// Only getTexture and position from Sprite is used
// In MenuScreen yellowBorder can only move up and down.
// In GameScreen it can only move left and right.
// Rules are set in MenuScreen and GameScreen as to where yellowBorder are allowed to move,
// but it does not work well with other pixel or width/height settings at the moment.
public class YellowBorder extends Sprite {
	
	// xIndex is used in GameScreen to know where to drop the pieces
	private int xIndex;
	
	// yIndex is used in MenuScreen to make various changes and settings.
	private int yIndex;
	
	public YellowBorder(Texture texture) {
		super(texture);
		this.xIndex = 0;
		this.yIndex = 0;
	}
	
	protected void moveLeft() {
		xIndex--;
		this.setX(this.getX() - GameHelper.PIECE_WIDTH);
	}
	
	protected void moveRight() {
		xIndex++;
		this.setX(this.getX() + GameHelper.PIECE_WIDTH);
	}
	
	public void moveUp() {
		yIndex++;
		this.setY(this.getY() + GameHelper.MENU_BUTTONS_HEIGHT);
	}
	
	public void moveDown() {
		yIndex--;
		this.setY(this.getY() - GameHelper.MENU_BUTTONS_HEIGHT);
	}
	
	public int getXIndex() {
		return xIndex;
	}
	
	public int getYIndex() {
		return yIndex;
	}
	
	public void setYIndex(int yIndex) {
		this.yIndex = yIndex;
	}
	
}
