package animation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

// This class builds on Animation, but adds in a few more field variables that I found useful to the animation class.
public class Animation2 extends Animation {
	// timePassed is the time in seconds that the animations has played.
	private float timePassed;
	
	// timeRemaining is the amount of seconds left that the animation will be displayed.
	private float timeRemaining;
	
	// x and y position where animation will be rendered on screen.
	private float renderX;
	private float renderY;
	
	// The size of which the animation will be rendered on screen
	private float renderWidth;
	private float renderHeight;

	public Animation2(float fps, Array<? extends TextureRegion> animation, PlayMode mode, float renderX, float renderY,
			float renderWidth, float renderHeight) {
		super(fps, animation, mode);
		this.timePassed = 0;
		this.timeRemaining = 0;
		this.setRenderX(renderX);
		this.setRenderY(renderY);
		this.setRenderWidth(renderWidth);
		this.setRenderHeight(renderHeight);
	}

	public float getTimePassed() {
		return timePassed;
	}

	public void setTimePassed(float timePassed) {
		this.timePassed = timePassed;
	}

	// Used in order for animation to know what frame to display in mlgRender method.
	public void passTime(float delta) {
		this.timePassed += delta;
		this.timeRemaining -= delta;
	}

	public float getTimeRemaining() {
		return timeRemaining;
	}

	public void setTimeRemaining(float timeRemaining) {
		this.timeRemaining = timeRemaining;
	}

	public float getRenderX() {
		return renderX;
	}

	public void setRenderX(float renderX) {
		this.renderX = renderX;
	}

	public float getRenderY() {
		return renderY;
	}

	public void setRenderY(float renderY) {
		this.renderY = renderY;
	}

	public float getRenderWidth() {
		return renderWidth;
	}

	public void setRenderWidth(float renderWidth) {
		this.renderWidth = renderWidth;
	}

	public float getRenderHeight() {
		return renderHeight;
	}

	public void setRenderHeight(float renderHeight) {
		this.renderHeight = renderHeight;
	}
}
