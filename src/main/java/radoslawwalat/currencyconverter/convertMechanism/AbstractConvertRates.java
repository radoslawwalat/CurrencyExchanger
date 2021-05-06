package radoslawwalat.currencyconverter.convertMechanism;

import lombok.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import radoslawwalat.currencyconverter.dto.RateDto;

import java.lang.invoke.MethodHandles;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractConvertRates implements ConvertRatesInterface {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private Rates rates;

    @Override
    public Rates getRates(){
        return this.rates;
    }

    @Override
    public Double convert(Double amount, String baseCurrencyCode, String targetCurrencyCode) {

        updateRates();

        Double baseCurrencyRate = getCurrencyRate(baseCurrencyCode);
        Double targetCurrencyRate = getCurrencyRate(targetCurrencyCode);

        Double result = amount * baseCurrencyRate;
        return result / targetCurrencyRate;

    }

    @Override
    public void updateRates() {

        if (rates.getLastUpdated() != null && LocalDateTime.now().isBefore(rates.getLastUpdated().plusDays(1))) {
            return;
        }

        List<RateDto> newRates = new ArrayList<>();
        loadConvertRates(newRates);
        rates.setRates(newRates);
        rates.setLastUpdated(LocalDateTime.now());

        log.info("Currency rates updated");
    }


    private Double getCurrencyRate(String providedCurrencyCode) {

        if (providedCurrencyCode.isEmpty()){
            throw new IllegalStateException("Please provide not empty currency code");
        }

        RateDto rate = rates.getRates().stream()
                .filter(r -> r.getCode().contentEquals(providedCurrencyCode))
                .findFirst()
                .orElse(null);

        if (rate == null) {
            throw new IllegalStateException(String.format("Could not find currency code %s", providedCurrencyCode));
        }

        return rate.getMid();
    }

    public abstract void loadConvertRates(List<RateDto> newRates);
}
