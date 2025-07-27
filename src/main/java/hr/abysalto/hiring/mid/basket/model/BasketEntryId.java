package hr.abysalto.hiring.mid.basket.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public record BasketEntryId(int basketId, int productId) implements Serializable {
}
