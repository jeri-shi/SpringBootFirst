/**
 * 
 */
package hello.service;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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

    return "Hello World - Spring Boot!";
  }

  @RequestMapping("/quote")
  public Quote quote() {
    LOGGER.trace("remote call /quote.");
    Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
    
    return quote;
  }
}
