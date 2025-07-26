package hr.abysalto.hiring.mid.product.exception;

public class InvalidProductIdException extends IllegalArgumentException {

    public static final String INVALID_PRODUCT_ID_ERROR_MESSAGE = "Invalid product Id provided.";

    public InvalidProductIdException() {
        super(INVALID_PRODUCT_ID_ERROR_MESSAGE);
    }
}
