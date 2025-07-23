package hr.abysalto.hiring.mid.mapper;

import hr.abysalto.hiring.mid.client.models.ProductFilter;
import hr.abysalto.hiring.mid.model.Pagination;

import java.util.List;

public final class ProductFilterMapper {

    public static ProductFilter from(Pagination pagination) {
        return new ProductFilter(pagination.size(), pagination.size() * (pagination.page() - 1), List.of());
    }
}
