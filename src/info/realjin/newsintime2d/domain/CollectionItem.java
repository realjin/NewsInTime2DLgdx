package info.realjin.newsintime2d.domain;

public class CollectionItem {
	private Integer id;
	private String name;

	private String url;

	public CollectionItem() {
	}
	public CollectionItem(String url) {
		this.url = url;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
