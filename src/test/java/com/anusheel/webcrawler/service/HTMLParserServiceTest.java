package com.anusheel.webcrawler.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.anusheel.webcrawler.data.UrlTitleMap;
import com.anusheel.webcrawler.data.UrlTitleMapList;

class HTMLParserServiceTest {
	
	@Mock
	private UrlTitleMapList urlTitleMapList;
	
	@InjectMocks
	private HTMLParserService htmlParserService;  

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testClearUrlListMap() {
		UrlTitleMap urlTitleMap = new UrlTitleMap();
		urlTitleMap.setTitle("My Page");
		urlTitleMap.setUrl("http://www.mypage.com");
		urlTitleMapList.add(urlTitleMap);
		htmlParserService.clearUrlListMap();
		List<UrlTitleMap> urlTitleMapListVal = urlTitleMapList.getUrlTitleMapList();
		assertNotNull(urlTitleMapListVal);
		assertEquals(0, urlTitleMapListVal.size());
	}

}
