/**
 * 
 */
package com.anusheel.webcrawler.request;

import javax.validation.constraints.NotNull;

/**
 * @author Lenovo
 *
 */
public class SeedUrlRequest {
	@NotNull
	String url;
	
	

	public SeedUrlRequest() {
		super();
	}

	public SeedUrlRequest(String url) {
		super();
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "SeedUrlRequest [url=" + url + "]";
	}
	
}
