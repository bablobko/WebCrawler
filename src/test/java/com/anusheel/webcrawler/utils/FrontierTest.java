package com.anusheel.webcrawler.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.PriorityQueue;
import java.util.Queue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FrontierTest {
	
	Frontier frontier;
	
	@BeforeEach
	void init() {
		Queue<String> priorityQueue = new PriorityQueue<>();
		String domain = "wiprodigital.com";
		frontier = new Frontier(priorityQueue, domain);
	}
	
	@AfterEach
	void cleanUp() {
		frontier = null;
	}

	@Test
	void testAdd() {
		String url = "https://wiprodigital.com/what-we-do/";
		frontier.add(url);
		assertEquals(1, frontier.size());
	}
	
	@Test
	void testFrontierIsEmpty() {
		assertEquals(0, frontier.size());
	}
	
	@Test
	void testPoll() {
		String url = "https://wiprodigital.com";
		frontier.add(url);
		String expected = "https://wiprodigital.com";
		String actual = frontier.poll();
		assertNotNull(actual, "The value returned from frontier.poll() call should not be null");
		assertEquals(expected, actual);
	}
	
	@Test
	void testSize() {
		String url = "https://wiprodigital.com";
		String url1 = "https://google.com";
		frontier.add(url);
		frontier.add(url1);
		int expected = 2;
		int actual = frontier.size();
		assertNotNull(actual, "The value expected value should not be null");
		assertEquals(expected, actual);
		frontier.poll();
		frontier.poll();
	    expected = 0;
		assertEquals(expected, frontier.size());
	}
	
	@Test
	void testUrlCanBeAdded() {
		String url = "https://wiprodigital.com";
		frontier.add(url);
		String url1 = "https://twitter.com";
		String domain = "wiprodigital.com";
		String uri = "http://wiprodigital.com";
		assertFalse(frontier.urlCanBeAdded(url1, domain, uri));
	}
	
	@Test
	void testUrlCanBeAddedForPreviouslyAddedUrl() {
		String url = "https://wiprodigital.com";
		frontier.add(url);
		String url1 = "https://wiprodigital.com";
		String domain = "wiprodigital.com";
		String uri = "http://wiprodigital.com";
		assertFalse(frontier.urlCanBeAdded(url1, domain, uri));
	}
}
