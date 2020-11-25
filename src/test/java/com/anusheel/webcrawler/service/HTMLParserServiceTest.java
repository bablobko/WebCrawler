package com.anusheel.webcrawler.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doAnswer;

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

	@Test
	void testClearUrlListMapForAlreadyEmpty() {
		when(urlTitleMapList.getUrlTitleMapList()).thenReturn(new UrlTitleMapList().getUrlTitleMapList());
		htmlParserService.clearUrlListMap();
		List<UrlTitleMap> urlTitleMapListVal = urlTitleMapList.getUrlTitleMapList();
		verify(urlTitleMapList, times(1)).clearList();
		assertNotNull(urlTitleMapListVal);
		assertEquals(0, urlTitleMapListVal.size());
	}

	@Test
	void testGetUrlListMap() {
		UrlTitleMap urlTitleMap = new UrlTitleMap();
		urlTitleMap.setTitle("My Page");
		urlTitleMap.setUrl("http://www.mypage.com");
		UrlTitleMapList urlMapList = new UrlTitleMapList();
		urlMapList.add(urlTitleMap);
		when(urlTitleMapList.getUrlTitleMapList()).thenReturn(urlMapList.getUrlTitleMapList());
		UrlTitleMapList urlListMap = htmlParserService.getUrlListMap();
		assertNotNull(urlListMap);
		assertEquals(1, urlListMap.getUrlTitleMapList().size());
		assertEquals("My Page", urlListMap.getUrlTitleMapList().get(0).getTitle());
		assertEquals("http://www.mypage.com", urlListMap.getUrlTitleMapList().get(0).getUrl());
	}

}
