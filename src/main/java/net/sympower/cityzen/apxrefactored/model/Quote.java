package net.sympower.cityzen.apxrefactored.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Quote {
    @JsonProperty("market")
    private String market;

    @JsonProperty("date_applied")
    private String dateApplied;

    @JsonProperty("values")
    private List<QuoteValue> quoteValues;

}
