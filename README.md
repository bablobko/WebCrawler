# WebCrawler

How to Run the Application :-

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

5.  To get more information about the domain under investigation, after crawling is completed,  please hit curl http://localhost:9879/url/desc


# Further Changes

1. If given a chance to further develop this, I would make it more distributed and more multithreaded, using join to combine the result from various threads and return back the final result.

2. The Frontier containing the URL would be a more elaborate datastructure with several components of various data structures like queue, priority queue and several other required things.

3. robot.txt would be incorporated so as to skip the urls from crawling procedure. That would have added to the politeness of the crawler.

4. No effort has been made in the submitted code for circumventing the spider traps. Given more time I would have certainly incorporated that in my crawler.

5. The design should be more scalable by adding more bandwidth and more machines we should be able to jack up the carwling rate.

6. Crawler should be running continuously and always fetching fresh content. That would be incorporated with more time at hand.

7. Changes in the World wide web are coming at a breathtaking pace, at this rate the, new technologies give rise to new web components, our crawler should be able to handle all of that and should have a vision to have more in the future, this entails a evolving architecture for the crawler, so as newer things appearing on the horizon could be easily blended in the existing scheme of things. This means that the web crawler should be modular so as to newer things could easily fitted into it. So modularity of design would be incorporated.


 
