package net.sympower.cityzen.apx;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Quote {

  public String market;

  @JsonProperty("date_applied")
  public String dateApplied;

  @JsonProperty("values")
  public List<QuoteValue> quoteValues;

  @Override
  public String toString() {
    return "Quote{" +
      "market='" + market + '\'' +
      ", dateApplied='" + dateApplied + '\'' +
      ", quoteValues=" + quoteValues +
      '}';
  }
}
