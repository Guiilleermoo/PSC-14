package es.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/buscarTrabajador/{dni}")
    public ResponseEntity<String> getTrabajador(@PathVariable String dni) {
        Trabajador trabajador = trabajadorRepository.findByDni(dni);

        if (trabajador == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin("http://127.0.0.1:5500")
    @GetMapping("/crearTrabajador/")
    public void crearTrabajador(Trabajador trabajador) {
        //Hay que hacer
        
        try {
            trabajadorRepository.save(trabajador);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error al crear el trabajador -> %s", e.getMessage()));
        }
    }

    @CrossOrigin("http://127.0.0.1:5500")
    @GetMapping("/eliminarTrabajador/{dni}")
    public ResponseEntity<String> eliminarTrabajador(@PathVariable String dni) {
        Trabajador trabajador = trabajadorRepository.findByDni(dni);
        

        if (trabajador == null) {
            
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            trabajadorRepository.deleteByDni(dni);
            trabajador = trabajadorRepository.findByDni(dni);
            if (trabajador == null) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
            }
        }
        
    }

    @CrossOrigin("http://127.0.0.1:5500")
    @GetMapping("/eliminarViaje/{ID}")
    public ResponseEntity<String> eliminarViaje(@PathVariable int id) {
        Viaje viaje = viajeRepository.findById(id).orElse(null);
        

        if (viaje == null) {
            
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            viajeRepository.deleteById(id);
            viaje = viajeRepository.findById(id).orElse(null);
            if (viaje == null) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
            }
        }
        
    }


    @CrossOrigin("http://127.0.0.1:5500")
    @GetMapping("/buscarCliente/{dni}")
    public ResponseEntity<String> getCliente(@PathVariable String dni) {
        Cliente cliente = clienteRepository.findByDni(dni);

        if (cliente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin("http://127.0.0.1:5500")
    @GetMapping("/eliminarCliente/{dni}")
    public ResponseEntity<String> eliminarCliente(@PathVariable String dni) {
        Cliente cliente = clienteRepository.findByDni(dni);
        

        if (cliente == null) {
            
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            clienteRepository.deleteByDni(dni);
            cliente = clienteRepository.findByDni(dni);
            if (cliente == null) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
            }
        }
        
    }

    @CrossOrigin("http://127.0.0.1:5500")
    @GetMapping("/buscarReserva/{dni}")
    public ResponseEntity<String> getReserva(@PathVariable int id) {
        Reserva reserva = reservaRepository.findById(id).orElse(null);

        if (reserva == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin("http://127.0.0.1:5500")
    @GetMapping("/eliminarReserva/{id}")
    public ResponseEntity<String> eliminarReserva(@PathVariable String id) {
        Reserva reserva = reservaRepository.deleteById(id);
        

        if (reserva == null) {
            
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            reservaRepository.deleteById(id);
            reserva = reservaRepository.findById(id).orElse(null);
            if (reserva == null) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
            }
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
