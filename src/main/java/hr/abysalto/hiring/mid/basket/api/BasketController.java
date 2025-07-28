package hr.abysalto.hiring.mid.basket.api;

import hr.abysalto.hiring.mid.basket.mapper.BasketMapper;
import hr.abysalto.hiring.mid.basket.model.BasketApiModel;
import hr.abysalto.hiring.mid.basket.model.BasketStatus;
import hr.abysalto.hiring.mid.basket.model.BasketUpdateRequest;
import hr.abysalto.hiring.mid.basket.service.BasketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Basket", description = "Api for managing basket.")
@RestController
@RequestMapping("/api/basket")
@AllArgsConstructor
public class BasketController {

    private final BasketService basketService;


    @Operation(
            summary = "Get users active basket.",
            description = "Get current users active basket. If there is no active basket it will add the basket",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BasketApiModel.class),
                                    examples = @ExampleObject(BasketPayloadExamples.BASKET_RESPONSE_PAYLOAD)
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<BasketApiModel> getBasket(Authentication authentication) {
        var basket = basketService.getBasket(authentication.getName());
        return ResponseEntity.ok(BasketMapper.toApiModel(basket));
    }

    @Operation(
            summary = "Update product in users basket.",
            description = "This will add, remove or update the basket entry. If count is 0 it will remove the item. If there is no active basket it will add the basket",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BasketApiModel.class),
                                    examples = @ExampleObject(BasketPayloadExamples.BASKET_RESPONSE_PAYLOAD)
                            )
                    )
            }
    )
    @PatchMapping
    public ResponseEntity<BasketApiModel> updateBasket(@Valid @RequestBody BasketUpdateRequest request, Authentication authentication) {
        var basket = basketService.updateBasket(authentication.getName(), request.productId(), request.count());
        return ResponseEntity.ok(BasketMapper.toApiModel(basket));
    }

    @Operation(
            summary = "Close the active basket.",
            description = "This will probably not be used.",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BasketApiModel.class),
                                    examples = @ExampleObject(BasketPayloadExamples.BASKET_RESPONSE_PAYLOAD)
                            )
                    )
            }
    )
    @PutMapping
    public ResponseEntity<BasketApiModel> cancelBasket(Authentication authentication) {
        var basket = basketService.updateBasketStatus(authentication.getName(), BasketStatus.CANCELLED);
        return ResponseEntity.ok(BasketMapper.toApiModel(basket));
    }
}
