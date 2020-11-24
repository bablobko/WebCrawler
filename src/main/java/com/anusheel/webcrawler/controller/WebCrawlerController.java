/**
 * 
 */
package com.anusheel.webcrawler.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.anusheel.webcrawler.data.UrlTitleMapList;
import com.anusheel.webcrawler.domain.SeedURLRepository;
import com.anusheel.webcrawler.request.SeedUrlRequest;
import com.anusheel.webcrawler.service.HTMLParserService;
import com.anusheel.webcrawler.service.WebCrawlerService;

/**
 * Controller
 *
 */
@RestController
public class WebCrawlerController {
	
	private static final Logger log = LoggerFactory.getLogger(WebCrawlerController.class);
	
	@Autowired
	private SeedURLRepository seedUrlRepository;
	
	@Autowired
	private HTMLParserService htmlParserService;
	
	@Autowired
	private WebCrawlerService webCrawlerService;
	
	@GetMapping("/")
	public ResponseEntity<?> hello(){
		return ResponseEntity.ok("<h1> Hello World</h1>");
	}
	
	@PostMapping("/urls")
	public ResponseEntity<?> seedUrl(@Valid @RequestBody SeedUrlRequest seedUrl) {
		Boolean val = null;
		URI location = null;
		log.info("The seedUrl method got hit " + seedUrl);
		try {
 		   val = seedUrlRepository.add(seedUrl);
		}catch(InterruptedException ie) {
			ie.printStackTrace();
		}
		if(val!= null && val == true) {
			try {
				location = new URI("http://localhost/url/desc");
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		    return ResponseEntity.created(location).body("Crawl Resource created successfully.\nPlease wait a while the operation completes.\nSite description will be available at /url/desc");
		}
		else {
			return ResponseEntity.unprocessableEntity().body("Crawl Resource Cannot Be Created. Please Kindly supply valid URL");
		}
			
	}
	
	@GetMapping("/url/desc")
	public UrlTitleMapList sendSiteDesc() {
		log.info("sendSiteDesc method has been hit.");
		if(webCrawlerService.crawlingCompletedStatus()) {
		    return htmlParserService.getUrlListMap();
		}else {
			return htmlParserService.getEmptyUrlListMap();
		}
	}
	
	@GetMapping("/stage")
	public ResponseEntity<?> crawlerCompletionStage() {
		log.info("crawlerCompletionStage method has been hit.");
		if(webCrawlerService.crawlingCompletedStatus()) {
			return ResponseEntity.status(HttpStatus.SEE_OTHER).body("Crawling Completed");
		}else {
			return ResponseEntity.accepted().body("Crawling In Progress.");
		}
	}

}
