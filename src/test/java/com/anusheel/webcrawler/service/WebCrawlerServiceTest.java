package com.anusheel.webcrawler.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.anusheel.webcrawler.utils.Frontier;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class WebCrawlerServiceTest {
	
	@Mock
	private HTMLParserService htmlParserService;
	
	@Mock
	Frontier urlFrontier;
	
	@InjectMocks
	private WebCrawlerService webCrawlerService;
	
	@BeforeEach
	void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
	}

    @Test
    void testCrawlingCompletedStatus() {
    	when(urlFrontier.size()).thenReturn(0);
    	boolean crawlingCompletedStatus = webCrawlerService.crawlingCompletedStatus();
    	assertNotNull(crawlingCompletedStatus, "The crawling completion status is not null.");
    	boolean actual = true; 
    	assertEquals(actual, crawlingCompletedStatus);
    }
    
    @Test
    void testCrawlingCompletedStatusForNotCompletion() {
    	when(urlFrontier.size()).thenReturn(10);
    	boolean crawlingCompletedStatus = webCrawlerService.crawlingCompletedStatus();
    	assertNotNull(crawlingCompletedStatus, "The crawling completion status is not null.");
    	boolean actual = false; 
    	assertEquals(actual, crawlingCompletedStatus);
    }
}
