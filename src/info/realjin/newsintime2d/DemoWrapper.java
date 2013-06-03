package info.realjin.newsintime2d;

import android.util.Log;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class DemoWrapper implements ApplicationListener, InputProcessor {

	public void create() {
	}

	public void resume() {
	}

	public void render() {
	}

	public void resize(int width, int height) {
	}

	public void pause() {
	}

	public void dispose() {
		Gdx.app.log("gdxdemo", "application destroyed");
	}

	public boolean keyDown(int keyCode) {
		Log.i("DemoWrapper handling keyInput", "");

		switch (keyCode) {
		case Keys.ESCAPE:
			Gdx.app.exit();
			break;
		default:
			Log.i("unknown key", "");

		}
		return true;
	}

	public boolean keyTyped(char arg0) {
		return false;
	}

	public boolean keyUp(int arg0) {
		return false;
	}

	public boolean scrolled(int arg0) {
		return false;
	}

	public boolean touchDown(int x, int y, int pointer, int button) {
		return false;
	}

	public boolean touchDragged(int arg0, int arg1, int arg2) {
		return false;
	}

	public boolean touchMoved(int arg0, int arg1) {
		return false;
	}

	public boolean needsGL20() {
		return false;
	}

	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		return false;
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		return false;
	}
}