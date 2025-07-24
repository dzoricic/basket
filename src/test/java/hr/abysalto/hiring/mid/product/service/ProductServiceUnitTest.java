package hr.abysalto.hiring.mid.product.service;

import hr.abysalto.hiring.mid.client.DummyJsonClient;
import hr.abysalto.hiring.mid.common.model.Pagination;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static hr.abysalto.hiring.mid.factory.ProductFactory.givenDummyJsonProductsResponse;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceUnitTest {

    @Mock
    private DummyJsonClient dummyJsonClient;

    @InjectMocks
    private ProductService productService;

    @Test
    void shouldGetProductsByPageAndSize() {
        // given
        var givenPagination = new Pagination(20, 1);
        var givenResponseIds = List.of(1);
        var givenDummyDtoResponse = givenDummyJsonProductsResponse(givenResponseIds, givenPagination);

        when(dummyJsonClient.getProducts(givenPagination.page(),  givenPagination.size())).thenReturn(givenDummyDtoResponse);

        // when
        var actual = productService.getProducts(givenPagination);

        // then
        then(actual.products().size()).isEqualTo(1);
        then(actual.products()).extracting("id").isEqualTo(givenResponseIds);
        then(actual.size()).isEqualTo(givenDummyDtoResponse.limit());
        then(actual.total()).isEqualTo(givenDummyDtoResponse.total());
    }
}
