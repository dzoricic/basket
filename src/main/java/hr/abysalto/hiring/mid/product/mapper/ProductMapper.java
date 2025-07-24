package hr.abysalto.hiring.mid.product.mapper;

import hr.abysalto.hiring.mid.client.models.DummyJsonProductDto;
import hr.abysalto.hiring.mid.client.models.DummyJsonProductsResponse;
import hr.abysalto.hiring.mid.common.util.CryptUtils;
import hr.abysalto.hiring.mid.product.model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class ProductMapper {

    public static ProductsResponseApiModel toApiModel(PagedProductsDto productsDto) {
        return new ProductsResponseApiModel(
                productsDto.products().stream().map(ProductMapper::toApiModel).toList(),
                productsDto.total(),
                productsDto.page(),
                productsDto.size()
        );
    }

    public static PagedProductsDto fromResponse(DummyJsonProductsResponse response) {
        return new PagedProductsDto(
                response.products().stream().map(ProductMapper::fromDummyDto).toList(),
                response.total(),
                resolvePage(response.skip(), response.limit()),
                response.limit()
        );
    }

    public static ProductApiModel toApiModel(ProductDto productDto) {
        var productSpecification = new ProductApiModel.ProductSpecification(
                productDto.specifications().width(),
                productDto.specifications().height(),
                productDto.specifications().depth(),
                productDto.specifications().weight(),
                productDto.meta().barcode(),
                productDto.meta().qrCode(),
                productDto.meta().category(),
                productDto.meta().tags(),
                productDto.meta().rating(),
                productDto.meta().thumbnail(),
                productDto.meta().images()
        );
        var purchasePolicy = new ProductApiModel.PurchasePolicy(
                productDto.orderInformation().warrantyInformation(),
                productDto.orderInformation().shippingInformation(),
                productDto.orderInformation().availabilityStatus(),
                productDto.orderInformation().returnPolicy(),
                productDto.orderInformation().minimumOrderQuantity()
        );
        return new ProductApiModel(
                CryptUtils.encrypt(productDto.id()),
                productDto.basicInformation().title(),
                productDto.basicInformation().description(),
                productDto.basicInformation().brand(),
                productDto.pricing(),
                productSpecification,
                purchasePolicy,
                productDto.reviews()
        );
    }

    public static ProductDto fromDummyDto(DummyJsonProductDto dummyDto) {
        var basicInformation = new ProductDto.BasicInformation(
                dummyDto.title(),
                dummyDto.description(),
                dummyDto.brand()
        );
        var pricing = new Pricing(
                dummyDto.price(),
                dummyDto.discountPercentage()
        );
        var orderInformation = new ProductDto.OrderInformation(
                dummyDto.warrantyInformation(),
                dummyDto.shippingInformation(),
                dummyDto.availabilityStatus(),
                dummyDto.returnPolicy(),
                dummyDto.minimumOrderQuantity()
        );
        var specification = new ProductDto.Specifications(
                dummyDto.dimensions().width(),
                dummyDto.dimensions().height(),
                dummyDto.dimensions().depth(),
                dummyDto.weight()
        );
        var meta = new ProductDto.Meta(
                dummyDto.meta().createdAt(),
                dummyDto.meta().updatedAt(),
                dummyDto.meta().barcode(),
                dummyDto.meta().qrCode(),
                dummyDto.sku(),
                dummyDto.stock(),
                dummyDto.category(),
                dummyDto.tags(),
                dummyDto.rating(),
                dummyDto.thumbnail(),
                dummyDto.images()
        );
        return new ProductDto(
                dummyDto.id(),
                basicInformation,
                pricing,
                orderInformation,
                specification,
                dummyDto.reviews(),
                meta
        );
    }

    private static int resolvePage(int skip, int limit) {
        return BigDecimal.valueOf(skip).divide(BigDecimal.valueOf(limit), RoundingMode.CEILING).intValue() + 1;
    }
}