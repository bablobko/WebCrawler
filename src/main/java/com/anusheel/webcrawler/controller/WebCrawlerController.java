/**
 * 
 */
package com.anusheel.webcrawler.controller;

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
	
	@GetMapping("/urls")
	public ResponseEntity<?> helloWorld(){
		return new ResponseEntity<>(HttpStatus.ACCEPTED).ok("<h1>Hello World From the second get method</h1>");
	}
	
	@PostMapping("/urls")
	public ResponseEntity<?> seedUrl(@Valid @RequestBody SeedUrlRequest seedUrl) {
		log.info("The seedUrl method got hit " + seedUrl);
		try {
 		   seedUrlRepository.add(seedUrl);
		}catch(InterruptedException ie) {
			ie.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.CREATED).ok("Crawl Resource created successfully.\nPlease wait a while the operation completes.\nSite description will be available at /url/desc");
	}
	
	@GetMapping("/url/desc")
	public UrlTitleMapList sendSiteDesc() {
		log.info("sendSiteDesc method has been hit.");
		//if crawl process completed.
		return htmlParserService.getUrlListMap();
	}
	
	@GetMapping("/stage")
	public String crawlerCompletionStage() {
		if(webCrawlerService.getStatus()) {
			return "Crawling Completed";
		}else {
			return "Crawling in progress";
		}
	}

}
