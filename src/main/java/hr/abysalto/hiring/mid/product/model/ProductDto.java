package hr.abysalto.hiring.mid.product.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record ProductDto(
        int id,
        BasicInformation basicInformation,
        Pricing pricing,
        OrderInformation orderInformation,
        Specifications specifications,
        List<Review> reviews,
        Meta meta
) {
    public record BasicInformation(
            String title,
            String description,
            String brand
    ) {
    }

    public record OrderInformation(
            String warrantyInformation,
            String shippingInformation,
            String availabilityStatus,
            String returnPolicy,
            Integer minimumOrderQuantity
    ) {
    }

    public record Specifications(
            BigDecimal width,
            BigDecimal height,
            BigDecimal depth,
            BigDecimal weight
    ) {
    }

    public record Meta(
            Instant createdAt,
            Instant updatedAt,
            String barcode,
            String qrCode,
            String stockKeepingUnit,
            int stock,
            String category,
            List<String> tags,
            BigDecimal rating,
            String thumbnail,
            List<String> images
    ) {
    }
}
