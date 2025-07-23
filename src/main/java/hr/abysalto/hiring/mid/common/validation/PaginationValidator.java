package hr.abysalto.hiring.mid.common.validation;

import hr.abysalto.hiring.mid.common.model.Pagination;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

import static hr.abysalto.hiring.mid.common.model.Pagination.MAX_PAGE;
import static hr.abysalto.hiring.mid.common.model.Pagination.MAX_SIZE;

public class PaginationValidator implements ConstraintValidator<PaginationLimits, Pagination> {

    @Override
    public boolean isValid(Pagination pagination, ConstraintValidatorContext context) {
        if (Objects.isNull(pagination)) {
            return true;
        }
        if (pagination.size() > MAX_SIZE || pagination.size() < 1) {
            return false;
        }
        return pagination.page() <= MAX_PAGE && pagination.page() >= 1;
    }
}
