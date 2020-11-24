package com.anusheel.webcrawler.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.anusheel.webcrawler.data.UrlTitleMap;
import com.anusheel.webcrawler.data.UrlTitleMapList;
import com.anusheel.webcrawler.domain.SeedURLRepository;
import com.anusheel.webcrawler.request.SeedUrlRequest;
import com.anusheel.webcrawler.service.HTMLParserService;
import com.anusheel.webcrawler.service.WebCrawlerService;

class WebCrawlerControllerTest {
	
	@Mock
	private SeedURLRepository seedUrlRepository;
	
	@Mock
	private HTMLParserService htmlParserService;
	
	@Mock
	private WebCrawlerService webCrawlerService; 
	
	@InjectMocks
	private WebCrawlerController webCrawlerController;
	
	@BeforeEach
	void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testSeedUrlForBadUrl() throws InterruptedException {
		SeedUrlRequest seedUrlRequest = new SeedUrlRequest(anyString());
		when(seedUrlRepository.add(seedUrlRequest)).thenReturn(false);
		ResponseEntity<?> seedUrlResponse = webCrawlerController.seedUrl(seedUrlRequest);
		assertNotNull(seedUrlResponse, "seedUrlResponse should not be null");
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), seedUrlResponse.getStatusCodeValue());
	}
	

	@Test
	void testSeedUrlForGoodUrl() throws InterruptedException {
		SeedUrlRequest seedUrlRequest = new SeedUrlRequest("https://www.google.com");
		when(seedUrlRepository.add(seedUrlRequest)).thenReturn(true);
		ResponseEntity<?> seedUrlResponse = webCrawlerController.seedUrl(seedUrlRequest);
		assertNotNull(seedUrlResponse, "seedUrlResponse should not be null");
		assertEquals(HttpStatus.CREATED.value(), seedUrlResponse.getStatusCodeValue());
	}
	
	@Test
	void testCrawlerCompletionStage() {
		when(webCrawlerService.crawlingCompletedStatus()).thenReturn(false);
		ResponseEntity<?> crawlerCompletionResponse = webCrawlerController.crawlerCompletionStage();
		assertNotNull(crawlerCompletionResponse, "crawlerCompletionResponse should not be null");
		assertEquals(HttpStatus.ACCEPTED, crawlerCompletionResponse.getStatusCode());
	}
	
	@Test
	void testCrawlerCompletionStageForSeeOther() {
		when(webCrawlerService.crawlingCompletedStatus()).thenReturn(true);
		ResponseEntity<?> crawlerCompletionResponse = webCrawlerController.crawlerCompletionStage();
		assertNotNull(crawlerCompletionResponse, "crawlerCompletionResponse should not be null");
		assertEquals(HttpStatus.SEE_OTHER, crawlerCompletionResponse.getStatusCode());
	}
	
	@Test
	void testSendSiteDesc() {
		UrlTitleMap urlTitleMap = new UrlTitleMap();
		urlTitleMap.setUrl("http://www.google.com");
		urlTitleMap.setTitle("Don't be evil.");
		UrlTitleMapList urlListMap = new UrlTitleMapList();
		urlListMap.add(urlTitleMap);
		when(webCrawlerService.crawlingCompletedStatus()).thenReturn(true);
		when(htmlParserService.getUrlListMap()).thenReturn(urlListMap);
		UrlTitleMapList mapList = webCrawlerController.sendSiteDesc();
		verify(webCrawlerService, times(1)).crawlingCompletedStatus();
		verify(htmlParserService, times(1)).getUrlListMap();
		assertNotNull(mapList, "Site Description should not be null.");
		String actual = "Don't be evil.";
		assertEquals(actual, mapList.getUrlTitleMapList().get(0).getTitle());
		actual = "http://www.google.com";
		assertEquals(actual, mapList.getUrlTitleMapList().get(0).getUrl());
	}
	
	@Test
	void testSendSiteDescForIncompleteCompletionStatus() {
		UrlTitleMap urlTitleMap = new UrlTitleMap();
		urlTitleMap.setTitle("Crawling Not Completed");
		urlTitleMap.setUrl("No URL as crawling has not completed");
		UrlTitleMapList urlTitleMapList = new UrlTitleMapList();
		urlTitleMapList.add(urlTitleMap);
		when(webCrawlerService.crawlingCompletedStatus()).thenReturn(false);
		when(htmlParserService.getEmptyUrlListMap()).thenReturn(urlTitleMapList);
		UrlTitleMapList mapList = webCrawlerController.sendSiteDesc();
		verify(webCrawlerService, times(1)).crawlingCompletedStatus();
		verify(htmlParserService, times(1)).getEmptyUrlListMap();
		assertNotNull(mapList, "Site Description should not be null.");
		String actual = "Crawling Not Completed";
		assertEquals(actual, mapList.getUrlTitleMapList().get(0).getTitle());
		actual = "No URL as crawling has not completed";
		assertEquals(actual, mapList.getUrlTitleMapList().get(0).getUrl());
	}
	

}
