/**
 * 
 */
package com.anusheel.webcrawler.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.anusheel.webcrawler.data.UrlTitleMapList;
import com.anusheel.webcrawler.request.SeedUrlRequest;
import com.anusheel.webcrawler.service.HTMLParserService;
import com.anusheel.webcrawler.service.WebCrawlerService;
import com.anusheel.webcrawler.utils.UrlUtils;

/**
 * 
 *
 */
@Repository
public class SeedURLRepository {

	private List<String> urlList;

	@Autowired
	WebCrawlerService crawlerService;
	
	@Autowired
	HTMLParserService htmlParserService; 

	public SeedURLRepository() {
		super();
		urlList = new ArrayList<>();
	}

	public boolean add(SeedUrlRequest seedUrl) throws InterruptedException {
		if (UrlUtils.isValidUrl(seedUrl.getUrl())) {
			urlList = addUrl(urlList, seedUrl.getUrl());
			htmlParserService.clearUrlListMap();
			CompletableFuture<Boolean> crawlingStarted = crawlerService.crawl(seedUrl.getUrl());
			return true;
		} else {
			return false;
		}
	}

	private List<String> addUrl(List<String> urlList, String url) {
		if (url != null && !urlList.contains(url)) {
			urlList.add(url);
		}
		return urlList;
	}

}
