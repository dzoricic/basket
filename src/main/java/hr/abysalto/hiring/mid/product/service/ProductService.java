package hr.abysalto.hiring.mid.product.service;

import hr.abysalto.hiring.mid.client.DummyJsonClient;
import hr.abysalto.hiring.mid.common.model.Pagination;
import hr.abysalto.hiring.mid.common.util.CryptUtils;
import hr.abysalto.hiring.mid.product.exception.ProductNotFound;
import hr.abysalto.hiring.mid.product.mapper.ProductMapper;
import hr.abysalto.hiring.mid.product.model.PagedProductsDto;
import hr.abysalto.hiring.mid.product.model.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final DummyJsonClient dummyJsonClient;

    public PagedProductsDto getProducts(Pagination pagination) {
        var dummyProductsResponse = dummyJsonClient.getProducts(pagination.page(), pagination.size());
        return ProductMapper.fromResponse(dummyProductsResponse);
    }

    public ProductDto getProduct(String encryptedProductId) {
        var productId = CryptUtils.decrypt(encryptedProductId);
        try {
            return ProductMapper.fromDummyDto(dummyJsonClient.getProduct(productId));
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ProductNotFound("Product with id %s not found.".formatted(encryptedProductId));
            }
            throw ex;
        }
    }

    public boolean exists(int productId) {
        try {
            var dummyDto = dummyJsonClient.getProduct(productId);
            return Objects.nonNull(dummyDto);
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                return false;
            }
            throw ex;
        }
    }
}
