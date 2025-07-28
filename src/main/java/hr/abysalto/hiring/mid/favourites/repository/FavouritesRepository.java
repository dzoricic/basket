package hr.abysalto.hiring.mid.favourites.repository;

import hr.abysalto.hiring.mid.favourites.model.FavouriteEntity;
import hr.abysalto.hiring.mid.favourites.model.FavouriteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouritesRepository extends JpaRepository<FavouriteEntity, Integer> {

    void deleteById(FavouriteId id);

    List<FavouriteEntity> getAllByIdUserId(int userId);
}
