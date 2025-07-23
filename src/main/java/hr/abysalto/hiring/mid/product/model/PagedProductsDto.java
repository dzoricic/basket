package hr.abysalto.hiring.mid.product.model;

import java.util.List;

public record PagedProductsDto(
        List<ProductDto> products,
        Integer total,
        Integer page,
        Integer size
) {
}
