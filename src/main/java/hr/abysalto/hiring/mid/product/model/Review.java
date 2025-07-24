package hr.abysalto.hiring.mid.product.model;

import java.time.Instant;

public record Review(
        int rating,
        String comment,
        Instant date,
        String reviewerName,
        String reviewerEmail
) {
}
