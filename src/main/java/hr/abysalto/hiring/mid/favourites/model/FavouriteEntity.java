package hr.abysalto.hiring.mid.favourites.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "favourites")
public class FavouriteEntity {

    @Column(name = "userId", nullable = false)
    private int userId;

    @Column(name = "productId", nullable = false)
    private int productId;
}
