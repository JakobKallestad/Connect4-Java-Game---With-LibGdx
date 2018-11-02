package main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import game.GameApp;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		// window size in pixels
		// currently the game does not scale well to other pixel-ratios.
		config.width = 640;
		config.height = 480;
		
		// starts the game
		new LwjglApplication(new GameApp(), config);
	}
}
