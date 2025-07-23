package hr.abysalto.hiring.mid.service;

import hr.abysalto.hiring.mid.client.DummyJsonApiClient;
import hr.abysalto.hiring.mid.mapper.ProductFilterMapper;
import hr.abysalto.hiring.mid.mapper.ProductsResponseMapper;
import hr.abysalto.hiring.mid.model.Pagination;
import hr.abysalto.hiring.mid.model.ProductsApiModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final DummyJsonApiClient dummyJsonApiClient;

    public ProductsApiModel getProducts(Pagination pagination) {
        var products = dummyJsonApiClient.getProducts(ProductFilterMapper.from(pagination));
        return ProductsResponseMapper.toProductsApiModel(products, pagination.page());
    }
}
