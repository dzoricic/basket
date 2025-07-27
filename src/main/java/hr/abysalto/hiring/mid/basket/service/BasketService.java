package hr.abysalto.hiring.mid.basket.service;

import hr.abysalto.hiring.mid.basket.exception.BasketNotFoundException;
import hr.abysalto.hiring.mid.basket.mapper.BasketMapper;
import hr.abysalto.hiring.mid.basket.model.*;
import hr.abysalto.hiring.mid.basket.repository.BasketRepository;
import hr.abysalto.hiring.mid.common.util.CryptUtils;
import hr.abysalto.hiring.mid.product.service.ProductService;
import hr.abysalto.hiring.mid.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BasketService {

    private final BasketRepository basketRepository;
    private final ProductService productService;
    private final UserService userService;

    public BasketDto getBasket(String username) {
        var userId = userService.getUserByUsername(username).id();
        var basket = findActiveBasket(userId);
        return basket.map(BasketMapper::fromEntity)
                .orElse(BasketMapper.fromEntity(createNewBasket(userId)));
    }

    public BasketDto updateBasket(String username, String encryptedProductId, int count) {
        var userId = userService.getUserByUsername(username).id();
        var productId = CryptUtils.decrypt(encryptedProductId);
        return BasketMapper.fromEntity(updateBasket(userId, productId, count));
    }

    public BasketDto updateBasketStatus(String username, BasketStatus basketStatus) {
        var userId = userService.getUserByUsername(username).id();
        return BasketMapper.fromEntity(updateBasketStatus(userId, basketStatus));
    }

    private BasketEntity updateBasket(int userId, int productId, int count) {
        var activeBasket = findActiveBasket(userId);
        if (activeBasket.isEmpty()) {
            var basket = createNewBasket(userId);
            return addNewEntry(basket, productId, count);
        }
        return updateEntries(activeBasket.get(), productId, count);
    }

    private BasketEntity updateEntries(BasketEntity basketEntity, int productId, int count) {
        if (count == 0) {
            return removeEntry(basketEntity, productId);
        }
        return updateEntry(basketEntity, productId, count);
    }

    private BasketEntity updateEntry(BasketEntity basketEntity, int productId, int count) {
        var entries = Objects.isNull(basketEntity.getBasketEntryEntities())
                ? new ArrayList<BasketEntryEntity>()
                : new ArrayList<>(basketEntity.getBasketEntryEntities());
        var entry = entries.stream().filter(e -> e.getId().productId() == productId).findFirst();
        entry.ifPresent(entryEntity -> {
            entryEntity.setProductCount(count);
            entries.removeIf(e -> e.getId().productId() != productId);
            entries.add(entryEntity);
        });

        if (entry.isEmpty()) {
            return addNewEntry(basketEntity, productId, count);
        }

        basketEntity.setBasketEntryEntities(entries);
        return basketRepository.save(basketEntity);
    }

    private BasketEntity addNewEntry(BasketEntity basketEntity, int productId, int count) {
        var product = productService.getProduct(productId);
        var entryEntity = new BasketEntryEntity();
        var entryId = new BasketEntryId(basketEntity.getId(), product.id());
        entryEntity.setId(entryId);
        entryEntity.setProductCount(count);
        entryEntity.setProductName(product.basicInformation().title());
        entryEntity.setProductPrice(resolvePrice(product.pricing().price(), product.pricing().discountPercentage(), count));
        entryEntity.setBasketEntity(basketEntity);

        var entries = Objects.isNull(basketEntity.getBasketEntryEntities())
                ? new ArrayList<BasketEntryEntity>()
                : new ArrayList<>(basketEntity.getBasketEntryEntities());
        entries.add(entryEntity);

        basketEntity.setBasketEntryEntities(entries);
        return basketRepository.save(basketEntity);
    }

    private BasketEntity removeEntry(BasketEntity basketEntity, int productId) {
        var entries = Objects.isNull(basketEntity.getBasketEntryEntities())
                ? new ArrayList<BasketEntryEntity>()
                : new ArrayList<>(basketEntity.getBasketEntryEntities());
        entries.removeIf(entry -> entry.getId().productId() != productId);

        basketEntity.setBasketEntryEntities(entries);
        return basketRepository.save(basketEntity);
    }

    private BasketEntity createNewBasket(int userId) {
        var entity = new BasketEntity();
        entity.setUserId(userId);
        entity.setBasketEntryEntities(List.of());
        entity.setStatus(BasketStatus.PENDING);
        return basketRepository.save(entity);
    }

    private Optional<BasketEntity> findActiveBasket(int userId) {
        return basketRepository.findByUserIdAndStatus(userId, BasketStatus.PENDING);
    }

    private BasketEntity updateBasketStatus(int userId, BasketStatus basketStatus) {
        var basket = basketRepository.findByUserIdAndStatus(userId, BasketStatus.PENDING);
        return basket.map((basketEntity) -> {
                    basketEntity.setStatus(basketStatus);
                    return basketRepository.save(basketEntity);
                })
                .orElseThrow(() -> new BasketNotFoundException("Basket was not found."));
    }

    private BigDecimal resolvePrice(BigDecimal price, BigDecimal discount, int count) {
        var mathContext = new MathContext(2, RoundingMode.UP);
        return price.multiply(BigDecimal.valueOf(100).subtract(discount, mathContext))
                .multiply(BigDecimal.valueOf(count), new MathContext(2, RoundingMode.UP));
    }
}
