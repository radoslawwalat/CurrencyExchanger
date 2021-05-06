package radoslawwalat.currencyconverter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Table;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class GetData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timeAdded;
    private Double amount;
    private String baseCurrency;
    private String targetCurrency;
    private Double result;

    public GetData(LocalDateTime timeAdded, Double amount, String baseCurrency, String targetCurrency, Double result) {
        this.timeAdded = timeAdded;
        this.amount = amount;
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.result = result;
    }
}
