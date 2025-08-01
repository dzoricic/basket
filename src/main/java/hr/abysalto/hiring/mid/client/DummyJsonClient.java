package hr.abysalto.hiring.mid.client;

import hr.abysalto.hiring.mid.client.models.DummyJsonProductDto;
import hr.abysalto.hiring.mid.client.models.DummyJsonProductsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
public class DummyJsonClient {

    private static final String DUMMY_API_URI = "https://dummyjson.com";
    private static final String PRODUCTS_PATH = "/products";
    private static final String LIMIT_PARAM = "limit";
    private static final String SKIP_PARAM = "skip";

    private final RestClient restClient = RestClient.builder().baseUrl(DUMMY_API_URI).build();

    @Cacheable(value = "productsCache")
    public DummyJsonProductsResponse getProducts(int page, int size) {
        var path = PRODUCTS_PATH + buildQuery(page, size);
        return getRequest(path, DummyJsonProductsResponse.class);
    }

    @Cacheable(value = "productCache", key = "#id", condition = "#id > 0")
    public DummyJsonProductDto getProduct(int id) {
        var path = "%s/%d".formatted(PRODUCTS_PATH, id);
        return getRequest(path, DummyJsonProductDto.class);
    }

    private <T> T getRequest(String path, Class<T> responseType) {
        return restClient.get()
                .uri(path)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(responseType);
    }

    private String buildQuery(int page, int size) {
        var builder = UriComponentsBuilder.newInstance();

        builder.queryParam(LIMIT_PARAM, size);
        builder.queryParam(SKIP_PARAM, size * (page - 1));

        return "?%s".formatted(builder.build().getQuery());
    }
}
