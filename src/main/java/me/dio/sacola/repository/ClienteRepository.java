package me.dio.sacola.repository;

import me.dio.sacola.model.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository  extends JpaRepository <Cliente, Long > {
}
