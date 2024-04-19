package es;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import es.dao.ViajeRepository;
import es.model.Viaje;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ViajeRepositoryTest {

    @Autowired
    private ViajeRepository viajeRepository;

    @Test
    public void testDeleteById() {
        // Guardar una reserva de ejemplo
        Viaje viaje = new Viaje();
        viaje.setId(1);
        viajeRepository.save(viaje);

        // Eliminar la reserva por ID
        viajeRepository.deleteById(1);

        // Verificar que la reserva ha sido eliminada
        assertNull(viajeRepository.findById(1));
    }

    @Test
    public void testFindById() {
        // Guardar una reserva de ejemplo
        Viaje viaje = new Viaje();
        viaje.setId(1);
        viajeRepository.save(viaje);

        // Buscar la reserva por ID
        Viaje viajeEncontrada = viajeRepository.findById(1);

        // Verificar que la reserva encontrada no es nula y tiene el ID correcto
        assertNotNull(viajeEncontrada);
        assertEquals(1, viajeEncontrada.getId());
    }
}


