package info.realjin.newsintime2d.domain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.locks.ReentrantLock;

import android.util.Log;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class NewsListWrapper {

	private ReentrantLock nlLock;
	private int updating; // 0. no 1.yes
	public final int UPDATING_YES = 1;
	public final int UPDATING_NO = 0;
	private static final SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy.MM.dd;HH:mm:ss");
	private static final String ID_SPLITTER = "|";

	// for test
	List<News> nl;

	public NewsListWrapper() {
		// TODO Auto-generated constructor stub
		nlLock = new ReentrantLock();
		updating = UPDATING_NO;

		// for test
		nl = new ArrayList<News>();
		// News n1 = new News(
		// "Power Word: Fortitude had its mana cost reduced to 1%, down from 4.4%.",
		// 1);
		// News n2 = new News(
		// "Mass Dispel no longer dispels magic effects that are normally undispellable. Cast time has been decreased to 0.5 seconds, down from 1.5 seconds for Discipline and Holy Priests.",
		// 2);
		// News n3 = new News(
		// "Void Shift had its cooldown decreased to 5 minutes", 3);
		News n1 = new News(
				"Power Word: Fortitude had its mana cost reduced to 1%, down from 4.4%.");
		News n2 = new News(
				"Mass Dispel no longer dispels magic effects that are normally undispellable. Cast time has been decreased to 0.5 seconds, down from 1.5 seconds for Discipline and Holy Priests.");
		News n3 = new News("Void Shift had its cooldown decreased to 5 minutes");
//		nl.add(n1);
//		nl.add(n2);
//		nl.add(n3);
	}

	/**
	 * fetch next string to be displayed. criterion: <br>
	 * 1. former news(s) longer than a screen <br>
	 * 2. former + new news(s) not exceeding max length<br>
	 * 
	 * @param curId
	 * @param f
	 * @return
	 */
	// TODO: mmm not completed!
	public Object[] fetchNextStr(News curNews, BitmapFont f) {
		String s = "";
		String formerS = getFormerStr(curNews);
		s += formerS;
		List<News> nextNewsList = fetchNext(curNews);
		for (News n : nextNewsList) {
			s += n.getText();
		}
		Object[] results = new Object[3];
		results[0] = s;
		// results[1] = new Integer(nextNewsList.get(nextNewsList.size() - 1)
		// .getId());
		results[1] = nextNewsList.get(0); // mmmm: right???
		results[2] = formerS;
		return results;
	}

	/*
	 * mmm: temp. only for test mmm: make sure longer than screen width!
	 */
	public String getFormerStr(News curNews) {
		// mmm: test. make sure longer than screen width!
		// mmm: just for test (news count = 1 hardcoded)
		Log.i("getFormerStr", "curid=" + curNews.getId());
		// News n = nl.get(curId - 1);
		return curNews.getText();
	}

	// public News fetchNext(long curid) {
	public List<News> fetchNext(News curNews) {
		int nextid;
		// List<News> nextNewsList = new ArrayList<News>();
		List<News> nextNewsList;

		nlLock.lock();

		if (updating == UPDATING_YES) {

		} else {
			// mmm: just for test (news count = 1 hardcoded)
			// nextid = curid % (nl.size());
			// News nextNews = nl.get(nextid);
			// nextNewsList.add(nextNews);
			nextNewsList = getByIdMinLTS(curNews);

			return nextNewsList;
		}
		nlLock.unlock();
		return null;
	}

	public void update() {
		// set flag
		nlLock.lock();
		updating = UPDATING_YES;
		nlLock.unlock();

		// update

		// unset flag
		nlLock.lock();
		updating = UPDATING_NO;
		nlLock.unlock();
	}

	public List<News> getNl() {
		return nl;
	}

	public void setNl(List<News> nl) {
		this.nl = nl;
	}

	public void clearAll() {
		// TODO mmmmmm not implemented!!!

	}

	public void addNewsList(List<News> newsList) {
		// TODO mmmmmm not implemented!!! temp impl!!!
		for (News n : newsList) {
			addNews(n);
		}
	}

	private void addNews(News n) {
		// TODO mmmmmm not implemented!!! temp impl!!!
		if (n == null) {
			return;
		}

		// check if exist
		if (existNews(n)) {
			Log.d("===domain.NEWSLIST===", "news exist: " + n.getText());
			return;
		}

		// allocate id
		long subid = allocateSubId(n);
		nlLock.lock();
		n.setSubid(subid);

		String sTime = sdf.format(n.getTime());
		String id = sTime + ID_SPLITTER + subid;
		n.setId(id);

		nl.add(n);
		nlLock.unlock();
	}

	private boolean existNews(News n) {
		nlLock.lock();
		for (News m : nl) {
			if (m.getText().equalsIgnoreCase(n.getText())) {
				nlLock.unlock();
				return true;
			}
		}
		nlLock.unlock();
		return false;
	}

	// --------- methods for newsretriever service to put news ----
	private long allocateSubId(News n) {
		long maxSubId = 0;
		nlLock.lock();

		for (News m : nl) {
			Date dm = m.getTime();
			Date dn = n.getTime();
			if (dm.getTime() == dn.getTime()) {
				if (m.getSubid() > maxSubId) {
					maxSubId = m.getSubid();
				}
			}
		}

		nlLock.unlock();

		// if not found same time, then maxSubId remains zero
		return maxSubId + 1;
	}

	/**
	 * get all news of all cat whose id is at least of the argument, and the
	 * length is longer than a screen
	 * 
	 * @param max
	 *            not including
	 * @param size
	 * @return result is sorted by id
	 */
	public List<News> getByIdMinLTS(News min) {
		List<News> result = new ArrayList<News>();
		SortedSet<News> ss = new TreeSet<News>();

		nlLock.lock();
		for (News n : nl) {
			if (n.compareTo(min) > 0) { // TODO: cmp!!
				ss.add(n);
			}
		}

		// TODO: what if size==0? test in Test.java!!
		// News[] temp = new News[ss.size()];
		// ss.toArray(temp);

		// mmmmmm: temp!!!!! first or last???
		result.add(ss.first());
		// result.addAll(Arrays.asList(temp));

		// if (result.size() >= size) {
		// result = result.subList(0, size);
		// } else {
		// Log.i("===NewsLIst===",
		// "getAllCatByIdMax: not so many(expecting size:" + size
		// + ", actual size:" + result.size());
		// }

		nlLock.unlock();
		return result;
	}
}
