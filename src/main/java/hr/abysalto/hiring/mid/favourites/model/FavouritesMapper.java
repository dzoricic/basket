package hr.abysalto.hiring.mid.favourites.model;

import hr.abysalto.hiring.mid.common.util.CryptUtils;

public final class FavouritesMapper {

    public static FavouritesApiModel toApiModel(FavouritesDto favouritesDto) {
        return new FavouritesApiModel(
                favouritesDto.favourites()
                        .stream()
                        .map(CryptUtils::encrypt)
                        .toList()
        );
    }
}
