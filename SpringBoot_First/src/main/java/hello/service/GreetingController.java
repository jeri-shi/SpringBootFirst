/**
 * 
 */
package hello.service;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


/**
 * @author shijin
 *
 */
@RestController
public class GreetingController {
  private static final Logger LOGGER = LogManager.getLogger(GreetingController.class);
  private static final String TEMPLATE = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private DiscoveryClient client;
  
  @RequestMapping("/greeting")
  public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    LOGGER.trace("/greeting?name=" + name);
    return new Greeting(counter.incrementAndGet(), String.format(TEMPLATE, name));
  }
  
  @RequestMapping("/hello")
  public String home() {
    LOGGER.fatal("here is a FATAL message");
    LOGGER.error("here is a ERROR message");
    LOGGER.warn("here is a WARN message");
    LOGGER.info("here is a INFO message");
    LOGGER.debug("here is a DEBUG message");
    LOGGER.trace("here is a TRACE message");
    UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://localhost:8080/user?name={name}").buildAndExpand("dodo").encode();
    LOGGER.info(uriComponents);
    
    String netflixString = restTemplate.getForEntity("http://hello-netflix/netflix", String.class).getBody();
    String ret = "Hello World - Spring Boot!" + netflixString!=null?"(" + netflixString + ")":"";
    LOGGER.info("netflixString = " + ret);
    return ret;
  }

  @RequestMapping("/quote")
  public Quote quote() {
    LOGGER.trace("remote call /quote.");
    Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
    LOGGER.info("remote call:" + quote);
    return quote;
  }
  
  @RequestMapping("/netflix")
  public String netflix() {
    
    String hostName = client.getLocalServiceInstance().getHost();
    String serviceId = client.getLocalServiceInstance().getServiceId();
    int port = client.getLocalServiceInstance().getPort();
    
    LOGGER.info("/netflix, host:" + hostName + ", serviceId:" + serviceId + ", port:" + port);
    
    return "Netflix:" + port;
    
  }
}
