package info.realjin.newsintime2d.domain;

public class RssItem {
	// xml�ļ��б�ǩ���ݣ�����Ҫ���ǩҪ��Ӧ����?
	public static final String TITLE = "title";
	public static final String PUBDATE = "pubdate";
	private String title = null;
	private String description = null;
	private String link = null;
	private String category = null;
	private String pubdate = null;

	public RssItem() {
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setPubDate(String pubdate) {
		this.pubdate = pubdate;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getLink() {
		return link;
	}

	public String getCategory() {
		return category;
	}

	public String getPubDate() {
		return pubdate;
	}

	public String toString() {
		if (title.length() > 20) {
			return title.substring(0, 42) + "...";
		}
		return title;
	}
}