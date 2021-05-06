package radoslawwalat.currencyconverter.convertMechanism;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import radoslawwalat.currencyconverter.dto.RateDto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class Rates implements Serializable {

    private LocalDateTime lastUpdated;
    private List<RateDto> rates = new ArrayList<>();

}
