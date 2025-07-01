package dev.training.library.repository;

import dev.training.library.model.RentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RentRepository extends JpaRepository<RentModel, Long> {
    List<RentModel> findByUserId(Long userId);
}
