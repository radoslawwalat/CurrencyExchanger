package radoslawwalat.currencyconverter.convertMechanism;

import radoslawwalat.currencyconverter.model.NbpApiRepository;

public interface ConvertRatesInterface {


    // used to convert baseCurrency to targetCurrency by code value

    Double convert(Double amount, String baseCurrencyCode, String targetCurrencyCode);


    // used to update currency rates from external api
    void updateRates();

    // used to expose list of working currencies and their mid values

    Rates getRates();
}
