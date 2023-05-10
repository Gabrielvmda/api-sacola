package me.dio.sacola.repository;

import me.dio.sacola.model.entities.Sacola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SacolaRepository extends JpaRepository <Sacola, Long> {
}
    