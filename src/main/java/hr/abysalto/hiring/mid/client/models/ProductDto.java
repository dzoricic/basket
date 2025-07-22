package hr.abysalto.hiring.mid.client.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ProductDto(
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
        Dimensions dimensions,
        String warrantyInformation,
        String shippingInformation,
        String availabilityStatus,
        List<Review> reviews,
        String returnPolicy,
        Integer minimumOrderQuantity,
        Meta meta,
        String thumbnail,
        List<String> images
) {
    public record Dimensions(
            double width,
            double height,
            double depth
    ) {
    }

    public record Review(
            int rating,
            String comment,
            LocalDateTime date,
            String reviewerName,
            String reviewerEmail
    ) {
    }

    public record Meta(
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            String barcode,
            String qrCode
    ) {
    }
}