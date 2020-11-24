package com.anusheel.webcrawler.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.anusheel.webcrawler.request.SeedUrlRequest;
import com.anusheel.webcrawler.service.WebCrawlerService;

class SeedURLRepositoryTest {
	
	@Mock
	private WebCrawlerService crawlerService;
		
	@InjectMocks
	private SeedURLRepository seedUrlRepository;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testAdd() {
		SeedUrlRequest seedUrl = new SeedUrlRequest();
		String url = "http://www.example.com";
		seedUrl.setUrl(url);
		boolean actual = false;
		try {
			when(crawlerService.crawl(url)).thenReturn(CompletableFuture.completedFuture(true));
			actual = seedUrlRepository.add(seedUrl);
			verify(crawlerService, times(1)).crawl(url);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertNotNull(actual, "Return from addUrl is never null.");
		assertEquals(true, actual );
	}
	
	@Test
	void testAddForNullUrl() {
		SeedUrlRequest seedUrl = new SeedUrlRequest();
		String url = null;
		seedUrl.setUrl(url);
		boolean actual = false;
		try {
			when(crawlerService.crawl(url)).thenReturn(CompletableFuture.completedFuture(true));
			actual = seedUrlRepository.add(seedUrl);
			verify(crawlerService, times(0)).crawl(url);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertNotNull(actual, "Return from addUrl is never null.");
		assertEquals(false, actual );
	}
	

}
