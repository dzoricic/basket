package hr.abysalto.hiring.mid.client.models;

import java.util.List;

public record DummyJsonProductsResponse(List<DummyJsonProductDto> products,
                                        int total,
                                        int skip,
                                        int limit) {
}
