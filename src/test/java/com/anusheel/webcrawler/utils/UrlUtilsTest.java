package com.anusheel.webcrawler.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UrlUtilsTest {
	
	@Test
	void testGetDomain() {
       String url = "http://www.example.com/";
       String expected = "example.com";
       String actual = UrlUtils.getDomain(url);
       assertNotNull(actual, "The value returned from getDomain call should not be null");
       assertEquals(expected, actual);
	}
	
	@Test
	void testGetDomainForException() {		
		assertThrows(NullPointerException.class, ()->{
			String url = "";
			UrlUtils.getDomain(url);
		});
	}
	
	@Test
	void testGetDomainForRemovingParams() {
		String url = "https://www.google.com/search?source=hp&q=wipro+digital";
		String expected = "google.com";
		String actual = UrlUtils.getDomain(url);
		assertNotNull(actual, "The value returned from getDomain call should not be null");
		assertEquals(expected, actual);
	}
	
	@Test
	void testGetDomainForNullPointer() {
		assertThrows(NullPointerException.class, ()->{
			String url="malformedUrl";
			UrlUtils.getDomain(url);
		});
	}
	
	@Test
	void testGetDomainForRemovingPath() {
		String url = "https://wiprodigital.com/what-we-do/";
		String  expected = "wiprodigital.com";
		String actual = UrlUtils.getDomain(url);
		assertNotNull(actual, "The value returned from getDomain call should not be null");
		assertEquals(expected, actual); 
	}
	
	@Test
	void testIsValidUrlForNull() {
		String url = null;
		boolean actualValue = UrlUtils.isValidUrl(url);
		assertNotNull(actualValue, "The result from isValidUrl must be either true or false and it should not be null.");
		assertEquals(actualValue, false);
	}
	
	@Test
	void testIsValidUrlForNotNull() {
		String url = "https://www.google.com";
		boolean actualValue = UrlUtils.isValidUrl(url);
		assertNotNull(actualValue, "The result from isValidUrl must be either true or false and it should not be null.");
		assertEquals(actualValue, true);
	}
	
	@Test
	void testMalFormedUrl() {
		String url = "httpswww://com.google";
		boolean actualValue = UrlUtils.isValidUrl(url);
		assertNotNull(actualValue,"The result from isValidUrl must be either true or false and it should not be null.");
		assertEquals(actualValue, false);
	}

}