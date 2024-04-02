package es.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.model.Cliente;
import service.ClienteService;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
    
    
}
