package board;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

// getTexture() and getPosition() from Sprite is used.
// I could have added a IPiece too, but I considered that a little unnecessary.
public class Piece extends Sprite {
	public Piece(Texture texture) {
		super(texture);
	}
}
