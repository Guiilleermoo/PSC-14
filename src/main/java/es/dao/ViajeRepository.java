package es.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.model.Viaje;
import java.util.List;


@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Integer> {
    void deleteById(int id);
    Viaje findById(int id);
}
