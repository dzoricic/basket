package hr.abysalto.hiring.mid.product.api;

import hr.abysalto.hiring.mid.common.model.Pagination;
import hr.abysalto.hiring.mid.product.mapper.ProductMapper;
import hr.abysalto.hiring.mid.product.model.ProductsResponseApiModel;
import hr.abysalto.hiring.mid.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "Products", description = "Retrieves products based on different search criteria.")
@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(
            summary = "Get all available products paged.",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "text/plain",
                                    schema = @Schema(implementation = ProductsResponseApiModel.class),
                                    examples = @ExampleObject(ProductPayloadExamples.GET_PRODUCTS_PAYLOAD_RESPONSE)
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<ProductsResponseApiModel> getProducts(Authentication authentication, @Valid Pagination pagination) {
        log.info("Authentication name: {}", authentication.getName());
        var products = ProductMapper.toApiModel(productService.getProducts(pagination));
        return ResponseEntity.ok(products);
    }
}
