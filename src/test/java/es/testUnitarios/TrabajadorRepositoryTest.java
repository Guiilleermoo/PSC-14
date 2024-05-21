package es.testUnitarios;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import es.dao.TrabajadorRepository;
import es.model.Trabajador;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class TrabajadorRepositoryTest {

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @Test
    public void testDeleteByDni() {
        // Guardar un cliente de ejemplo
        Trabajador trabajador = new Trabajador();
        trabajador.setDni("78955017L");
        trabajador.setNombre("Unai Aira");
        trabajadorRepository.save(trabajador);

        // Eliminar el cliente por DNI
        trabajadorRepository.deleteByDni("78955017L");

        // Verificar que el cliente ha sido eliminado
        assertNull(trabajadorRepository.findByDni("78955017L"));
    }

    @Test
    public void testFindByDni() {
        // Guardar un cliente de ejemplo
        Trabajador trabajador = new Trabajador();
        trabajador.setDni("78955017L");
        trabajador.setNombre("Unai Aira");
        trabajadorRepository.save(trabajador);

        // Buscar el cliente por DNI
        Trabajador trabajadorEncontrado = trabajadorRepository.findByDni("78955017L");

        // Verificar que el cliente encontrado no es nulo y tiene el DNI correcto
        assertNotNull(trabajadorEncontrado);
        assertEquals("78955017L", trabajadorEncontrado.getDni());
    }
}
