package es.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    @GetMapping("/buscarTrabajadorLogin")
        public ResponseEntity<String> getTrabajadorLogin(@RequestBody String jsonData) {
        try {
            JSONObject data = new JSONObject(jsonData);
            Trabajador t = trabajadorRepository.findByDni(data.getString("dni"));

            if(t != null) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // OPCION 1
    @CrossOrigin("http://127.0.0.1:5500")
    @GetMapping("/buscarTrabajador")
        public ResponseEntity<String> getTrabajador(@RequestBody String jsonData) {
        try {
            JSONObject data = new JSONObject(jsonData);
            Trabajador t = trabajadorRepository.findByDni(data.getString("dni"));
            ObjectMapper om = new ObjectMapper();
            String trabajadorjson = om.writeValueAsString(t);

            return new ResponseEntity<>(trabajadorjson, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // OPCION 2
    @CrossOrigin("http://127.0.0.1:5500")
    @GetMapping("/buscarTrabajador2")
        public ResponseEntity<Trabajador> getTrabajador2(@RequestBody String jsonData) {
        try {
            JSONObject data = new JSONObject(jsonData);
            Trabajador t = trabajadorRepository.findByDni(data.getString("dni"));

            return new ResponseEntity<>(t, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
   
    @CrossOrigin("http://127.0.0.1:5500")
    @GetMapping("/buscarCliente")
        public ResponseEntity<Cliente> getCliente2(@RequestBody String jsonData) {
        try {
            JSONObject data = new JSONObject(jsonData);
            Cliente c = clienteRepository.findByDni(data.getString("dni"));

            if (c != null) {
            return new ResponseEntity<>(c, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
    @GetMapping("/buscarReserva/{dni}")
    public ResponseEntity<Viaje> getReserva2(@PathVariable String dni) {
        try {
           //Reserva reserva =reservaRepository.findByDni(dni);
            return new ResponseEntity<>(HttpStatus.OK);
            
         } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin("http://127.0.0.1:5500")
    @PostMapping("/crearReserva/{idViaje}")
    public ResponseEntity<String> crearReserva(@PathVariable Integer idViaje, @RequestBody String jsonData) {
        try {
            JSONObject data = new JSONObject(jsonData);
            Reserva r = new Reserva();
            Viaje v = viajeRepository.findById(idViaje).orElse(null);
            Cliente c = clienteRepository.findByDni(data.getString("dniCliente"));
            if ( c == null) {
                return new ResponseEntity<>("No eres cliente debes de registrarte", HttpStatus.NOT_FOUND);
            }else{
                r.setCliente(c);  
            }
            
            v.setAsientosDisponibles(v.getAsientosDisponibles()-Integer.parseInt(data.getString("numPlazas"))); 
            r.setViaje(v);
            int randomNum = 0;
            for(int i = 0; i< 10; i++){
                randomNum = (int)(Math.random() * 1000 + 1);
            }
            r.setId(randomNum);
            r.setNumPlazas(Integer.parseInt(data.getString("numPlazas")));
            reservaRepository.save(r);
            return new ResponseEntity<>("Tu reserva se ha registrado correctamente", HttpStatus.OK);
            
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
            return new ResponseEntity<>( "Viaje creado correctamente", HttpStatus.OK);
            
         } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el viaje -> " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin("http://127.0.0.1:5500")
    @GetMapping("/buscarViaje/{id}")
        public ResponseEntity<Viaje> getViaje(@PathVariable Integer id) {
            Viaje v = viajeRepository.findById(id).orElse(null);
            try {
                if (v != null) {
                return new ResponseEntity<>(v, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @CrossOrigin("http://127.0.0.1:5500")
    @PutMapping("/editarViaje/{id}")
    public ResponseEntity<Viaje> editarViaje(@PathVariable Integer id, @RequestBody String jsonData) {
        try {
            JSONObject data = new JSONObject(jsonData);
            Viaje v = viajeRepository.findById(id).orElse(null);
            v.setId(id);
            v.setOrigen(data.getString("origen"));
            v.setDestino(data.getString("destino"));
            v.setFecha(data.getString("fecha"));
            v.setDuracion(Integer.parseInt(data.getString("duracion")));
            v.setPrecio(Double.parseDouble(data.getString("precio")));
            v.setOferta(Integer.parseInt(data.getString("oferta")));
            v.setEmpresa(data.getString("empresa"));
            v.setAsientosTotales(Integer.parseInt(data.getString("asientosTotales")));
            v.setAsientosDisponibles(Integer.parseInt(data.getString("asientosDisponibles")));
            viajeRepository.save(v);

            return new ResponseEntity<>(v, HttpStatus.OK);
            
         } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin("http://127.0.0.1:5500")
    @GetMapping("/viajes")
    public ResponseEntity<List<Viaje>> getViajes2() {
        try {
            List<Viaje> viajes = viajeRepository.findAll();
            return new ResponseEntity<>(viajes, HttpStatus.OK);
            
         } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @CrossOrigin("http://127.0.0.1:5500")
    @GetMapping("/viajesCliente")
    public ResponseEntity<List<Reserva>> getViajesCliente2(@RequestBody String jsonData) {
        try {
            JSONObject data = new JSONObject(jsonData);

            List<Reserva> reservas = reservaRepository.findByCliente(clienteRepository.findByDni(data.getString("dni")));
            return new ResponseEntity<>(reservas, HttpStatus.OK);
            
         } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
