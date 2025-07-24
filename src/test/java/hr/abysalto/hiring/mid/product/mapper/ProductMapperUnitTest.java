package hr.abysalto.hiring.mid.product.mapper;

import hr.abysalto.hiring.mid.common.model.Pagination;
import hr.abysalto.hiring.mid.common.util.CryptUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static hr.abysalto.hiring.mid.factory.ProductFactory.*;
import static org.assertj.core.api.BDDAssertions.then;

public class ProductMapperUnitTest {

    @Test
    void shouldGetProductDtoGivenDummyJsonDto() {
        // given
        var givenDummyJsonId = 1;
        var givenDummyJsonDto = givenDummyJsonProductDto(givenDummyJsonId);

        // when
        var actual = ProductMapper.fromDummyDto(givenDummyJsonDto);

        // then
        then(actual.id()).isEqualTo(givenDummyJsonId);
        then(actual.basicInformation().title()).isEqualTo(givenDummyJsonDto.title());
        then(actual.basicInformation().description()).isEqualTo(givenDummyJsonDto.description());
        // ...
    }

    @Test
    void shouldGetPagedProductsDtoGivenDummyResponse() {
        // given
        var givenDummyProductsIds = List.of(1);
        var givenPagination = new Pagination(10, 1);
        var givenDummyJsonResponse = givenDummyJsonProductsResponse(givenDummyProductsIds, givenPagination);

        // when
        var actual = ProductMapper.fromResponse(givenDummyJsonResponse);

        // then
        then(actual.products().size()).isEqualTo(givenDummyProductsIds.size());
        then(actual.total()).isEqualTo(givenDummyJsonResponse.total());
        then(actual.size()).isEqualTo(givenDummyJsonResponse.limit());
        then(actual.page()).isEqualTo(resolvePage(givenDummyJsonResponse.skip(), givenDummyJsonResponse.limit()));
    }

    @Test
    void shouldGetProductApiModelGivenProductDto() {
        // given
        var givenProductId = 1;
        var givenProduct = givenProductDto(givenProductId);

        // when
        var actual = ProductMapper.toApiModel(givenProduct);

        // then
        then(actual.id()).isEqualTo(CryptUtils.encrypt(givenProductId));
        then(actual.title()).isEqualTo(givenProduct.basicInformation().title());
        then(actual.description()).isEqualTo(givenProduct.basicInformation().description());
        then(actual.brand()).isEqualTo(givenProduct.basicInformation().brand());
        // ...
    }

    @Test
    void shouldGetProductsResponseApiModelGivenPagedProductsDto() {
        // given
        var givenProductIds = List.of(1);
        var givenPagination = new Pagination(10, 1);
        var givenPagedProductsDto = givenPagedProductsDto(givenProductIds, givenPagination);

        // when
        var actual = ProductMapper.toApiModel(givenPagedProductsDto);

        // then
        then(actual.products().size()).isEqualTo(givenProductIds.size());
        then(actual.total()).isEqualTo(actual.total());
        then(actual.size()).isEqualTo(actual.size());
        then(actual.page()).isEqualTo(actual.page());
    }

    private int resolvePage(Integer skip, Integer limit) {
        return BigDecimal.valueOf(skip).divide(BigDecimal.valueOf(limit), RoundingMode.CEILING).intValue() + 1;
    }
}
