package com.anusheel.webcrawler.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UrlTitleMapList {

	List<UrlTitleMap> urlTitleMapList;
	
	public UrlTitleMapList() {
		super();
		this.urlTitleMapList = new ArrayList<UrlTitleMap>();
	}
	

	public UrlTitleMapList(List<UrlTitleMap> urlTitleMapList) {
		super();
		this.urlTitleMapList = urlTitleMapList;
	}



	/**
	 * @return the urlTitleMapList
	 */
	public List<UrlTitleMap> getUrlTitleMapList() {
		return urlTitleMapList;
	}

	/**
	 * @param urlTitleMapList the urlTitleMapList to set
	 */
	public void setUrlTitleMapList(List<UrlTitleMap> urlTitleMapList) {
		this.urlTitleMapList = urlTitleMapList;
	}

	public boolean add(UrlTitleMap urlTitleMap) {
		return this.urlTitleMapList.add(urlTitleMap);
	}

}
