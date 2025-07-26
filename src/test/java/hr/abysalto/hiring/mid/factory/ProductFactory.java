package hr.abysalto.hiring.mid.factory;

import hr.abysalto.hiring.mid.client.models.DummyJsonProductDto;
import hr.abysalto.hiring.mid.client.models.DummyJsonProductsResponse;
import hr.abysalto.hiring.mid.common.model.Pagination;
import hr.abysalto.hiring.mid.product.model.PagedProductsDto;
import hr.abysalto.hiring.mid.product.model.Pricing;
import hr.abysalto.hiring.mid.product.model.ProductDto;

import java.util.List;

import static hr.abysalto.hiring.mid.constants.ProductConstants.*;

public final class ProductFactory {

    public static DummyJsonProductDto givenDummyJsonProductDto(int id) {
        return new DummyJsonProductDto(
                id,
                TITLE,
                DESCRIPTION,
                CATEGORY,
                PRICE,
                DISCOUNT_PERCENTAGE,
                RATING,
                STOCK,
                TAGS,
                BRAND,
                STOCK_KEEPING_UNIT,
                WEIGHT,
                new DummyJsonProductDto.Dimensions(WIDTH, HEIGHT, DEPTH),
                WARRANTY_INFORMATION,
                SHIPPING_INFORMATION,
                AVAILABILITY_STATUS,
                List.of(REVIEW),
                RETURN_POLICY,
                MINIMUM_ORDER_QUANTITY,
                new DummyJsonProductDto.Meta(CREATED_AT, UPDATED_AT, BARCODE, QR_CODE),
                THUMBNAIL,
                IMAGES
        );
    }

    public static DummyJsonProductsResponse givenDummyJsonProductsResponse(List<Integer> ids, Pagination pagination) {
        var dummyDtos = ids.stream().map(ProductFactory::givenDummyJsonProductDto).toList();
        return new DummyJsonProductsResponse(dummyDtos, ids.size(), pagination.size() * (pagination.page() - 1), pagination.size());
    }

    public static ProductDto givenProductDto(int id) {
        var basicInformation = new ProductDto.BasicInformation(
                TITLE,
                DESCRIPTION,
                BRAND
        );
        var pricing = new Pricing(
                PRICE,
                DISCOUNT_PERCENTAGE
        );
        var orderInformation = new ProductDto.OrderInformation(
                WARRANTY_INFORMATION,
                SHIPPING_INFORMATION,
                AVAILABILITY_STATUS,
                RETURN_POLICY,
                MINIMUM_ORDER_QUANTITY
        );
        var specification = new ProductDto.Specifications(
                WIDTH,
                HEIGHT,
                DEPTH,
                WEIGHT
        );
        var meta = new ProductDto.Meta(
                CREATED_AT,
                UPDATED_AT,
                BARCODE,
                QR_CODE,
                STOCK_KEEPING_UNIT,
                STOCK,
                CATEGORY,
                TAGS,
                RATING,
                THUMBNAIL,
                IMAGES
        );
        return new ProductDto(
                id,
                basicInformation,
                pricing,
                orderInformation,
                specification,
                List.of(REVIEW),
                meta
        );
    }

    public static PagedProductsDto givenPagedProductsDto(List<Integer> ids, Pagination pagination) {
        var productDtos =  ids.stream().map(ProductFactory::givenProductDto).toList();
        return new PagedProductsDto(productDtos, ids.size(), pagination.page(), pagination.size());
    }
}
