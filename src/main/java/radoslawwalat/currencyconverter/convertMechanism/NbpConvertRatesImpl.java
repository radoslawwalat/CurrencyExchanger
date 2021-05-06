package radoslawwalat.currencyconverter.convertMechanism;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import radoslawwalat.currencyconverter.dto.RateDto;
import radoslawwalat.currencyconverter.dto.RatesTableDto;

import java.util.List;

@Component
@Qualifier("Specified")
public class NbpConvertRatesImpl extends AbstractConvertRates{

    @Override
    public void loadConvertRates(List<RateDto> newRates) {

        loadConvertRatesTable("A", newRates);
        loadConvertRatesTable("B", newRates);
    }

    private void loadConvertRatesTable(String tableName, List<RateDto> newRates) {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<RatesTableDto>> response;


        try{
            response = restTemplate.exchange("http://api.nbp.pl/api/exchangerates/tables/" + tableName,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<RatesTableDto>>() {});
        } catch (RestClientException e) {
            throw new IllegalStateException("Could not access NBP API");
        }

        List<RatesTableDto> ratesTableDtoList = response.getBody();

        try {
            if (!ratesTableDtoList.isEmpty()) {
                newRates.addAll(ratesTableDtoList.get(0).getRates());
            }
        } catch(NullPointerException e) {
            throw new IllegalStateException("NBP API data was not loaded properly");
        }

    }
}
