/**
 * 
 */
package com.anusheel.webcrawler.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.anusheel.webcrawler.request.SeedUrlRequest;
import com.anusheel.webcrawler.service.WebCrawlerService;

/**
 * 
 *
 */
@Repository
public class SeedURLRepository {
	
	private List<String> urlList;
	
	@Autowired
	WebCrawlerService crawlerService;
	
	

	public SeedURLRepository() {
		super();
		urlList = new ArrayList<>();
	}
	

	public void add(SeedUrlRequest seedUrl) throws InterruptedException {
		urlList = addUrl(urlList, seedUrl.getUrl());
		CompletableFuture<Boolean> crawlingStarted = crawlerService.crawl(seedUrl.getUrl());
	}

	private List<String> addUrl(List<String> urlList, String url) {
		if(url != null && !urlList.contains(url)) {
			urlList.add(url);
		}
		return urlList;
	}
	

}
