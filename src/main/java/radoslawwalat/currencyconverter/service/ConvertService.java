package radoslawwalat.currencyconverter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import radoslawwalat.currencyconverter.convertMechanism.ConvertRatesInterface;
import radoslawwalat.currencyconverter.dto.RateDto;

import java.lang.invoke.MethodHandles;
import java.util.List;


@Service
public class ConvertService {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final ConvertRatesInterface convertRates;

    public ConvertService(ConvertRatesInterface convertRates) {
        this.convertRates = convertRates;
    }

    public Double exchange(Double amount, String baseCurrencyCode, String targetCurrencyCode) {
        log.info("Exchanging currency (amount: {}, baseCurrencyCode: {}, targetCurrencyCode: {}", amount,
                baseCurrencyCode, targetCurrencyCode);

        return convertRates.convert(amount, baseCurrencyCode, targetCurrencyCode);

    }

    public List<RateDto> printListOfRates(){
        return convertRates.getRates().getRates();
    }
}
