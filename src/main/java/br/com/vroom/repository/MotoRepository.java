package br.com.vroom.repository;

import br.com.vroom.model.Moto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MotoRepository extends JpaRepository<Moto, Long> {
    List<Moto> findAllByOrderByProblemaAsc();
    List<Moto> findAllByOrderByModeloAsc();
}
