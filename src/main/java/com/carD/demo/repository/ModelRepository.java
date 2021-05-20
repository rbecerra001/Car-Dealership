package com.carD.demo.repository;

import com.carD.demo.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
    List<Model> findByUserId(Long userId);
    Model findByIdAndUserId(Long modelId, Long userId);
    Model findByNameAndUserId(String modelName, Long userId);
}
