package hr.abysalto.hiring.mid.model;

import hr.abysalto.hiring.mid.client.models.ProductDto;

import java.util.List;

public record ProductsApiModel(List<ProductDto> products,
                               Integer total,
                               Integer page,
                               Integer size) {
}
