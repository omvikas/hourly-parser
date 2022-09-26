package net.sympower.cityzen.apx.serviceImpl;

import net.sympower.cityzen.apxrefactored.model.HourlyResponse;
import net.sympower.cityzen.apxrefactored.model.Quote;
import net.sympower.cityzen.apxrefactored.model.QuoteValue;
import net.sympower.cityzen.apxrefactored.serviceimpl.IvxApxServiceImpl;
import net.sympower.cityzen.apxrefactored.util.ApxDataLoaderRefactored;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IvxApxServiceImplTest {

    @Mock
    private ApxDataLoaderRefactored loader;
    @InjectMocks
    private IvxApxServiceImpl service;

    @Test
    public void getHourlyQuotes(){
        //Given
        List<Quote> quoteList = new ArrayList<>();
        List<QuoteValue> quoteValues = new ArrayList<>();

        QuoteValue volumeValue = new QuoteValue("Net Volume",4514.20,"MWh");
        QuoteValue hourValue = new QuoteValue("Hour",01d,"none");
        QuoteValue priceValue = new QuoteValue("Price",34d,"Euro/MWh");
        quoteValues.add(volumeValue);
        quoteValues.add(hourValue);
        quoteValues.add(priceValue);

        Quote q1 = new Quote();
        q1.setDateApplied("1573599600000");
        q1.setMarket("APX Power NL Hourly");
        q1.setQuoteValues(quoteValues);
        q1.setQuoteValues(quoteValues);
        quoteList.add(q1);

        //when
        when(loader.load())
                .thenReturn(quoteList);

       List<HourlyResponse> response =  service.getHourlyQuotes();

       //then
        assertTrue(response.size() > 0);
        assertTrue("4514.2MWh".equals(response.get(0).getNetVolume().replaceAll("\\s", "")));
        assertTrue("34.0Euro/MWh".equals(response.get(0).getPrice().replaceAll("\\s", "")));
        assertTrue("01:00".equals(response.get(0).getHour().replaceAll("\\s", "")));
        assertTrue("Tue,12Nov201923:00:00GMT".equals(response.get(0).getDate().replaceAll("\\s", "")));

    }
}
