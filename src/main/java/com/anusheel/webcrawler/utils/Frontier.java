/**
 * 
 */
package com.anusheel.webcrawler.utils;

import java.util.PriorityQueue;
import java.util.Queue;

import org.springframework.stereotype.Component;

/**
 * URLFrontier
 *
 */
@Component
public class Frontier {

	String domainName;

	Queue<String> priorityQueue;

	public Frontier() {
		super();
		priorityQueue = new PriorityQueue<>();
	}

	public Frontier(Queue<String> priorityQueue, String domainName) {
		super();
		this.priorityQueue = priorityQueue;
		this.domainName = domainName;
	}

	public void add(String outgoingUrls) {
		this.priorityQueue.add(outgoingUrls);
	}
	
	public boolean frontierIsEmpty() {
		if(this.priorityQueue.size() == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean urlCanBeAdded(String url, String domain, String uri) {
		if(priorityQueue.contains(url)) return false;
		this.domainName = domain;
		//url.contains(uri) ||
		String urlDomain = UrlUtils.getDomain(url);
		if(this.priorityQueue.contains(url) || !url.contains(this.domainName) || !urlDomain.equals(domain)) {
			return false;
		}
		
		return true;
		 
	}

	public String poll() {
		return this.priorityQueue.poll();
	}

	public int size() {
		return this.priorityQueue.size();
	}

}
