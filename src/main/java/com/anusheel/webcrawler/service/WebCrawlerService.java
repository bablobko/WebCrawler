/**
 * 
 */
package com.anusheel.webcrawler.service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.anusheel.webcrawler.data.HtmlParseData;
import com.anusheel.webcrawler.utils.Frontier;
import com.anusheel.webcrawler.utils.UrlUtils;

/**
 * WebCrawlerService
 *
 */
@Service
public class WebCrawlerService {

	private static final Logger log = LoggerFactory.getLogger(WebCrawlerService.class);

	@Autowired
	HTMLParserService htmlParserService;

	@Autowired
	Frontier urlFrontier;

	private CompletableFuture<Boolean> crawlerCompletionStatus = null;

	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";

	@Async
	public CompletableFuture<Boolean> crawl(String url) throws InterruptedException {
		try {
			Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
			Document htmlDocument = connection.get();

			if (connection.response().statusCode() == 200) {
				log.info("\n VISITING Recieved web page at " + url);
			}
			if (!connection.response().contentType().contains("text/html")) {
				log.info("\n**FAILURE** Got something other than HTML.");
				crawlerCompletionStatus = CompletableFuture.completedFuture(false);
				return crawlerCompletionStatus;
			}
			String domain = UrlUtils.getDomain(url);
			log.info("\n\n\n The domain of the url --> " + url + " is ---------->" + domain + "\n\n\n");
			String uri = null;// UrlUtils.getUri(url);
			HtmlParseData parsedDocument = htmlParserService.parseDocument(htmlDocument, url);

			if (urlFrontier.urlCanBeAdded(url, domain, uri)) {
				urlFrontier.add(parsedDocument.getOutgoingUrls());
			}

			fetchAllTheUrls(htmlDocument, url, domain, uri);

			crawlerCompletionStatus = CompletableFuture.completedFuture(true);

			return crawlerCompletionStatus;
		} catch (IOException ioe) {
			log.error("Exception at crawl method of the CrawlerService ----->> " + ioe.getMessage());
			return CompletableFuture.completedFuture(false);
		}
	}

	private void fetchAllTheUrls(Document htmlDocument, String url, String domain, String uri) {
		Elements linksOnPage = htmlDocument.select("a[href]");
		for (Element page : linksOnPage) {
			String urlInPage = page.attr("abs:href");

			log.info("\n The newly received url is " + urlInPage);

			if (urlFrontier.urlCanBeAdded(urlInPage, domain, uri)) {
				log.info("The url " + urlInPage + " will be added to the Frontier.");
				urlFrontier.add(urlInPage);
			}
		}
		// Poll the Frontier and start crawling that page at the url.
		pollTheFrontier();
	}

	private void pollTheFrontier() {
		// Poll the Frontier and start crawling that page at the url.
		while(!urlFrontier.frontierIsEmpty()) {
			log.info("The size of the urlFrontier is " + urlFrontier.size());
			String topUrl = urlFrontier.poll();
			Connection connection = Jsoup.connect(topUrl).userAgent(USER_AGENT);
			Document documentOfTopUrl = null;
			try {
				documentOfTopUrl = connection.get();
			} catch (IOException e) {
				e.printStackTrace();
			}
			HtmlParseData parsedDocument = htmlParserService.parseDocument(documentOfTopUrl, topUrl);
		}
		
		if (urlFrontier.frontierIsEmpty()) {
			this.crawlerCompletionStatus.isDone();
		}

	}

	public boolean crawlingCompletedStatus() {
		if (urlFrontier.size() == 0) {
			return true; 
		}else{
		    return false;
		}
	}
}
