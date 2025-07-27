package hr.abysalto.hiring.mid.basket.model;

import java.util.List;

public record BasketApiModel(
        String id,
        BasketStatus basketStatus,
        List<BasketEntryApiModel> basketEntries
) {
}
