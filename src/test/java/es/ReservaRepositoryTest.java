package es;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import es.dao.ClienteRepository;
import es.dao.ReservaRepository;
import es.dao.ViajeRepository;
import es.model.Cliente;
import es.model.Reserva;
import es.model.Viaje;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ReservaRepositoryTest {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ViajeRepository viajeRepository;

    @Test
    public void testDeleteById() {
        // Guardar una reserva de ejemplo
        Reserva reserva = new Reserva();
        reserva.setId(1);
        reservaRepository.save(reserva);

        // Eliminar la reserva por ID
        reservaRepository.deleteById(1);

        // Verificar que la reserva ha sido eliminada
        assertNull(reservaRepository.findById(1));
    }

    @Test
    public void testFindById() {
        // Guardar una reserva de ejemplo
        Reserva reserva = new Reserva();
        reserva.setId(1);
        reservaRepository.save(reserva);

        // Buscar la reserva por ID
        Reserva reservaEncontrada = reservaRepository.findById(1);

        // Verificar que la reserva encontrada no es nula y tiene el ID correcto
        assertNotNull(reservaEncontrada);
        assertEquals(1, reservaEncontrada.getId());
    }

    @Test
    public void testFindByCliente() {
        // Crear un cliente de ejemplo
        Cliente cliente = new Cliente();
        cliente.setDni("78955017L");
        cliente.setNombre("Unai Aira");
        clienteRepository.save(cliente);

        // Crear un viaje de ejemplo
        Viaje viaje = new Viaje();
        viaje.setId(1);
        viaje.setOrigen("Madrid");
        viaje.setDestino("Barcelona");
        viaje.setFecha("2022-12-31");
        viaje.setDuracion(120);
        viaje.setPrecio(100.0);
        viaje.setOferta(10);
        viaje.setEmpresa("Deusto");
        viaje.setAsientosTotales(100);
        viaje.setAsientosDisponibles(100);
        viajeRepository.save(viaje);


        // Guardar una reserva asociada al cliente
        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setViaje(viaje);
        reserva.setId(1);
        reserva.setNumPlazas(1);
        reservaRepository.save(reserva);

        // Buscar las reservas por cliente
        List<Reserva> reservas = reservaRepository.findByCliente(cliente);

        // Verificar que la lista de reservas no sea nula y contenga la reserva guardada
        assertNotNull(reservas);
        assertFalse(reservas.isEmpty());
        assertTrue(reservas.contains(reserva));
    }
}

