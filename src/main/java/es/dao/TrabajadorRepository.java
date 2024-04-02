package es.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.model.Trabajador;

@Repository
public interface TrabajadorRepository extends JpaRepository<Trabajador, Integer>{
    
}
