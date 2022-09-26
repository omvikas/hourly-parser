package net.sympower.cityzen.apxrefactored.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HourlyResponse {

    private String date;
    private String hour;
    private String netVolume;
    private String price;
}
