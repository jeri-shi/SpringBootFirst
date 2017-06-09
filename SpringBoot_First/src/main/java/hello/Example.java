package hello;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 
 */

/**
 * @author shijin
 *
 */
@SpringBootApplication
public class Example {
  private static final Logger LOGGER = LogManager.getLogger(Example.class);

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.build();
  }

  public static void main(String[] args) throws Exception {
    // System.setProperty("spring.output.ansi.enabled", "ALWAYS");
    LOGGER.info("Spring Boot First Application is started.");
    SpringApplication.run(Example.class, args);

  }
}
