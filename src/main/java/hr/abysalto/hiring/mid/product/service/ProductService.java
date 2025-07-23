package hr.abysalto.hiring.mid.product.service;

import hr.abysalto.hiring.mid.client.DummyJsonClient;
import hr.abysalto.hiring.mid.common.model.Pagination;
import hr.abysalto.hiring.mid.product.mapper.ProductMapper;
import hr.abysalto.hiring.mid.product.model.PagedProductsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final DummyJsonClient dummyJsonClient;

    public PagedProductsDto getProducts(Pagination pagination) {
        var dummyProductsResponse = dummyJsonClient.getProducts(pagination.page(), pagination.size());
        return ProductMapper.fromResponse(dummyProductsResponse);
    }
}
