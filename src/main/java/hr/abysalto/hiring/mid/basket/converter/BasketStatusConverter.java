package hr.abysalto.hiring.mid.basket.converter;

import hr.abysalto.hiring.mid.basket.model.BasketStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;

@Converter(autoApply = false)
public class BasketStatusConverter implements AttributeConverter<BasketStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(BasketStatus status) {
        return Objects.isNull(status) ? null : status.getId();
    }

    @Override
    public BasketStatus convertToEntityAttribute(Integer id) {
        return Objects.isNull(id) ? null : BasketStatus.fromId(id);
    }
}
