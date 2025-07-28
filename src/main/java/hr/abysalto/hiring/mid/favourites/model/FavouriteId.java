package hr.abysalto.hiring.mid.favourites.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public record FavouriteId(Integer userId, Integer productId) implements Serializable {
}
