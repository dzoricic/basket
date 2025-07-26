package hr.abysalto.hiring.mid.product.exception;

import java.util.NoSuchElementException;

public class ProductNotFound extends NoSuchElementException {

    public ProductNotFound(String message) {
        super(message);
    }
}
