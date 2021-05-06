package radoslawwalat.currencyconverter;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import radoslawwalat.currencyconverter.convertMechanism.ConvertRatesInterface;

@SpringBootApplication
public class CurrencyconverterApplication {

    @Autowired
    @Qualifier("Specified")
    private ConvertRatesInterface convertRatesInterface;


    public static void main(String[] args) {
        SpringApplication.run(CurrencyconverterApplication.class, args);
    }


    @Bean
    InitializingBean initData() {
        return this::reloadRates;
    }

    private void reloadRates() {
        convertRatesInterface.updateRates();
    }
}
