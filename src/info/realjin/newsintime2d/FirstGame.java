package info.realjin.newsintime2d;

import info.realjin.newsintime2d.domain.NewsListWrapper;
import android.util.Log;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

public class FirstGame implements ApplicationListener {
	// 绘图用的SpriteBatch
	private SpriteBatch batch;

	private int n = 0;
	GL10 gl;
	BitmapFont font;
	Matrix4 mx4Font;
	NewsInTime2DAPP app;
	NewsListWrapper nlWrapper;

	int curNewsId;
	float x, y;
	String str;

	public FirstGame(NewsInTime2DAPP a) {
		this.app = a;
		nlWrapper = app.getNlWrapper();

		curNewsId = 1;
		str = nlWrapper.getNl().get(curNewsId - 1).getText();
		x = 10;
		y = 50;
	}

	@Override
	public void create() {
		// batch = new SpriteBatch(); // 实例化

		gl = Gdx.gl10;
		gl.glDisable(GL10.GL_DEPTH_TEST);
		// gl.glEnable(GL10.GL_DEPTH_TEST);
		// gl.glDisable(GL10.GL_TEXTURE_2D);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glClearColor(0, 0, 0, 1);

		mx4Font = new Matrix4();
		// font = new BitmapFont(true);

		font = new BitmapFont(Gdx.files.internal("data/tms24.fnt"),
				Gdx.files.internal("data/tms24.png"), true);

		font.scale(1f);
		// Vector3 v3old = new Vector3(800, 200, 0);
		// Vector3 v3new = new Vector3(200, 800, 0);
		// mx4Font.setToRotation(v3old, v3new);
		// mx4Font.setToRotation(new Vector3(200, 200, 0), 180);
		// mx4Font.setToRotation(new Vector3(0, 0, 0), 180);
		mx4Font.rotate(1000, 1000, 0, 180);

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {

		// Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); // 清屏
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		// batch.begin();
		// batch.end();

		SpriteBatch spriteBatch;

		// CharSequence str =
		// "Libgdx is a cross-platform game development library written in Java, with some JNI code for performance hungry sections.";
		// News curNews;
		// CharSequence str =
		// "Libgdx is a cross-platform game development library written in Java, with some JNI code for performance hungry sections.";
		spriteBatch = new SpriteBatch();
		spriteBatch.setTransformMatrix(mx4Font);

		x -= 1.5f;

		TextBounds tb = font.getBounds(str);
		float maxExceed = tb.width - app.getScrHeight();
		if (0 - x >= maxExceed) {// mmm: margin
			Object[] fnsResults = nlWrapper.fetchNextStr(curNewsId, font);
			// mmm: what if last not long enough!!!!
//			String lastSingleStr = nlWrapper.getNl().get(curNewsId - 1)
//					.getText();
//			str = lastSingleStr + (String) fnsResults[0];
			str = (String) fnsResults[0];
			curNewsId = (Integer) fnsResults[1];
//			 x = app.getScrHeight();
//			x = app.getScrHeight() - font.getBounds(lastSingleStr).width;
			x = app.getScrHeight() - font.getBounds((String) fnsResults[2]).width;

			Log.i("===FNS===", "x=" + x + ",str=" + str);
		}
		// Log.i("www", "exc="+maxExceed+", cur="+x);

		spriteBatch.begin();
		font.draw(spriteBatch, str, x, y);
		spriteBatch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

}
