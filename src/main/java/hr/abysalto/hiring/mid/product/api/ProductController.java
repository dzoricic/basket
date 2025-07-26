package hr.abysalto.hiring.mid.product.api;

import hr.abysalto.hiring.mid.common.model.Pagination;
import hr.abysalto.hiring.mid.favourites.model.FavouritesDto;
import hr.abysalto.hiring.mid.favourites.model.FavouritesRequest;
import hr.abysalto.hiring.mid.favourites.service.FavouritesService;
import hr.abysalto.hiring.mid.product.mapper.ProductMapper;
import hr.abysalto.hiring.mid.product.model.ProductApiModel;
import hr.abysalto.hiring.mid.product.model.ProductsResponseApiModel;
import hr.abysalto.hiring.mid.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Products", description = "Retrieves products based on different search criteria.")
@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final FavouritesService favouritesService;

    @Operation(
            summary = "Get all available products paged.",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "text/plain",
                                    schema = @Schema(implementation = ProductsResponseApiModel.class),
                                    examples = @ExampleObject(ProductPayloadExamples.GET_PRODUCTS_RESPONSE)
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<ProductsResponseApiModel> getProducts(@Valid Pagination pagination) {
        var products = ProductMapper.toApiModel(productService.getProducts(pagination));
        return ResponseEntity.ok(products);
    }

    @Operation(
            summary = "Get product by id.",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "text/plain",
                                    schema = @Schema(implementation = ProductApiModel.class),
                                    examples = @ExampleObject(ProductPayloadExamples.GET_PRODUCT_RESPONSE)
                            )
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ProductApiModel> getProducts(@PathVariable String id) {
        var products = ProductMapper.toApiModel(productService.getProduct(id));
        return ResponseEntity.ok(products);
    }

    @Operation(
            summary = "Add product to favourites.",
            responses = {
                    @ApiResponse(
                            description = "Accepted",
                            responseCode = "201"
                    )
            }
    )
    @PostMapping("/favourites/add")
    public ResponseEntity<Void> addToFavourites(@RequestBody @Valid @NotNull FavouritesRequest favouritesRequest, Authentication authentication) {
        var username = authentication.getName();
        var encryptedProductId = favouritesRequest.productId();
        favouritesService.addFavourite(username, encryptedProductId);
        return ResponseEntity.accepted().build();
    }

    @Operation(
            summary = "Remove product from favourites.",
            responses = {
                    @ApiResponse(
                            description = "Accepted",
                            responseCode = "201"
                    )
            }
    )
    @PostMapping("/favourites/remove")
    public ResponseEntity<Void> removeFromFavourites(@RequestBody @Valid FavouritesRequest favouritesRequest, Authentication authentication) {
        var username = authentication.getName();
        var encryptedProductId = favouritesRequest.productId();
        favouritesService.removeFavourite(username, encryptedProductId);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/favourites")
    public ResponseEntity<FavouritesDto> getAllFavourites(Authentication authentication) {
        var favourites = favouritesService.getAllFavourites(authentication.getName());
        return ResponseEntity.ok(favourites);
    }
}
