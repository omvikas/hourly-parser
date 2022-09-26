package net.sympower.cityzen.apxrefactored.serviceimpl;


import lombok.extern.slf4j.Slf4j;
import net.sympower.cityzen.apxrefactored.model.HourlyResponse;
import net.sympower.cityzen.apxrefactored.model.Quote;
import net.sympower.cityzen.apxrefactored.model.QuoteValue;
import net.sympower.cityzen.apxrefactored.service.IvxApxService;
import net.sympower.cityzen.apxrefactored.util.ApxDataLoaderRefactored;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

@Service
@Slf4j
public class IvxApxServiceImpl implements IvxApxService {


    private final ApxDataLoaderRefactored apxDataLoader;
    private static final String HOUR = "Hour";
    private static final String NET_VOLUME = "Net Volume";
    private static final String PRICE = "Price";

    private final UnaryOperator<String> dateProducer = stringDate -> {
        DateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
        dateFormat.setTimeZone(TimeZone.getTimeZone("CEST"));
        return dateFormat.format(new Date(Long.parseLong(stringDate)));
    };

    private final BiFunction<Quote,String,QuoteValue> getQuoteFunction = ((quote, label) -> {
        QuoteValue qv;
        qv = quote.getQuoteValues().stream().filter(e -> label.equals(e.getTLabel())).findFirst().orElse(null);
        return qv;
    });

    @Autowired
    public IvxApxServiceImpl(ApxDataLoaderRefactored apxDataLoader) {
        this.apxDataLoader = apxDataLoader;
    }

    @Override
    public List<HourlyResponse> getHourlyQuotes() {
        log.info("Inside IvxApxServiceImpl::getHourlyQuotes");
        List<Quote> quoteList = apxDataLoader.load();
        return editResponse(quoteList);

    }

    private List<HourlyResponse> editResponse(List<Quote> quoteList) {
        log.info("Inside editResponse");
        List<HourlyResponse> hourlyResponseList = new ArrayList<>();

        quoteList.forEach(quote->{

            HourlyResponse hourlyResponse = HourlyResponse.builder().build();
            hourlyResponse.setDate(dateProducer.apply(quote.getDateApplied()));
            setTime(quote, hourlyResponse);
            setVolume(quote, hourlyResponse);
            setPrice(quote, hourlyResponse);
            hourlyResponseList.add(hourlyResponse);

        });

        return hourlyResponseList;
    }

    private void setTime(Quote quote, HourlyResponse hourlyResponse) {
        log.info("Inside IvxApxServiceImpl::setTime");
        QuoteValue qv;
        qv = getQuoteFunction.apply(quote,HOUR);
        if (qv != null) {
            String time = String.valueOf(qv.getValue());
            String timeStr = time.split("\\.")[0];
            if (timeStr.length() == 1) {
                hourlyResponse.setHour(new StringBuilder("0").append(timeStr).append(":00").toString());
            } else {
                hourlyResponse.setHour(new StringBuilder(timeStr).append(":00").toString());
            }
        }
    }

    private void setVolume(Quote quote, HourlyResponse hourlyResponse) {
        log.info("Inside IvxApxServiceImpl::setVolume");
        QuoteValue qv;
        qv = getQuoteFunction.apply(quote,NET_VOLUME);
        if (qv != null) {
            String netVolume = qv.getValue().toString();
            String volumeUnit = qv.getUnit();
            hourlyResponse.setNetVolume(new StringBuilder(netVolume).append(" ").append(volumeUnit).toString());

        }
    }

    private void setPrice(Quote quote, HourlyResponse hourlyResponse) {
        log.info("Inside IvxApxServiceImpl::setPrice");
        QuoteValue qv;
        qv = getQuoteFunction.apply(quote,PRICE);
        if (qv != null) {
            String price = qv.getValue().toString();
            String priceUnit = qv.getUnit();
            hourlyResponse.setPrice(new StringBuilder(price).append(" ").append(priceUnit).toString());
        }
    }


}
