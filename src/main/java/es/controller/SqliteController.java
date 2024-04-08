package es.controller;

import java.util.Optional;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.dao.ClienteRepository;
import es.dao.ReservaRepository;
import es.dao.TrabajadorRepository;
import es.dao.ViajeRepository;
import es.model.*;


@RestController
@RequestMapping("/sql")
public class SqliteController {
    
    ClienteRepository clienteRepository;
    ReservaRepository reservaRepository;
    TrabajadorRepository trabajadorRepository;
    ViajeRepository viajeRepository;

    public SqliteController(ClienteRepository clienteRepository, ReservaRepository reservaRepository, TrabajadorRepository trabajadorRepository, ViajeRepository viajeRepository) {
        this.clienteRepository = clienteRepository;
        this.reservaRepository = reservaRepository;
        this.trabajadorRepository = trabajadorRepository;
        this.viajeRepository = viajeRepository; 
    }

    @CrossOrigin("http://127.0.0.1:5500")
 /*    @GetMapping("/buscarTrabajador/{dni}")
    public ResponseEntity<String> getTrabajador(@PathVariable String dni) {
        Trabajador trabajador = trabajadorRepository.findByDni(dni);

        if (trabajador == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
         }
*/
    @GetMapping("/buscarTrabajador")
        public ResponseEntity<String> getTrabajador(@RequestBody String jsonData) {
        try {
            JSONObject data = new JSONObject(jsonData);
            Trabajador t = trabajadorRepository.findByDni(data.getString("dni"));

            return new ResponseEntity<>( HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("No se ha encontrado el trabajador " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin("http://127.0.0.1:5500")
    @PostMapping("/crearTrabajador")
    public ResponseEntity<String>  crearTrabajador(@RequestBody String jsonData) {
    
        try {
            JSONObject data = new JSONObject(jsonData);
            Trabajador t = new Trabajador();
            t.setDni(data.getString("dni"));
            t.setNombre(data.getString("nombre"));   
            t.setGmail(data.getString("gmail"));
            t.setTelefono(data.getString("telefono"));
            t.setSueldo(data.getInt("sueldo"));
        

            trabajadorRepository.save(t);
            return new ResponseEntity<>("trabajador  ha sido guardado correctamente", HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error al crear el trabajador -> %s", e.getMessage()));
        }
    }

    @CrossOrigin("http://127.0.0.1:5500")
    @DeleteMapping("/eliminarTrabajador")
    public ResponseEntity<String> eliminarTrabajador(@RequestBody String jsonData) {
        try {
            JSONObject data = new JSONObject(jsonData);
            Trabajador t = trabajadorRepository.findByDni(data.getString("dni"));
            if (t != null) {
                trabajadorRepository.delete(t);
                return new ResponseEntity<>("Trabajador ha sido eliminado correctamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No se ha encontrado el trabajador", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el trabajador -> " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin("http://127.0.0.1:5500")
    @DeleteMapping("/eliminarViaje")
    public ResponseEntity<String> eliminarViaje(@RequestBody String jsonData) {
        try {
            JSONObject data = new JSONObject(jsonData);
            Viaje t = viajeRepository.findById(data.getInt("id"));
            if (t != null) {
                viajeRepository.delete(t);
                return new ResponseEntity<>("El viaje ha sido eliminado correctamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No se ha encontrado el viaje", HttpStatus.NOT_FOUND);
            }
           
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el viaje -> " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    


    @GetMapping("/buscarCliente")
        public ResponseEntity<String> getCliente(@RequestBody String jsonData) {
        try {
            JSONObject data = new JSONObject(jsonData);
            Cliente c = clienteRepository.findByDni(data.getString("dni"));
            if (c != null) {
            return new ResponseEntity<>( HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No se ha encontrado al cliente", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("No se ha encontrado al cliente " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin("http://127.0.0.1:5500")
    @DeleteMapping("/eliminarCliente")
    public ResponseEntity<String> eliminarCliente(@RequestBody String jsonData) {
        try {
            JSONObject data = new JSONObject(jsonData);
            Cliente c = clienteRepository.findByDni(data.getString("dni"));
            if (c != null) {
                clienteRepository.delete(c);
                return new ResponseEntity<>("Cliente ha sido eliminado correctamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No se ha encontrado al cliente", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el cliente -> " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin("http://127.0.0.1:5500")
    @PostMapping("/crearCliente")
    public ResponseEntity<String>  crearCliente(@RequestBody String jsonData) {
    
        try {
            JSONObject data = new JSONObject(jsonData);
            Cliente t = new Cliente();
            t.setDni(data.getString("dni"));
            t.setNombre(data.getString("nombre"));   
            t.setGmail(data.getString("gmail"));
            t.setTelefono(data.getString("telefono"));
            t.setResidencia(data.getString("residencia"));
        

            clienteRepository.save(t);
            return new ResponseEntity<>("cliente  ha sido guardado correctamente", HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error al crear el cliente -> %s", e.getMessage()));
        }
    }
    
    @CrossOrigin("http://127.0.0.1:5500")
    @GetMapping("/buscarReserva")
    public ResponseEntity<String> getReserva(@RequestBody String jsonData) {
        try {
            JSONObject data = new JSONObject(jsonData);
            Reserva reserva =reservaRepository.findById(data.getInt("id"));
            return new ResponseEntity<>( HttpStatus.OK);
            
         } catch (Exception e) {
            return new ResponseEntity<>("Error al buscar la reserva -> " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin("http://127.0.0.1:5500")
    @PostMapping("/crearReserva")
    public ResponseEntity<String> crearReserva(@RequestBody String jsonData) {
        try {
            JSONObject data = new JSONObject(jsonData);
            Reserva r = new Reserva();
            r.setId(data.getInt("id"));
            r.setCliente(clienteRepository.findByDni(data.getString("dniCliente")));   
            r.setViaje(viajeRepository.findById(data.getInt("idViaje")));
            r.setNumPlazas(data.getInt("numPlazas"));
            reservaRepository.save(r);
            return new ResponseEntity<>( HttpStatus.OK);
            
         } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la reserva -> " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin("http://127.0.0.1:5500")
    @DeleteMapping("/eliminarReserva")
    public ResponseEntity<String> eliminarReserva(@RequestBody String jsonData) {
        try {
            JSONObject data = new JSONObject(jsonData);
            Reserva r = reservaRepository.findById(data.getInt("id"));
            if (r != null) {
                reservaRepository.delete(r);
                return new ResponseEntity<>("Reserva ha sido eliminado correctamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No se ha encontrado la reserva", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el reserva -> " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin("http://127.0.0.1:5500")
    @PostMapping("/crearViaje")
    public ResponseEntity<String> crearViaje(@RequestBody String jsonData) {
        try {
            JSONObject data = new JSONObject(jsonData);
            Viaje v = new Viaje();
            v.setId(data.getInt("id"));
            v.setOrigen(data.getString("origen"));
            v.setDestino(data.getString("destino"));
            v.setFecha(data.getString("fecha"));
            v.setDuracion(data.getInt("duracion"));
            v.setPrecio(data.getDouble("precio"));
            v.setOferta(data.getInt("oferta"));
            v.setEmpresa(data.getString("empresa"));
            v.setAsientosTotales(data.getInt("asientosTotales"));
            v.setAsientosDisponibles(data.getInt("asientosDisponibles"));
            viajeRepository.save(v);
            return new ResponseEntity<>( HttpStatus.OK);
            
         } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el viaje -> " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin("http://127.0.0.1:5500")
    @PostMapping("/editarViaje")
    public ResponseEntity<String> editarViaje(@RequestBody String jsonData) {
        try {
            JSONObject data = new JSONObject(jsonData);
            Viaje v = new Viaje();
            v.setId(data.getInt("id"));
            v.setOrigen(data.getString("origen"));
            v.setDestino(data.getString("destino"));
            v.setFecha(data.getString("fecha"));
            v.setDuracion(data.getInt("duracion"));
            v.setPrecio(data.getDouble("precio"));
            v.setOferta(data.getInt("oferta"));
            v.setEmpresa(data.getString("empresa"));
            v.setAsientosTotales(data.getInt("asientosTotales"));
            v.setAsientosDisponibles(data.getInt("asientosDisponibles"));
            viajeRepository.save(v);
            return new ResponseEntity<>( HttpStatus.OK);
            
         } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el viaje -> " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /*EJEMPLO VALDI
     * 
     * @PostMapping("/get-student-credentials")
        public ResponseEntity<String> getStudentCredentials(@RequestBody String jsonData) {
        try {
            JSONObject data = new JSONObject(jsonData);
            Student student = studentRepository.findByDni(data.getString("dni"));

            return new ResponseEntity<>(student.getCredentialsAsJson(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al obtener las credenciales del estudiante: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     * 
     */
}
