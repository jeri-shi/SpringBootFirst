package hello;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 
 */

/**
 * @author shijin
 *
 */
@RestController
@EnableAutoConfiguration
public class Example {
  private static final Logger LOGGER = LogManager.getLogger(Example.class);

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
  
  public static void main(String[] args) throws Exception {
//    System.setProperty("spring.output.ansi.enabled", "ALWAYS");
    SpringApplication.run(Example.class, args);
    
  }
}
