package hr.abysalto.hiring.mid.client.models;

import hr.abysalto.hiring.mid.product.model.Review;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record DummyJsonProductDto(
        int id,
        String title,
        String description,
        String category,
        BigDecimal price,
        BigDecimal discountPercentage,
        BigDecimal rating,
        int stock,
        List<String> tags,
        String brand,
        String sku,
        BigDecimal weight,
        DummyJsonProductDto.Dimensions dimensions,
        String warrantyInformation,
        String shippingInformation,
        String availabilityStatus,
        List<Review> reviews,
        String returnPolicy,
        Integer minimumOrderQuantity,
        DummyJsonProductDto.Meta meta,
        String thumbnail,
        List<String> images
) {
    public record Dimensions(
            BigDecimal width,
            BigDecimal height,
            BigDecimal depth
    ) {
    }

    public record Meta(
            Instant createdAt,
            Instant updatedAt,
            String barcode,
            String qrCode
    ) {
    }
}