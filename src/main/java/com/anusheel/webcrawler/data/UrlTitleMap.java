package com.anusheel.webcrawler.data;

public class UrlTitleMap {
	
	private String url;
	private String title;
	
	public UrlTitleMap() {
		super();
	}

	public UrlTitleMap(String url, String title) {
		super();
		this.url = url;
		this.title = title;
	}
	

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

}
