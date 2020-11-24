package com.anusheel.webcrawler.utils;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UrlUtils {
	
	private static final Logger log = LoggerFactory.getLogger(UrlUtils.class);

	public static String getDomain(String url) {
		URI uri = null;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			log.info("Exception ocurred at " + e.getMessage() );
		}
		String domain = uri.getHost();
		return domain.startsWith("www.")? domain.substring(4):domain;
	}
	
	public static boolean isValidUrl(String url) {
		URL urlObject = null;
		try {
		   urlObject = new URL(url);
		   return true;
		}catch(MalformedURLException mue) {
		   log.debug("The url is not valid " + mue.getMessage());
		   return false;
		}
	}
	
	

}
