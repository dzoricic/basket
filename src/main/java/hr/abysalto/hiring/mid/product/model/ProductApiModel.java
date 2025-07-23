package hr.abysalto.hiring.mid.product.model;

import java.math.BigDecimal;
import java.util.List;

public record ProductApiModel(
        String id,
        String title,
        String description,
        String brand,
        Pricing pricing,
        ProductSpecification productSpecification,
        PurchasePolicy purchasePolicy,
        List<Review> reviews
) {

    public record ProductSpecification(
            BigDecimal width,
            BigDecimal height,
            BigDecimal depth,
            BigDecimal weight,
            String barcode,
            String qrCode,
            String category,
            List<String> tags,
            BigDecimal rating,
            String thumbnail,
            List<String> images
    ) {
    }

    public record PurchasePolicy(
            String warrantyInformation,
            String shippingInformation,
            String availabilityStatus,
            String returnPolicy,
            Integer minimumOrderQuantity
    ) {
    }
}
