package hr.abysalto.hiring.mid.client.models;

import java.util.List;

public record ProductsResponseDto(List<ProductDto> products,
                                  int total,
                                  int skip,
                                  int limit) {
}
