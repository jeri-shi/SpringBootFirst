/**
 * 
 */
package hello.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author shijin
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Value {
  private Long id;
  private String quote;

  public Value() {

  }

  /**
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @return the quote
   */
  public String getQuote() {
    return quote;
  }

  /**
   * @param quote the quote to set
   */
  public void setQuote(String quote) {
    this.quote = quote;
  }

  @Override
  public String toString() {
    return "Value{" + "id=" + id + ", quote=" + quote + '\'' + '}';
  }
}
