package hr.abysalto.hiring.mid.basket.model;

import hr.abysalto.hiring.mid.basket.converter.BasketStatusConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "basket")
public class BasketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Convert(converter = BasketStatusConverter.class)
    private BasketStatus status;

    private Instant createdAt;

    private Instant updatedAt;

    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BasketEntryEntity> basketEntryEntities;
}
