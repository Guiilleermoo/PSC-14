package es.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String>{
    void deleteByDni(String dni);
    Cliente findByDni(String dni);
    Cliente findByGmail(String gmail);
    
}
