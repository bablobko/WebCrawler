package com.anusheel.webcrawler.service;

import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class WebCrawlerServiceTest {
	
	@Mock
	WebCrawlerService webCrawlerService;


	@Test
	public void crawlTestForNull() throws InterruptedException {
		String url = "xyzbadurl";
		CompletableFuture<Boolean> crawl = webCrawlerService.crawl(url);
		Assertions.assertNull(crawl);
	}
	
}
