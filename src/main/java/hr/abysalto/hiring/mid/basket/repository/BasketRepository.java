package hr.abysalto.hiring.mid.basket.repository;

import hr.abysalto.hiring.mid.basket.model.BasketEntity;
import hr.abysalto.hiring.mid.basket.model.BasketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<BasketEntity, Integer> {

    Optional<BasketEntity> findByUserIdAndStatus(Integer userId, BasketStatus status);
}
