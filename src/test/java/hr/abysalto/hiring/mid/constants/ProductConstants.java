package hr.abysalto.hiring.mid.constants;

import hr.abysalto.hiring.mid.product.model.Review;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public final class ProductConstants {

    public static final int ID = 1;
    public static final String TITLE = "Essence Mascara Lash Princess";
    public static final String DESCRIPTION = "The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula.";
    public static final String CATEGORY = "beauty";
    public static final BigDecimal PRICE = new BigDecimal("9.99");
    public static final BigDecimal DISCOUNT_PERCENTAGE = new BigDecimal("7.17");
    public static final BigDecimal RATING = new BigDecimal("4.94");
    public static final int STOCK = 5;
    public static final List<String> TAGS = List.of("beauty", "mascara");
    public static final String BRAND = "Essence";
    public static final String STOCK_KEEPING_UNIT = "RCH45Q1A";
    public static final BigDecimal WIDTH = BigDecimal.ONE;
    public static final BigDecimal HEIGHT = BigDecimal.ONE;
    public static final BigDecimal DEPTH = BigDecimal.ONE;
    public static final BigDecimal WEIGHT = BigDecimal.ONE;
    public static final String WARRANTY_INFORMATION = "1 month warranty";
    public static final String SHIPPING_INFORMATION = "Ships in 1 month";
    public static final String AVAILABILITY_STATUS = "Low Stock";
    public static final Review REVIEW = new Review(2, "Very unhappy with my purchase!", Instant.now(), "John Doe", "john.doe@x.dummyjson.com");
    public static final String RETURN_POLICY = "30 days return policy";
    public static final Integer MINIMUM_ORDER_QUANTITY = 24;
    public static final Instant CREATED_AT = Instant.now();
    public static final Instant UPDATED_AT = Instant.now();
    public static final String BARCODE = "9164035109868";
    public static final String QR_CODE = "...";
    public static final String THUMBNAIL = "...";
    public static final List<String> IMAGES = List.of("...", "...", "...");
}
