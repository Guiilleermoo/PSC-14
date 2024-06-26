package es.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.model.Trabajador;


@Repository
public interface TrabajadorRepository extends JpaRepository<Trabajador, String>{
    Trabajador findByDni(String dni);
    Trabajador findByGmail(String gmail);
    void deleteByDni(String dni);
}
