package hr.abysalto.hiring.mid.mapper;

import hr.abysalto.hiring.mid.client.models.ProductsResponseDto;
import hr.abysalto.hiring.mid.model.ProductsApiModel;

public final class ProductsResponseMapper {

    public static ProductsApiModel toProductsApiModel(ProductsResponseDto productsDto, Integer page) {
        return new ProductsApiModel(productsDto.products(), productsDto.total(), page, productsDto.limit());
    }
}