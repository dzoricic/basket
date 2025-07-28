package hr.abysalto.hiring.mid.favourites.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "favourites")
public class FavouriteEntity {

    @EmbeddedId
    private FavouriteId id;
}
