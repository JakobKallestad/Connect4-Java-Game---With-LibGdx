package game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import screen.MenuScreen;

// This is used to render whatever screen is currently in setScreen.
public class GameApp extends Game {
	private SpriteBatch batch;

	@Override
	public void create() {
		this.batch = new SpriteBatch();
		setScreen(new MenuScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	public SpriteBatch getBatch() {
		return this.batch;
	}

}
