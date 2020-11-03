package com.anusheel.webcrawler.response;

public class UrlSavedResponse {

	private String statusMessage;
	
	public UrlSavedResponse() {
		super();
	}

	public UrlSavedResponse(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

}
