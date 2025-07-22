package hr.abysalto.hiring.mid.client.models;

import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ProductFilter {
    private Integer limit;
    private Integer skip;
    private List<String> select;

    public Optional<String> getQueryParams() {
        var builder = UriComponentsBuilder.newInstance();

        Optional.ofNullable(limit).ifPresent(l -> builder.queryParam("limit", l));
        Optional.ofNullable(skip).ifPresent(s -> builder.queryParam("skip", s));
        Optional.ofNullable(select).ifPresent(s -> {
            if (!s.isEmpty()) {
                builder.queryParam("select", String.join(",", s));
            }
        });

        return Optional.ofNullable(builder.build().getQuery())
                .filter(StringUtils::hasText)
                .map(query -> "?" + query);
    }
}
