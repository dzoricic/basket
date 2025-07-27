package hr.abysalto.hiring.mid.basket.model;

import java.util.List;

public record BasketDto(
        int id,
        int userId,
        BasketStatus status,
        List<BasketEntryDto> basketEntries
) {
}
