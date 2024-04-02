package es.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.model.Viaje;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Integer> {

}
