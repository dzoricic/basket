package hr.abysalto.hiring.mid.basket.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BasketStatus {
    PENDING(0),
    COMPLETED(1),
    CANCELLED(2);

    private final int id;

    public static BasketStatus fromId(int id) {
        for (BasketStatus status : values()) {
            if (status.id == id) return status;
        }
        throw new IllegalArgumentException("Invalid BasketStatus id: " + id);
    }
}
