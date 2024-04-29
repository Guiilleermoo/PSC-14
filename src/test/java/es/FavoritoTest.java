package es;

import org.junit.jupiter.api.Test;

import es.model.Cliente;
import es.model.Favorito;
import es.model.Trabajador;
import es.model.Viaje;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class FavoritoTest {

    @Test
    public void testFavoritoConstructorAndGetters() {
        // Crear ejemplos de las clases relacionadas
        Integer id = 1;
        Viaje viajeMock = mock(Viaje.class);
        Cliente clienteMock = mock(Cliente.class);

        // Crear un objeto de la clase favorito
        Favorito favorito = new Favorito();
        favorito.setId(id);
        favorito.setViaje(viajeMock);
        favorito.setCliente(clienteMock);
        System.out.println(favorito);

        // Verificar que el constructor y los getters funcionan correctamente
        assertEquals(id, favorito.getId());
        assertEquals(viajeMock, favorito.getViaje());
        assertEquals(clienteMock, favorito.getCliente());
    }

    @Test
    public void testHashCode() {
        // Crear dos viajes con los mismos atributos
        Viaje viaje1 = new Viaje();
        viaje1.setId(1);
        viaje1.setOrigen("Madrid");
        viaje1.setDestino("Barcelona");
        viaje1.setFecha("2024-05-01");
        viaje1.setDuracion(3);
        viaje1.setPrecio(100.0);
        viaje1.setOferta(10);
        viaje1.setEmpresa("Renfe");
        viaje1.setAsientosTotales(50);
        viaje1.setAsientosDisponibles(50);

        Viaje viaje2 = new Viaje();
        viaje2.setId(1);
        viaje2.setOrigen("Madrid");
        viaje2.setDestino("Barcelona");
        viaje2.setFecha("2024-05-01");
        viaje2.setDuracion(3);
        viaje2.setPrecio(100.0);
        viaje2.setOferta(10);
        viaje2.setEmpresa("Renfe");
        viaje2.setAsientosTotales(50);
        viaje2.setAsientosDisponibles(50);

        // Crear dos clientes con los mismos atributos
        Cliente cliente1 = new Cliente();
        cliente1.setDni("78955017L");
        cliente1.setNombre("Unai Aira");
        cliente1.setGmail("unaiaira@gmail.com");
        cliente1.setTelefono("666444555");
        cliente1.setResidencia("Calle Deusto");
        cliente1.setPassword("contra");

        Cliente cliente2 = new Cliente();
        cliente2.setDni("78955017L");
        cliente2.setNombre("Unai Aira");
        cliente2.setGmail("unaiaira@gmail.com");
        cliente2.setTelefono("666444555");
        cliente2.setResidencia("Calle Deusto");
        cliente2.setPassword("contra");


        Favorito favorito1 = new Favorito();
        favorito1.setId(1);
        favorito1.setViaje(viaje1);
        favorito1.setCliente(cliente1);

        Favorito favorito2 = new Favorito();
        favorito2.setId(1);
        favorito2.setViaje(viaje2);
        favorito2.setCliente(cliente2);

        // Verificar que los hash codes de los trabajadores sean iguales
        assertEquals(favorito1.hashCode(), favorito2.hashCode());

        // Cambiar un atributo de uno de los trabajadores
        favorito2.setId(2);

        // Verificar que los hash codes de los trabajadores ya no sean iguales
        assertNotEquals(favorito1.hashCode(), favorito2.hashCode());

    }

    @Test
    public void testEquals() {
        // Crear dos trabajadores con los mismos atributos
        // Crear dos viajes con los mismos atributos
        Viaje viaje1 = new Viaje();
        viaje1.setId(1);
        viaje1.setOrigen("Madrid");
        viaje1.setDestino("Barcelona");
        viaje1.setFecha("2024-05-01");
        viaje1.setDuracion(3);
        viaje1.setPrecio(100.0);
        viaje1.setOferta(10);
        viaje1.setEmpresa("Renfe");
        viaje1.setAsientosTotales(50);
        viaje1.setAsientosDisponibles(50);

        Viaje viaje2 = new Viaje();
        viaje2.setId(1);
        viaje2.setOrigen("Madrid");
        viaje2.setDestino("Barcelona");
        viaje2.setFecha("2024-05-01");
        viaje2.setDuracion(3);
        viaje2.setPrecio(100.0);
        viaje2.setOferta(10);
        viaje2.setEmpresa("Renfe");
        viaje2.setAsientosTotales(50);
        viaje2.setAsientosDisponibles(50);

        // Crear dos clientes con los mismos atributos
        Cliente cliente1 = new Cliente();
        cliente1.setDni("78955017L");
        cliente1.setNombre("Unai Aira");
        cliente1.setGmail("unaiaira@gmail.com");
        cliente1.setTelefono("666444555");
        cliente1.setResidencia("Calle Deusto");
        cliente1.setPassword("contra");

        Cliente cliente2 = new Cliente();
        cliente2.setDni("78955017L");
        cliente2.setNombre("Unai Aira");
        cliente2.setGmail("unaiaira@gmail.com");
        cliente2.setTelefono("666444555");
        cliente2.setResidencia("Calle Deusto");
        cliente2.setPassword("contra");


        Favorito favorito1 = new Favorito();
        favorito1.setId(1);
        favorito1.setViaje(viaje1);
        favorito1.setCliente(cliente1);

        Favorito favorito2 = new Favorito();
        favorito2.setId(1);
        favorito2.setViaje(viaje2);
        favorito2.setCliente(cliente2);

        // Verificar que los dos trabajadores sean iguales
        assertTrue(favorito1.equals(favorito2));
        assertTrue(favorito2.equals(favorito1));

        // Cambiar un atributo de uno de los trabajadores
        favorito2.setId(2);

        // Verificar que los trabajadores ya no sean iguales
        assertFalse(favorito1.equals(favorito2));
        assertFalse(favorito2.equals(favorito1));
    }
}