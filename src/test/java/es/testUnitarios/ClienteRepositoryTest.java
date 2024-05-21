package es.testUnitarios;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import es.dao.ClienteRepository;
import es.model.Cliente;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void testDeleteByDni() {
        // Guardar un cliente de ejemplo
        Cliente cliente = new Cliente();
        cliente.setDni("78955017L");
        cliente.setNombre("Unai Aira");
        clienteRepository.save(cliente);

        // Eliminar el cliente por DNI
        clienteRepository.deleteByDni("78955017L");

        // Verificar que el cliente ha sido eliminado
        assertNull(clienteRepository.findByDni("78955017L"));
    }

    @Test
    public void testFindByDni() {
        // Guardar un cliente de ejemplo
        Cliente cliente = new Cliente();
        cliente.setDni("78955017L");
        cliente.setNombre("Unai Aira");
        clienteRepository.save(cliente);

        // Buscar el cliente por DNI
        Cliente clienteEncontrado = clienteRepository.findByDni("78955017L");

        // Verificar que el cliente encontrado no es nulo y tiene el DNI correcto
        assertNotNull(clienteEncontrado);
        assertEquals("78955017L", clienteEncontrado.getDni());
    }
}
