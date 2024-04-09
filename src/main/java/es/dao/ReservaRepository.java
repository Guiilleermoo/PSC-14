package es.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.model.Cliente;
import es.model.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer>{
    void deleteById(int id);
    Reserva findById(int id);
    List<Reserva> findByCliente(Cliente cliente);

}