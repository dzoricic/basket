package hr.abysalto.hiring.mid.basket.model;

import java.math.BigDecimal;

public record BasketEntryDto(
        int basketId,
        int productId,
        String productName,
        BigDecimal productPrice,
        int productCount
) {
}
