package net.sympower.cityzen.apxrefactored.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class QuoteResponse {
    private List<Quote> quote;
}
