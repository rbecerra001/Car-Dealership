package com.carD.demo.repository;

import com.carD.demo.model.Make;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MakeRepository extends JpaRepository<Make, Long> {
    List<Make> findByUserId(Long userId);
    Optional<Make> findByIdAndUserId(Long artistId, Long userId);
}
