package radoslawwalat.currencyconverter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class PostMappingDto {

    private Double amount;
    private String baseCurrency;
    private String targetCurrency;
}
