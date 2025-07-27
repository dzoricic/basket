package hr.abysalto.hiring.mid.basket.model;

import java.math.BigDecimal;

public record BasketEntryApiModel(
        String productId,
        String productName,
        BigDecimal productPrice,
        int productCount
) {
}
