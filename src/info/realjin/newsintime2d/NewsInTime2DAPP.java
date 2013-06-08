package info.realjin.newsintime2d;

import info.realjin.newsintime2d.domain.Collection;
import info.realjin.newsintime2d.domain.CollectionItem;
import info.realjin.newsintime2d.domain.NewsListWrapper;
import info.realjin.newsintime2d.service.retriever.RetrieverService;
import android.app.Application;

public class NewsInTime2DAPP extends Application {

	private int scrWidth = 0;
	private int scrHeight = 0;
	private MainActivity mainActivity;

	private NewsListWrapper nlWrapper;
	private RetrieverService rtrService;

	public NewsInTime2DAPP() {
		// TODO Auto-generated constructor stub
		// mmm: for test
		nlWrapper = new NewsListWrapper();

//		Collection tempColl = new Collection();
//		tempColl.getItems().add(
//				new CollectionItem(
//						"http://rss.sina.com.cn/news/marquee/ddt.xml"));
//		rtrService = new RetrieverService(data.getNewsList(), tempColl);
//		rtrService.start();
		
		
		Collection tempColl = new Collection();
		tempColl.getItems().add(
				new CollectionItem(
						"http://rss.sina.com.cn/news/marquee/ddt.xml"));
		rtrService = new RetrieverService(nlWrapper, tempColl);
		rtrService.start();
	}

	// -----------setters and getters --------
	public MainActivity getMainActivity() {
		return mainActivity;
	}

	public void setMainActivity(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
	}

	public NewsListWrapper getNlWrapper() {
		return nlWrapper;
	}

	public void setNlWrapper(NewsListWrapper nlWrapper) {
		this.nlWrapper = nlWrapper;
	}

	public int getScrWidth() {
		return scrWidth;
	}

	public void setScrWidth(int scrWidth) {
		this.scrWidth = scrWidth;
	}

	public int getScrHeight() {
		return scrHeight;
	}

	public void setScrHeight(int scrHeight) {
		this.scrHeight = scrHeight;
	}

}
