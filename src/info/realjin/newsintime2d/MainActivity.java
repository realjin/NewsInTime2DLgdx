package info.realjin.newsintime2d;

import android.os.Bundle;
import android.view.Display;
import android.view.Menu;

import com.badlogic.gdx.backends.android.AndroidApplication;

public class MainActivity extends AndroidApplication {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Display display = getWindowManager().getDefaultDisplay();
		NewsInTime2DAPP app = (NewsInTime2DAPP) getApplication();
		app.setScrWidth(display.getWidth());
		app.setScrHeight(display.getHeight());

		app.setMainActivity(this);

		try {
			// TODO: mmm: !!!!! temp
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// setContentView(R.layout.activity_main);
		initialize(new FirstGame(app), false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		// return true;

		menu.add(Menu.NONE, Menu.FIRST + 1, 1, "Edit Collection").setIcon(
				android.R.drawable.ic_menu_edit);
		menu.add(Menu.NONE, Menu.FIRST + 2, 2, "Play").setIcon(
				android.R.drawable.ic_media_play);
		menu.add(Menu.NONE, Menu.FIRST + 3, 5, "Menu3").setIcon(
				android.R.drawable.ic_menu_help);
		menu.add(Menu.NONE, Menu.FIRST + 4, 6, "Menu4").setIcon(
				android.R.drawable.ic_menu_day);
		return true;
	}

}
