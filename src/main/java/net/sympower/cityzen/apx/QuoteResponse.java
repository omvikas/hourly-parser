package net.sympower.cityzen.apx;

import java.util.List;

public class QuoteResponse {

  public List<Quote> quote;

  @Override
  public String toString() {
    return "QuoteResponse{" +
      "quote=" + quote +
      '}';
  }
}
