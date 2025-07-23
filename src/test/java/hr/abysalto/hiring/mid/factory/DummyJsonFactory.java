package hr.abysalto.hiring.mid.factory;

import hr.abysalto.hiring.mid.client.models.DummyJsonProductDto;
import hr.abysalto.hiring.mid.client.models.DummyJsonProductsResponse;
import hr.abysalto.hiring.mid.common.model.Pagination;

import java.util.List;

import static hr.abysalto.hiring.mid.constants.TestConstants.*;

public final class DummyJsonFactory {

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
        var dummyDtos = ids.stream().map(DummyJsonFactory::givenDummyJsonProductDto).toList();
        return new DummyJsonProductsResponse(dummyDtos, ids.size(), pagination.size() * (pagination.page() - 1), pagination.size());
    }
}
