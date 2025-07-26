package hr.abysalto.hiring.mid.user.exception;

import java.util.NoSuchElementException;

public class UserNotFoundException extends NoSuchElementException {

    public UserNotFoundException(String key, String value) {
        super("User with %s: %s not found.".formatted(key, value));
    }
}
