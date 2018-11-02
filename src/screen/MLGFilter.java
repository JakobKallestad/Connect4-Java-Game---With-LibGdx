package screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import animation.Animation2;
import board.Piece;
import helper.GameHelper;

public class MLGFilter {

	// Animations
	private List<Animation2> animlist;
	private Animation2 animation1;
	private Animation2 animation2;
	private Animation2 animation3;
	private Animation2 animation4;
	private Animation2 animation5;
	private Animation2 animation6;
	private Animation2 animation7;
	private Animation2 animation8;
	private Animation2 animation9;
	private Animation2 animation10;
	private Animation2 animation11;
	private Animation2 animation12;
	private Animation2 animation13;
	private Animation2 animation14;
	private Animation2 animation15;

	// Duck Pieces
	public Piece duckMalePiece = new Piece(new Texture("mlg_atlas/DuckMalePiece.png"));
	public Piece duckFemalePiece = new Piece(new Texture("mlg_atlas/DuckFemalePiece.png"));

	
	// Sound effects
	// Gdx.audio.newSound(Gdx.files.internal("Sound/screams2.mp3"));
	public Sound screams = Gdx.audio.newSound(Gdx.files.internal("Sound/Bomb_Drop.wav"));
	public Sound quack = Gdx.audio.newSound(Gdx.files.internal("Sound/quack2.mp3"));
	private Sound quickSound = Gdx.audio.newSound(Gdx.files.internal("Sound/MLG Quickscope.mp3"));
	private Sound wowSound = Gdx.audio.newSound(Gdx.files.internal("Sound/WOW.mp3"));
	private Sound drawSound = Gdx.audio.newSound(Gdx.files.internal("Sound/ILLUMINATI CONFIRMED.wav"));

	// startfilters decides which animations (by index in animlist) should be played constantly form the start.
	private int[] startfilters = { 2, 4, 5, 6, 7, 12 };
	private Random random = new Random();
	
	// Constructor
	public MLGFilter(boolean use) {
		this.animlist = new ArrayList<>();
		if (use) {
			this.animation1 = new Animation2(1 / 10f,
					new TextureAtlas(Gdx.files.internal("mlg_atlas/ColorCircle.atlas")).getRegions(), PlayMode.NORMAL,
					0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			this.animlist.add(animation1);
			// ----------------------------

			this.animation2 = new Animation2(1 / 10f,
					new TextureAtlas(Gdx.files.internal("mlg_atlas/ConfusedPerson.atlas")).getRegions(),
					PlayMode.NORMAL, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			this.animlist.add(animation2);
			// ----------------------------

			this.animation3 = new Animation2(1 / 10f,
					new TextureAtlas(Gdx.files.internal("mlg_atlas/DancingGirl.atlas")).getRegions(), PlayMode.LOOP, 50,
					0, 120, 215);
			this.animlist.add(animation3);
			// ----------------------------

			this.animation4 = new Animation2(1 / 10f,
					new TextureAtlas(Gdx.files.internal("mlg_atlas/DopeComputer.atlas")).getRegions(), PlayMode.NORMAL,
					0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			this.animlist.add(animation4);
			// ----------------------------

			this.animation5 = new Animation2(1 / 24f,
					new TextureAtlas(Gdx.files.internal("mlg_atlas/DopeFrog.atlas")).getRegions(), PlayMode.LOOP, 0,
					300, 250, 215);
			this.animlist.add(animation5);
			// ----------------------------

			this.animation6 = new Animation2(1 / 24f,
					new TextureAtlas(Gdx.files.internal("mlg_atlas/DuckWalking.atlas")).getRegions(),
					PlayMode.LOOP_PINGPONG, 500, 0, 185, 230);
			this.animlist.add(animation6);
			// ----------------------------

			this.animation7 = new Animation2(1 / 24f,
					new TextureAtlas(Gdx.files.internal("mlg_atlas/Eyebrows.atlas")).getRegions(), PlayMode.LOOP, 300,
					300, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4);
			this.animlist.add(animation7);
			// ----------------------------

			this.animation8 = new Animation2(1 / 10f,
					new TextureAtlas(Gdx.files.internal("mlg_atlas/GirlRunning.atlas")).getRegions(), PlayMode.LOOP,
					500, 270, 125, 190);
			this.animlist.add(animation8);
			// ----------------------------

			this.animation9 = new Animation2(1 / 10f,
					new TextureAtlas(Gdx.files.internal("mlg_atlas/Illuminati.atlas")).getRegions(), PlayMode.LOOP, 0,
					0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			this.animlist.add(animation9);
			// ----------------------------

			this.animation10 = new Animation2(1 / 30f,
					new TextureAtlas(Gdx.files.internal("mlg_atlas/QuickScope6.atlas")).getRegions(), PlayMode.NORMAL,
					0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			this.animlist.add(animation10);
			// ----------------------------

			this.animation11 = new Animation2(1 / 10f,
					new TextureAtlas(Gdx.files.internal("mlg_atlas/Sakura.atlas")).getRegions(), PlayMode.LOOP, 0, 0,
					Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			this.animlist.add(animation11);
			// ----------------------------

			this.animation12 = new Animation2(1 / 10f,
					new TextureAtlas(Gdx.files.internal("mlg_atlas/Sharingan.atlas")).getRegions(), PlayMode.NORMAL, 0,
					0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			this.animlist.add(animation12);
			// ----------------------------

			this.animation13 = new Animation2(1 / 24f,
					new TextureAtlas(Gdx.files.internal("mlg_atlas/ShopDog.atlas")).getRegions(), PlayMode.LOOP, 150, 0,
					100, 200);
			this.animlist.add(animation13);
			// ----------------------------

			this.animation14 = new Animation2(1 / 10f,
					new TextureAtlas(Gdx.files.internal("mlg_atlas/Shrek.atlas")).getRegions(), PlayMode.LOOP, 0, 0,
					Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			this.animlist.add(animation14);
			// ----------------------------

			this.animation15 = new Animation2(1 / 24f,
					new TextureAtlas(Gdx.files.internal("mlg_atlas/Waow.atlas")).getRegions(), PlayMode.NORMAL, 0, 0,
					Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			this.animlist.add(animation15);
			// ----------------------------

			// setting dancing, running and frog animations on screen.
			start(startfilters);
		}
	}

	// Returns true if MLG mode was selected in MenuScreen
	public boolean isOn() {
		return animlist.size() > 0;
	}

	// Sets the time remaining for the constant animations to 9999999. Basically all game unless you are sleeping.
	public void start(int[] startfilters) {
		for (int i = 0; i < startfilters.length; i++) {
			Animation2 anim = animlist.get(startfilters[i]);
			anim.setTimeRemaining(9999999);
		}
	}

	// Has a chance of playing an effect of the screen depending on random values.
	public void effect(float x, float y) {
		if (animlist.size() == 0) {
			return;
		}
		double r = random.nextDouble();
		if (r > 0.5) {
			if (y > 80) {
				quickScope(x, y);
				return;
			}
		}
		r = random.nextDouble();
		if (r > 0.7) {
			waow();
		}
		if (r < 0.3) {
			animlist.get(10).setTimeRemaining(1.3f);
		}
	}

	// quickScope effect. The speed and placement of the animation was relatively complex and figured out with math and trial and error.
	public void quickScope(float x, float y) {
		Animation2 anim = animlist.get(9);
		float frameSpeed = (y / 80) * 10 + 30;
		anim.setTimePassed(0);
		anim.setTimeRemaining(60 / frameSpeed);
		anim.setRenderX(50 + x * GameHelper.PIECE_WIDTH - Gdx.graphics.getWidth() / 2);
		anim.setRenderY(40 + y - Gdx.graphics.getHeight() / 2);
		anim.setFrameDuration(1 / frameSpeed);
		quickSound.play();
	}

	// waow effect with sound.
	public void waow() {
		Animation2 anim = animlist.get(14);
		anim.setTimePassed(0);
		anim.setTimeRemaining(1.7f);
		wowSound.play();
	}

	// renders Shrek on screen. Because why not?
	public void shrek(SpriteBatch batch, float delta) {
		if (isOn()) {
			Animation2 anim = animlist.get(13);
			batch.setColor(1, 1, 1, 0.8f);
			batch.draw(anim.getKeyFrame(anim.getTimePassed()), anim.getRenderX(), anim.getRenderY(),
					anim.getRenderWidth(), anim.getRenderHeight());
			batch.setColor(1, 1, 1, 1f);
			anim.passTime(delta);
		}
	}

	// mlgRender will have all the animations and will play them if the animation
	// has still some duration left given from gameScreen or infinite
	public void mlgRender(SpriteBatch batch, float delta) {
		for (Animation2 anim : animlist) {
			// print the respected anim with correct frame and if duration is still going.
			if (anim.getTimeRemaining() > 0) {
				batch.setColor(1, 1, 1, 0.8f);
				batch.draw(anim.getKeyFrame(anim.getTimePassed()), anim.getRenderX(), anim.getRenderY(),
						anim.getRenderWidth(), anim.getRenderHeight());
				batch.setColor(1, 1, 1, 1f);
				anim.passTime(delta);
			}
		}
	}

	// This was never implemented due to not being finished in time.
	// It was supposed to move the animations around the screen. A little like langton's ant.
	public void randomMovmemt(int[] startfilters) {
		for (int i = 0; i < startfilters.length; i++) {
			Animation2 anim = animlist.get(startfilters[i]);

			// moverules
			if (anim.getRenderX() > 4) {

			}
		}
	}

	// frees up memory. I could not find out how to dispose of the animations! :o
	public void dispose() {
		duckMalePiece.getTexture().dispose();
		duckFemalePiece.getTexture().dispose();
		screams.dispose();
		quack.dispose();
		quickSound.dispose();
		wowSound.dispose();
		drawSound.dispose();
	}

	// IllumiantiSound plays, should the game end in a draw and mlg.isOn()
	public void drawSound() {
		if (isOn()) {
			long soundID = drawSound.play();
			drawSound.setLooping(soundID, true);
			animlist.get(8).setTimeRemaining(1000);
		}
	}
}
