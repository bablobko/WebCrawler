# WebCrawler

1. Import it as a maven project in any ide and run it as a java project.

2. After the application has started running, without any issues, hit the localhost at port 9879, like this.
 curl -v --header "Content-Type: application/json" -d "{\"url\":\"http://www.wiprodigital.com\"}" http://localhost:9879/urls
 
3. Check the stage of the crawling by hitting this url  curl http://localhost:9879/stage

It may respond like this.
 $ curl http://localhost:9879/stage
    Crawling in progress

    OR

$ curl http://localhost:9879/stage
Crawling Completed

5.  To get more information about the domain under investigation, please hit curl http://localhost:9879/url/desc
