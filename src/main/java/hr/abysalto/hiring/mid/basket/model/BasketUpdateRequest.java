package hr.abysalto.hiring.mid.basket.model;

import jakarta.validation.constraints.NotNull;

public record BasketUpdateRequest(@NotNull String productId, int count) {
}
