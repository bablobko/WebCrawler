/**
 * 
 */
package com.anusheel.webcrawler.data;

/**
 * @author Lenovo
 *
 */
public class HtmlParseData {
	
	private String url;
	private String html;
	private String text;
	private String mainHeader;
	private String outgoingUrls;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getMainHeader() {
		return mainHeader;
	}
	public void setMainHeader(String mainHeader) {
		this.mainHeader = mainHeader;
	}
	public String getOutgoingUrls() {
		return outgoingUrls;
	}
	public void setOutgoingUrls(String stringUrls) {
		this.outgoingUrls = stringUrls;
	}
}
