package screen;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.GameApp;
import player.YellowBorder;

/*
 * This is where the game is set up. Music, GameModes and playerTypes are also selected from here.
 */
public class MenuScreen implements Screen {
	private GameApp game;
	private SpriteBatch batch;
	
	// layers is used to determine where in the menu we are located. 
	// its a simple way for MenuScreen to know what to do with the yIndex from yellowBorder in update.
	private int layers = 0;
	
	private Texture bg = new Texture("Assets/MainMenu.png");
	
	// Makes sure that input have a time-delay in between
	private double timeRemaining = 0;
	
	// Used to visually make it easier for the user to choose from the menu AND
	// to make the desired choices given the yIndex combined with layers.
	private YellowBorder yellowBorder = new YellowBorder(new Texture("Assets/YellowBorder3.png"));

	// Textures for displaying text to screen. There is probably a better way to do this, but this will do for now.
	private Texture button01 = new Texture("ButtonLabels/New_Game_label.png");
	private Texture button02 = new Texture("ButtonLabels/Settings_label.png");
	private Texture button03 = new Texture("ButtonLabels/Exit_label.png");
	private Texture button11 = new Texture("ButtonLabels/Music_on:off_label.png");
	private Texture button12 = new Texture("ButtonLabels/SFX_on:off_label.png");
	private Texture button13 = new Texture("ButtonLabels/Back_label.png");
	private Texture button21 = new Texture("ButtonLabels/Normal_label.png");
	private Texture button22 = new Texture("ButtonLabels/MLG_label.png");
	private Texture button23 = new Texture("ButtonLabels/Big_Board_label.png");
	private Texture button24 = new Texture("ButtonLabels/Back_label.png");
	private Texture button31 = new Texture("ButtonLabels/Human_vs_Human_label.png");
	private Texture button32 = new Texture("ButtonLabels/Human_vs_AI_label.png");
	private Texture button33 = new Texture("ButtonLabels/AI_vs_AI_label.png");
	private List<Texture> buttonList;
	
	// Values for sending to GameScreen:
	private String playerTypes;
	private String gameMode;
	
	//test music:
	private Music music = Gdx.audio.newMusic(Gdx.files.internal("Sound/B3.mp3"));
	private Music music2 = Gdx.audio.newMusic(Gdx.files.internal("Sound/Fire-på-rad-med-phaser.mp3"));
	private Music music3 = Gdx.audio.newMusic(Gdx.files.internal("Sound/15.3.2018.mp3"));
	private Music music4 = Gdx.audio.newMusic(Gdx.files.internal("Sound/Into Deadlands.mp3"));
	private Sound impactSound = Gdx.audio.newSound(Gdx.files.internal("Sound/Bomb.wav"));
	private List<Music> musicList;
	private int currentMusic = 0;
	

	public MenuScreen(GameApp game) {
		this.game = game;
		this.batch = game.getBatch();
		this.yellowBorder.setPosition(140, 310);
		this.yellowBorder.setYIndex(3);
		this.impactSound.setVolume(1, 0.2f);
		this.musicList = new ArrayList<>();
		musicList.add(music);
		musicList.add(music2);
		musicList.add(music3);
		musicList.add(music4);
		for (Music music: musicList) {
			music.setLooping(true);
			music.setVolume(0.4f);
		}
		musicList.add(null);
		musicList.get(0).play();

		// Used for displaying button-texture to screen.
		this.buttonList = new ArrayList<Texture>();
		buttonList.add(null);
		buttonList.add(button01);
		buttonList.add(button02);
		buttonList.add(button03);
		buttonList.add(null);
	}

	// Moves yellowBorder and changes layers.
	private void update(float delta) {
		if (timeRemaining <= 0) {
			if ((Gdx.input.isKeyPressed(Input.Keys.ANY_KEY))) {
				timeRemaining = 0.15;
			}
			if (Gdx.input.isKeyPressed(Input.Keys.UP) && yellowBorder.getY() < 305) { //approx numbers
				yellowBorder.moveUp();
			}
			if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && yellowBorder.getY() > 210) { //approx numbers
				yellowBorder.moveDown();
			}
			if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
				handleLayers();
				updateLayers();
			}
		} else {
			timeRemaining -= delta;
		}

	}

	// determines what to do with the space-click
	private void handleLayers() {
		int pressed = yellowBorder.getYIndex();
		if (layers == 0) {
			if (pressed == 3) {
				layers+=2;
			}
			if (pressed == 2) {
				layers++;
			}
			if (pressed == 1) {
				System.exit(0);
			}
		}
		else if (layers == 1) {
			if (pressed == 3) {
				changeMusic();
			}
			if (pressed == 2) {
				changeSFX();
			}
			if (pressed == 1) {
				layers--;
			}
		}
		else if (layers == 2) {
			layers++;
			if (pressed == 3) {
				gameMode = "Normal";
			}
			if (pressed == 2) {
				gameMode = "MLG";
			}
			if (pressed == 1) {
				layers-=3;
			}
		}
		else if (layers == 3) {
			layers++;
			if (pressed == 3) {
				playerTypes = "HvH";
			}
			if (pressed == 2) {
				playerTypes = "HvA";
			}
			if (pressed == 1) {
				playerTypes = "AvA";
			}
			// new game is created. Moving to GameScreen
			if (musicList.get(currentMusic%5) != null) {
				musicList.get(currentMusic%5).stop();
			}
			dispose();
			game.setScreen(new GameScreen(game, gameMode, playerTypes, musicList.get(currentMusic%5), impactSound));
		}
	}

	private void changeSFX() {
		// does not works yet. (because I'm lazy.)
		impactSound.play(0.2f);
	}

	private void changeMusic() {
		if (musicList.get(currentMusic%5) != null) {
			musicList.get(currentMusic%5).stop();
		}
		currentMusic++;
		if (musicList.get(currentMusic%5) != null) {
			musicList.get(currentMusic%5).play();
		}
		
	}

	@Override
	public void render(float delta) {
		
		// clears screen
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// gets input from user and if user press space, then handleLayers and updateLayers as well.
		update(delta);
		
		batch.begin();
		batch.draw(bg, 0, 0);

		// draws the button-textures to screen
		for (int i = 0; i < buttonList.size(); i++) {
			if (buttonList.get(i) != null) {
				batch.draw(buttonList.get(i), (Gdx.graphics.getWidth()-buttonList.get(i).getWidth())/2, 400 - (i * 90));
			}
		}

		// draws yellowBorder to screen at it's position
		yellowBorder.draw(batch);
		batch.end();
	}

	// updates the textures within buttonList based on the layers number.
	// (Note: that there are some null-buttons because I originally planned more GameModes and options.)
	private void updateLayers() {
		if (layers == 0) {
			buttonList.set(0, null);
			buttonList.set(1, button01);
			buttonList.set(2, button02);
			buttonList.set(3, button03);
			buttonList.set(4, null);
		}
		if (layers == 1) {
			buttonList.set(0, null);
			buttonList.set(1, button11);
			buttonList.set(2, button12);
			buttonList.set(3, button13);
			buttonList.set(4, null);
		}
		if (layers == 2) {
			buttonList.set(0, null);
			buttonList.set(1, button21);
			buttonList.set(2, button22);
			buttonList.set(3, button24);
			buttonList.set(4, null);
		}
		if (layers == 3) {
			buttonList.set(0, null);
			buttonList.set(1, button31);
			buttonList.set(2, button32);
			buttonList.set(3, button33);
			buttonList.set(4, null);
		}
	}

	// Frees up memory by disposing of textures no longer being used when moving on to GameScreen.
	@Override
	public void dispose() {
		button01.dispose();
		button02.dispose();
		button03.dispose();
		button11.dispose();
		button12.dispose();
		button13.dispose();
		button21.dispose();
		button22.dispose();
		button23.dispose();
		button31.dispose();
		button32.dispose();
		button33.dispose();
		yellowBorder.getTexture().dispose();
		bg.dispose();
		for (Music m: musicList) {
			if (m != null && m != musicList.get(currentMusic%5)) {
				m.dispose();	
			}
		}
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
