package hr.abysalto.hiring.mid.client;

import hr.abysalto.hiring.mid.client.models.ProductFilter;
import hr.abysalto.hiring.mid.client.models.ProductsResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Service
@Slf4j
public class DummyJsonApiClient {

    private static final String DUMMY_API_URI = "https://dummyjson.com";
    private static final String PRODUCTS_PATH = "/products";
    private final RestClient restClient = RestClient.builder().baseUrl(DUMMY_API_URI).build();

    public ProductsResponseDto getProducts(ProductFilter productFilter) {
        var path = PRODUCTS_PATH + productFilter.getQueryParams().orElse("");
        return getRequest(path, ProductsResponseDto.class);
    }

    private <T> T getRequest(String path, Class<T> responseType) {
        try {
            return restClient.get()
                    .uri(path)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .body(responseType);
        } catch (RestClientException ex) {
            log.error("Error while calling {}", path, ex);
            throw ex;
        }
    }
}
