package hr.abysalto.hiring.mid.favourites.repository;

import hr.abysalto.hiring.mid.favourites.model.FavouriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavouritesRepository extends JpaRepository<FavouriteEntity, Integer> {

    void deleteByUserIdAndProductId(int userId, int productId);

    Optional<FavouriteEntity> findByUserIdAndProductId(int userId, int productId);

    List<FavouriteEntity> getAllByUserId(int userId);
}
