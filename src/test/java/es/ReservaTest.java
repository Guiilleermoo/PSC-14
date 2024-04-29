package es;

import org.junit.jupiter.api.Test;

import es.model.Cliente;

import es.model.Reserva;
import es.model.Viaje;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class ReservaTest {

    @Test
    public void testReservaConstructorAndGetters() {
        // crear ejemplos de las clases relacionadas
        Integer id = 1;
        Viaje viajeMock = mock(Viaje.class);
        Cliente clienteMock = mock(Cliente.class);
        Integer numPlazas = 5;

        // crear un objeto de la clase reserva
        Reserva reserva = new Reserva();
        reserva.setId(id);
        reserva.setViaje(viajeMock);
        reserva.setCliente(clienteMock);
        reserva.setNumPlazas(numPlazas);

        // verificar que el constructor y los getters funcionan correctamente
        assertEquals(id, reserva.getId());
        assertEquals(viajeMock, reserva.getViaje());
        assertEquals(clienteMock, reserva.getCliente());
        assertEquals(numPlazas, reserva.getNumPlazas());
    }
    @Test
    public void testToString() {
        // Crear una instancia de Reserva con datos de ejemplo
        Reserva reserva = new Reserva();
        reserva.setId(1);
        reserva.setViaje(new Viaje());
        reserva.setCliente(new Cliente());
        reserva.setNumPlazas(2);

        // Definir la representación esperada de la reserva como una cadena
        String expectedToString = "Reserva(id=1, viaje=Viaje(id=0, origen=null, destino=null, fecha=null, duracion=0, precio=0.0, oferta=0, empresa=null, asientosTotales=0, asientosDisponibles=0), cliente=Cliente(dni=null, nombre=null, gmail=null, telefono=null, residencia=null, password=null), numPlazas=2)";

        // Verificar que el método toString() devuelve la representación esperada
        assertEquals(expectedToString, reserva.toString());
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


        Reserva reserva1 = new Reserva();
        reserva1.setId(1);
        reserva1.setViaje(viaje1);
        reserva1.setCliente(cliente1);
        reserva1.setNumPlazas(2);

        Reserva reserva2 = new Reserva();
        reserva2.setId(1);
        reserva2.setViaje(viaje1);
        reserva2.setCliente(cliente1);
        reserva2.setNumPlazas(2);

        // Verificar que los hash codes de los trabajadores sean iguales
        assertEquals(reserva1.hashCode(), reserva2.hashCode());

        // Cambiar un atributo de uno de los trabajadores
        reserva2.setNumPlazas(3);

        // Verificar que los hash codes de los trabajadores ya no sean iguales
        assertNotEquals(reserva1.hashCode(), reserva2.hashCode());

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


        Reserva reserva1 = new Reserva();
        reserva1.setId(1);
        reserva1.setViaje(viaje1);
        reserva1.setCliente(cliente1);
        reserva1.setNumPlazas(2);

        Reserva reserva2 = new Reserva();
        reserva2.setId(1);
        reserva2.setViaje(viaje1);
        reserva2.setCliente(cliente1);
        reserva2.setNumPlazas(2);

        // Verificar que los dos trabajadores sean iguales
        assertTrue(reserva1.equals(reserva2));
        assertTrue(reserva2.equals(reserva1));

        // Cambiar un atributo de uno de los trabajadores
        reserva2.setId(2);

        // Verificar que los trabajadores ya no sean iguales
        assertFalse(reserva1.equals(reserva2));
        assertFalse(reserva2.equals(reserva1));
    }
}
