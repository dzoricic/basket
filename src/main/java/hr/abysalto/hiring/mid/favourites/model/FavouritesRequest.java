package hr.abysalto.hiring.mid.favourites.model;

import jakarta.validation.constraints.NotEmpty;

public record FavouritesRequest(@NotEmpty String productId) {
}
