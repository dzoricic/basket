package hr.abysalto.hiring.mid.basket.mapper;

import hr.abysalto.hiring.mid.basket.model.*;
import hr.abysalto.hiring.mid.common.util.CryptUtils;

public final class BasketMapper {

    public static BasketApiModel toApiModel(BasketDto dto) {
        return new BasketApiModel(
                CryptUtils.encrypt(dto.id()),
                dto.status(),
                dto.basketEntries()
                        .stream()
                        .map(BasketMapper::toApiModel)
                        .toList()
        );
    }

    public static BasketDto fromEntity(BasketEntity entity) {
        return new  BasketDto(
                entity.getId(),
                entity.getUserId(),
                entity.getStatus(),
                entity.getBasketEntryEntities()
                        .stream()
                        .map(BasketMapper::fromEntity)
                        .toList()
        );
    }

    private static BasketEntryApiModel toApiModel(BasketEntryDto dto) {
        return new BasketEntryApiModel(
                CryptUtils.encrypt(dto.productId()),
                dto.productName(),
                dto.productPrice(),
                dto.productCount()
        );
    }

    private static BasketEntryDto fromEntity(BasketEntryEntity entity) {
        return new BasketEntryDto(
                entity.getId().basketId(),
                entity.getId().productId(),
                entity.getProductName(),
                entity.getProductPrice(),
                entity.getProductCount()
        );
    }
}
