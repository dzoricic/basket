package hr.abysalto.hiring.mid.basket.exception;

import java.util.NoSuchElementException;

public class BasketNotFoundException extends NoSuchElementException {

    public BasketNotFoundException(String message) {
        super(message);
    }
}
