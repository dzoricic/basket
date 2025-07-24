package hr.abysalto.hiring.mid.product.model;

import java.util.List;

public record ProductsResponseApiModel(List<ProductApiModel> products,
                                       Integer total,
                                       Integer page,
                                       Integer size) {
}
