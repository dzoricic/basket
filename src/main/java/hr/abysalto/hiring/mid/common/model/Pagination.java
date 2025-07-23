package hr.abysalto.hiring.mid.common.model;

import hr.abysalto.hiring.mid.common.validation.PaginationLimits;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Validated
@PaginationLimits
public record Pagination(Integer size, Integer page) {

    public static final Integer MAX_SIZE = 100;
    public static final Integer MAX_PAGE = 20;

    private static final Integer DEFAULT_SIZE = 20;
    private static final Integer DEFAULT_PAGE = 1;

    public Pagination(Integer size, Integer page) {
        this.size = Optional.ofNullable(size).orElse(DEFAULT_SIZE);
        this.page = Optional.ofNullable(page).orElse(DEFAULT_PAGE);
    }
}
