package hr.abysalto.hiring.mid.favourites.service;

import hr.abysalto.hiring.mid.common.util.CryptUtils;
import hr.abysalto.hiring.mid.favourites.model.FavouriteEntity;
import hr.abysalto.hiring.mid.favourites.model.FavouriteId;
import hr.abysalto.hiring.mid.favourites.model.FavouritesDto;
import hr.abysalto.hiring.mid.favourites.repository.FavouritesRepository;
import hr.abysalto.hiring.mid.product.exception.ProductNotFound;
import hr.abysalto.hiring.mid.product.service.ProductService;
import hr.abysalto.hiring.mid.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FavouritesService {

    private final FavouritesRepository favouritesRepository;
    private final ProductService productService;
    private final UserService userService;

    public FavouritesDto getAllFavourites(String username) {
        var userId = userService.getUserByUsername(username).id();
        return getAllFavourites(userId);
    }

    public void addFavourite(String username, String encryptedProductId) {
        var userId = userService.getUserByUsername(username).id();
        var productId = CryptUtils.decrypt(encryptedProductId);
        addFavourite(userId, productId);
    }

    @Transactional
    public void removeFavourite(String username, String encryptedProductId) {
        var userId = userService.getUserByUsername(username).id();
        var productId = CryptUtils.decrypt(encryptedProductId);
        removeFavourite(userId, productId);
    }

    private FavouritesDto getAllFavourites(int userId) {
        var favourites = favouritesRepository.getAllByIdUserId(userId)
                .stream()
                .map(entity -> entity.getId().productId())
                .toList();
        return new FavouritesDto(favourites);
    }

    private void addFavourite(int userId, int productId) {
        if (!productService.exists(productId)) {
            throw new ProductNotFound("Product not found.");
        }
        var entity = new FavouriteEntity();
        var favouriteId = new FavouriteId(userId, productId);
        entity.setId(favouriteId);
        favouritesRepository.save(entity);
    }

    private void removeFavourite(int userId, int productId) {
        favouritesRepository.deleteById(new FavouriteId(userId, productId));
    }
}
