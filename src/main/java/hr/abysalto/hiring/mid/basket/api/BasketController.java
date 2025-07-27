package hr.abysalto.hiring.mid.basket.api;

import hr.abysalto.hiring.mid.basket.model.BasketDto;
import hr.abysalto.hiring.mid.basket.model.BasketStatus;
import hr.abysalto.hiring.mid.basket.model.BasketUpdateRequest;
import hr.abysalto.hiring.mid.basket.service.BasketService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Basket", description = "Api for managing basket.")
@RestController
@RequestMapping("/api/basket")
@AllArgsConstructor
public class BasketController {

    private final BasketService basketService;

    @GetMapping
    public ResponseEntity<BasketDto> getBasket(Authentication authentication) {
        var username = authentication.getName();
        return ResponseEntity.ok(basketService.getBasket(username));
    }

    @PatchMapping("/update")
    public ResponseEntity<BasketDto> updateBasket(@Valid @NotNull BasketUpdateRequest request, Authentication authentication) {
        var username = authentication.getName();
        return ResponseEntity.ok(basketService.updateBasket(username, request.productId(), request.count()));
    }

    @PatchMapping("/cancel")
    public ResponseEntity<BasketDto> cancelBasket(Authentication authentication) {
        var username = authentication.getName();
        return ResponseEntity.ok(basketService.updateBasketStatus(username, BasketStatus.CANCELLED));
    }
}
