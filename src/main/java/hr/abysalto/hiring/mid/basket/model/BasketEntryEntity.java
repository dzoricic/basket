package hr.abysalto.hiring.mid.basket.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "basketEntry")
public class BasketEntryEntity {

    @EmbeddedId
    private BasketEntryId id;

    @MapsId("basketId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "basketId")
    private BasketEntity basketEntity;

    @Column(name = "productName", nullable = false)
    private String productName;

    @Column(name = "productPrice", nullable = false)
    private BigDecimal productPrice;
}
