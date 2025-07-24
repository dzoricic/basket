package hr.abysalto.hiring.mid.common.exceptions;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ApiError(HttpStatus status,
                       String message,
                       String path,
                       List<String> details) {

    public ApiError(HttpStatus status, String message) {
        this(status, message, "", List.of());
    }

    public ApiError(HttpStatus status, String message, List<String> details) {
        this(status, message, "", details);
    }
}
