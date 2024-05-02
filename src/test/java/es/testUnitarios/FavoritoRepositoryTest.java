package es.testUnitarios;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import es.dao.ClienteRepository;
import es.dao.ViajeRepository;
import es.dao.FavoritoRepository;
import es.model.Cliente;
import es.model.Viaje;
import es.model.Favorito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class FavoritoRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ViajeRepository viajeRepository;

    @Autowired
    private FavoritoRepository favoritoRepository;

    @Test
    public void testFindById() {
        // Guardar una reserva de ejemplo
        // Crear un cliente de ejemplo
        Cliente cliente = new Cliente();
        cliente.setDni("78955017L");
        cliente.setNombre("Unai Aira");
        cliente.setResidencia("Deusto");
        cliente.setTelefono("666444555");
        cliente.setGmail("unaiaira@gmail.com");
        cliente.setPassword("contra");
        clienteRepository.save(cliente);

        // Crear un viaje de ejemplo
        Viaje viaje = new Viaje();
        viaje.setId(38);
        viaje.setOrigen("Barcelona");
        viaje.setDestino("Madrid");
        viaje.setFecha("2024-12-31");
        viaje.setDuracion(120);
        viaje.setPrecio(100.0);
        viaje.setOferta(10);
        viaje.setEmpresa("Deusto");
        viaje.setAsientosTotales(100);
        viaje.setAsientosDisponibles(100);
        viajeRepository.save(viaje);

        // Guardar una reserva asociada al cliente
        Favorito favorito = new Favorito();
        favorito.setId(9);
        favorito.setCliente(cliente);
        favorito.setViaje(viaje);
        favoritoRepository.save(favorito);

        // Buscar el favorito por ID
        Favorito favoritoBuscado = favoritoRepository.findById(9);
        System.out.println(favoritoBuscado);
        
        // Verificar que la reserva encontrada no es nula y tiene el ID correcto
        assertNotNull(favoritoBuscado);
        assertEquals(9, favorito.getId());       
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
        viaje.setId(38);
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
        Favorito favorito = new Favorito();
        favorito.setId(9);
        favorito.setCliente(cliente);
        favorito.setViaje(viaje);
        favoritoRepository.save(favorito);

        // Buscar las reservas por cliente
        List<Favorito> favoritos = favoritoRepository.findByClienteDni(cliente.getDni());

        // Verificar que la lista de reservas no sea nula y contenga la reserva guardada
        assertNotNull(favorito);
        assertFalse(favoritos.isEmpty());
        assertTrue(favoritos.contains(favorito));
    } 
}


