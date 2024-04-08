package es.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.model.Cliente;
import es.model.Reserva;
import es.service.ClienteService;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer>{
    void deleteById(int id);
    Reserva findById(int id);
    
}