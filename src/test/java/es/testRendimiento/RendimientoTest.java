package es.testRendimiento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.github.noconnor.junitperf.JUnitPerfRule;
import com.github.noconnor.junitperf.JUnitPerfTest;
import com.github.noconnor.junitperf.JUnitPerfTestRequirement;
import com.github.noconnor.junitperf.reporting.providers.HtmlReportGenerator;

import es.controller.SqliteController;
import es.dao.ClienteRepository;
import es.dao.FavoritoRepository;
import es.dao.ReservaRepository;
import es.dao.TrabajadorRepository;
import es.dao.ViajeRepository;
import es.model.Cliente;
import es.model.Trabajador;
import es.model.Viaje;


public class RendimientoTest {

    private ViajeRepository viajeRepository;
    private SqliteController sqliteController = mock(SqliteController.class);
    private ClienteRepository clienteRepository;
    private FavoritoRepository favoritoRepository;
    private ReservaRepository reservaRepository;
    private TrabajadorRepository trabajadorRepository;


   
    @Before
    public void setUp() {
        viajeRepository = mock(ViajeRepository.class);
        clienteRepository = mock(ClienteRepository.class);
        favoritoRepository = mock(FavoritoRepository.class);
        reservaRepository = mock(ReservaRepository.class);
        trabajadorRepository = mock(TrabajadorRepository.class);
        sqliteController = new SqliteController(favoritoRepository, clienteRepository, reservaRepository, trabajadorRepository, viajeRepository); // Reemplaza "TuClase" con el nombre de tu clase que contiene el método getViajesTodos()
    }

    @Rule 
	public JUnitPerfRule perfTestRule = new JUnitPerfRule(new HtmlReportGenerator("target/junitperf/report.html"));
    /* 
    @Test
    @JUnitPerfTest(threads = 20, durationMs = 3000)
    @JUnitPerfTestRequirement(meanLatency = 100)
    public void testCrearTrabajador() {
        // Datos de prueba
        String jsonData = "{\"dni\": \"123456789\", \"nombre\": \"Unai\", \"gmail\": \"unaiaira@gmail.com\", \"telefono\": \"666444555\", \"sueldo\": 2000}";

        // Mock de la instancia de Trabajador
        Trabajador trabajador = new Trabajador();
        trabajador.setDni("123456789");
        trabajador.setNombre("Unai");
        trabajador.setGmail("unaiaira@gmail.com");
        trabajador.setTelefono("666444555");
        trabajador.setSueldo(2000);

        // Simular el guardado del trabajador
        when(trabajadorRepository.save(trabajador)).thenReturn(trabajador);

        // Llamar al método de creación de trabajador
        ResponseEntity<String> response = sqliteController.crearTrabajador(jsonData);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verificar que se devuelve el mensaje esperado
        assertEquals("trabajador ha sido guardado correctamente", response.getBody());
    }   

    @Test
    @JUnitPerfTest(threads = 20, durationMs = 3000)
    @JUnitPerfTestRequirement(meanLatency = 100)
    public void testCrearClienteCorrecto() {
        // Datos de prueba
        String jsonData = "{\"dni\": \"123456789\", \"nombre\": \"Unai\", \"gmail\": \"unaiaira@gmail.com\", \"telefono\": \"666444555\", \"residencia\": \"Deusto\", \"contrasena\": \"password\"}";

        // Simular el guardado del cliente
        when(clienteRepository.save(new Cliente())).thenReturn(new Cliente());

        // Llamar al método del controlador
        ResponseEntity<String> response = sqliteController.crearCliente(jsonData);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verificar que se devuelve el mensaje esperado
        assertEquals("cliente ha sido guardado correctamente", response.getBody());
    }
    */
    
    @Test
    @JUnitPerfTest(threads = 20, durationMs = 3000)
    @JUnitPerfTestRequirement(meanLatency = 100)
    public void testCrearViaje() {
        // Datos de prueba
        String jsonData = "{\"id\": 1, \"origen\": \"Bilbao\", \"destino\": \"Madrid\", \"fecha\": \"2024-04-30\", \"duracion\": 120, \"precio\": 50.0, \"oferta\": 0, \"empresa\": \"Deusto\", \"asientosTotales\": 50, \"asientosDisponibles\": 50}";

        // Llamar al método de creación de viaje
        ResponseEntity<String> response = sqliteController.crearViaje(jsonData);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verificar que se devuelve el mensaje esperado
        assertEquals("Viaje creado correctamente", response.getBody());
    }
    @Test
    @JUnitPerfTest(threads = 1, durationMs = 3000)
    public void testGetViajePerformance() {
        // Preparar datos de prueba
        Integer id = 1;
        Viaje viaje = new Viaje();
        Optional<Viaje> optionalViaje = Optional.of(viaje);

        // Simular el comportamiento del viajeRepository
        when(viajeRepository.findById(id)).thenReturn(optionalViaje);

        // Llamar a la función a probar
        ResponseEntity<Viaje> response = sqliteController.getViaje(id);

        // Verificar que la respuesta es la esperada
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(viaje, response.getBody());
    }
    @Test
    @JUnitPerfTest(threads = 1, durationMs = 3000)
    public void testGetTrabajadorExistente() {
        // Datos de prueba
        String gmail = "unaiaira@gmail.com";
        String password = "password";
        Trabajador trabajador = new Trabajador();
        trabajador.setGmail(gmail);
        trabajador.setPassword(password);

        // Simular la búsqueda del tarbajador en el repositorio
        when(trabajadorRepository.findByGmail(gmail)).thenReturn(trabajador);

        // Llamar al método de tu controlador
        ResponseEntity<Trabajador> response = sqliteController.getTrabajador(gmail, password);

        // Verificar que se devuelve un ResponseEntity con HttpStatus.OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verificar que se devuelve el cliente esperado
        assertEquals(trabajador, response.getBody());
    }

}
