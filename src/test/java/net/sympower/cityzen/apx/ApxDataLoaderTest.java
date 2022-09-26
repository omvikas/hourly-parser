package net.sympower.cityzen.apx;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApxDataLoaderTest {

  private Quote quote;
  private static final double DELTA = 1e-15;

  @Test
  public void load() throws Exception {

    Logger logger = LogManager.getLogger(net.sympower.cityzen.apx.ApxDataLoaderTest.class);

    ApxDataLoader sut = new ApxDataLoader();
    sut.url = getClass().getResource("apx-data.json");
    QuoteResponse response = sut.load();

    assertNotNull(response.equals(null));
    assertNotNull(response.quote.isEmpty());
    Assertions.assertTrue(response.quote.size()>0);

    Quote quoteFields = response.quote.get(0);
    QuoteValue quoteValueFields = response.quote.get(0).quoteValues.get(0);

    //logger.debug("The response object is "+response.quote.get(0));
    //logger.debug("The no. of items in JSON response is "+response.quote.size());
  }

  @Test
  public void parseTest() throws Exception {
    ApxDataLoader sut = new ApxDataLoader();
    sut.url = getClass().getResource("apx-data.json");
    QuoteResponse response = sut.load();
    System.out.println(response);

    JsonFactory factory = new JsonFactory();
    JsonParser parser = factory.createParser(sut.url);

    System.out.println("*******" + parser.toString());
    Quote quote = new Quote();
    QuoteValue quoteValue = new QuoteValue();
    while (!parser.isClosed()) {
      JsonToken jsonToken = parser.nextToken();

      System.out.println("jsonToken = " + jsonToken);

      if (JsonToken.FIELD_NAME.equals(jsonToken)) {
        String fieldName = parser.getCurrentName();
        System.out.println(fieldName);

        jsonToken = parser.nextToken();

        if ("market".equals(fieldName)) {
          quote.market = parser.getValueAsString();
          System.out.println("quote.market =" + quote.market);
          assertEquals("market", fieldName);
        }
        else if ("date_applied".equals(fieldName)) {
          quote.dateApplied = parser.getValueAsString();
          System.out.println("quote.dateApplied =" + quote.dateApplied);
          assertEquals("date_applied", fieldName);
        }
        else if ("tLabel".equals(fieldName)) {
          quoteValue.tLabel = parser.getValueAsString();
          System.out.println("quoteValue.tLabel =" + quoteValue.tLabel);
          assertEquals("tLabel", fieldName);
        }
        else if ("value".equals(fieldName)) {
          quoteValue.value = Float.parseFloat(parser.getValueAsString());
          System.out.println("quoteValue.value =" + quoteValue.value);
          assertEquals("value", fieldName);
        }
      }

    }
  }

}
