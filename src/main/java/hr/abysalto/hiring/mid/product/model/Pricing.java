package hr.abysalto.hiring.mid.product.model;

import java.math.BigDecimal;

public record Pricing(
        BigDecimal price,
        BigDecimal discountPercentage
) {
}
