package radoslawwalat.currencyconverter.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ListData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // random value tracked, could be anything
    private LocalDateTime accessedAt;

    public ListData(LocalDateTime accessedAt) {
        this.accessedAt = accessedAt;
    }

}
