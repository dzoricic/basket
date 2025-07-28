package hr.abysalto.hiring.mid.basket.model;

import hr.abysalto.hiring.mid.basket.converter.BasketStatusConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "basket")
public class BasketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "userId", nullable = false)
    private Integer userId;

    @Convert(converter = BasketStatusConverter.class)
    private BasketStatus status;

    @CreationTimestamp
    @Column(name = "createdAt", updatable = false, nullable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt", nullable = false)
    private Instant updatedAt;

    @OneToMany(mappedBy = "basketEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BasketEntryEntity> basketEntryEntities;
}
