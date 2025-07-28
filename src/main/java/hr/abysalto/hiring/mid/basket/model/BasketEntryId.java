package hr.abysalto.hiring.mid.basket.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public record BasketEntryId(
        @Column(name = "basketId", nullable = false)
        int basketId,
        @Column(name = "productId", nullable = false)
        int productId
) implements Serializable {
}
