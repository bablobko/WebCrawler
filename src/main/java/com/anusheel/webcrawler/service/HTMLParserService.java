package com.anusheel.webcrawler.service;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anusheel.webcrawler.data.HtmlParseData;
import com.anusheel.webcrawler.data.UrlTitleMap;
import com.anusheel.webcrawler.data.UrlTitleMapList;

@Service
public class HTMLParserService {
	
	@Autowired
	private UrlTitleMapList urlTitleMapList;
	
	

	public HTMLParserService() {
		super();
	}
	
	
	public HTMLParserService(UrlTitleMapList urlTitleMapList) {
		super();
		this.urlTitleMapList = urlTitleMapList;
	}


	public HtmlParseData parseDocument(Document htmlDocument, String url) {
		HtmlParseData parsedPageContent = new HtmlParseData();
		Elements hTags = htmlDocument.select("h1");
		Elements h1Tags = hTags.select("h1");
		String titleTagText = h1Tags.text();
		UrlTitleMap urlTitleMap = new UrlTitleMap(url, titleTagText);
		this.urlTitleMapList.add(urlTitleMap);
		parsedPageContent.setMainHeader(titleTagText);
		Elements absHref = htmlDocument.select("a[href]");		
		parsedPageContent.setOutgoingUrls(absHref.attr("abs:href"));
		return parsedPageContent;
	}


	public UrlTitleMapList getUrlListMap() {
		return this.urlTitleMapList;
	}

}
