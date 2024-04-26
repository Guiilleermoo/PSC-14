package es;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

import es.model.Viaje;

public class ViajeTest {

    private Viaje viaje;

    @Before
    public void setUp() {
        viaje = new Viaje();
        viaje.setId(1);
        viaje.setOrigen("Bilbao");
        viaje.setDestino("Madrid");
        viaje.setFecha("2022-12-31");
        viaje.setDuracion(120);
        viaje.setPrecio(100.0);
        viaje.setOferta(10);
        viaje.setEmpresa("Deusto");
        viaje.setAsientosTotales(100);
        viaje.setAsientosDisponibles(100);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(1, viaje.getId());
        assertEquals("Bilbao", viaje.getOrigen());
        assertEquals("Madrid", viaje.getDestino());
        assertEquals("2022-12-31", viaje.getFecha());
        assertEquals(120, viaje.getDuracion());
        assertEquals(100.0, viaje.getPrecio(), 0.01);
        assertEquals(10, viaje.getOferta());
        assertEquals("Deusto", viaje.getEmpresa());
        assertEquals(100, viaje.getAsientosTotales());
        assertEquals(100, viaje.getAsientosDisponibles()); 
    }

    @Test
    public void testCalcularPrecioFinal() {
        int precioFinal = viaje.calcularPrecioFinal();

        assertEquals(90, precioFinal); // 100 - 10%
    }

    @Test
    public void testOrElseThrowsUnsupportedOperationException() {
        // Creamos una instancia de la clase Viaje
        Viaje viaje = new Viaje();

        // Verificamos que llamar al método orElse lance una UnsupportedOperationException
        assertThrows(UnsupportedOperationException.class, () -> {
            viaje.orElse(new Object());
        });
    }

     @Test
    public void testEqualsAndHashCode() {
        // Creamos dos instancias de Viaje con los mismos atributos
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

        // Verificamos que los objetos sean iguales según el método equals
        assertTrue(viaje1.equals(viaje2));
        assertTrue(viaje2.equals(viaje1));

        // Verificamos que tengan el mismo hashCode
        assertEquals(viaje1.hashCode(), viaje2.hashCode());
    }

    @Test
    public void testNotEquals() {
        // Creamos dos instancias de Viaje con diferentes atributos
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
        viaje2.setId(2);
        viaje2.setOrigen("Barcelona");
        viaje2.setDestino("Madrid");
        viaje2.setFecha("2024-05-02");
        viaje2.setDuracion(4);
        viaje2.setPrecio(150.0);
        viaje2.setOferta(20);
        viaje2.setEmpresa("Vueling");
        viaje2.setAsientosTotales(50);
        viaje2.setAsientosDisponibles(40);

        // Verificamos que los objetos no sean iguales según el método equals
        assertFalse(viaje1.equals(viaje2));
        assertFalse(viaje2.equals(viaje1));

        // Aunque no son iguales, es posible que tengan el mismo hashCode, pero no es una garantía
        assertNotEquals(viaje1.hashCode(), viaje2.hashCode());
    }
}
