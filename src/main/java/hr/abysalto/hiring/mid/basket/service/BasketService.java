package hr.abysalto.hiring.mid.basket.service;

import hr.abysalto.hiring.mid.basket.exception.BasketNotFoundException;
import hr.abysalto.hiring.mid.basket.mapper.BasketMapper;
import hr.abysalto.hiring.mid.basket.model.*;
import hr.abysalto.hiring.mid.basket.repository.BasketRepository;
import hr.abysalto.hiring.mid.common.util.CryptUtils;
import hr.abysalto.hiring.mid.product.service.ProductService;
import hr.abysalto.hiring.mid.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class BasketService {

    private final BasketRepository basketRepository;
    private final ProductService productService;
    private final UserService userService;

    @Transactional
    public BasketDto getBasket(String username) {
        var userId = userService.getUserByUsername(username).id();
        var basket = getOrCreateNewBasket(userId);
        return BasketMapper.fromEntity(basket);
    }

    @Transactional
    public BasketDto updateBasket(String username, String encryptedProductId, int count) {
        var userId = userService.getUserByUsername(username).id();
        var productId = CryptUtils.decrypt(encryptedProductId);
        var activeBasket = getOrCreateNewBasket(userId);

        if (count == 0) {
            removeEntry(activeBasket, productId);
        } else {
            updateEntry(activeBasket, productId, count);
        }

        return BasketMapper.fromEntity(activeBasket);
    }

    public BasketDto updateBasketStatus(String username, BasketStatus basketStatus) {
        var userId = userService.getUserByUsername(username).id();
        return BasketMapper.fromEntity(updateBasketStatus(userId, basketStatus));
    }

    private void updateEntry(BasketEntity basketEntity, int productId, int count) {
        basketEntity.getBasketEntryEntities()
                .stream()
                .filter(entryEntity -> entryEntity.getId().productId() == productId)
                .findFirst()
                .ifPresentOrElse(
                        entryEntity -> entryEntity.setProductCount(count),
                        () -> addNewEntry(basketEntity, productId, count));
    }

    private void addNewEntry(BasketEntity basketEntity, int productId, int count) {
        var product = productService.getProduct(productId);

        var entryEntity = new BasketEntryEntity();
        entryEntity.setBasketEntity(basketEntity);
        var entryId = new BasketEntryId(basketEntity.getId(), productId);
        entryEntity.setId(entryId);
        entryEntity.setProductCount(count);
        entryEntity.setProductName(product.basicInformation().title());
        entryEntity.setProductPrice(resolvePrice(product.pricing().price(), product.pricing().discountPercentage(), count));

        basketEntity.getBasketEntryEntities().add(entryEntity);
    }

    private void removeEntry(BasketEntity basketEntity, int productId) {
        basketEntity.getBasketEntryEntities().removeIf(e -> e.getId().productId() == productId);
    }

    private BasketEntity getOrCreateNewBasket(int userId) {
        return findActiveBasket(userId)
                .map(basket -> {
                    log.info("Found basket {} for user {}.", basket.getId(), userId);
                    return basket;
                })
                .orElseGet(() -> {
                    log.warn("Couldn't find active basket for {}. Creating new one...", userId);
                    return createNewBasket(userId);
                });
    }

    private BasketEntity createNewBasket(int userId) {
        var entity = new BasketEntity();
        entity.setUserId(userId);
        entity.setBasketEntryEntities(new ArrayList<>());
        entity.setStatus(BasketStatus.PENDING);
        return basketRepository.saveAndFlush(entity);
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
