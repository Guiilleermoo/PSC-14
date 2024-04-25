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

import es.dao.ClienteRepository;
import es.dao.FavoritoRepository;
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
    FavoritoRepository favoritoRepository;
    public SqliteController(FavoritoRepository favoritoRepository,ClienteRepository clienteRepository, ReservaRepository reservaRepository, TrabajadorRepository trabajadorRepository, ViajeRepository viajeRepository) {
        this.clienteRepository = clienteRepository;
        this.reservaRepository = reservaRepository;
        this.trabajadorRepository = trabajadorRepository;
        this.viajeRepository = viajeRepository; 
        this.favoritoRepository = favoritoRepository;
    }

    // FUNCIONES TRABAJADOR
    @CrossOrigin("http://127.0.0.1:5500")
    @GetMapping("/buscarTrabajador/{gmail}/{password}")
        public ResponseEntity<Trabajador> getTrabajador(@PathVariable String gmail, @PathVariable String password) {
        Trabajador t = trabajadorRepository.findByGmail(gmail);
        
        try {
            if (t != null && t.getPassword().equals(password) ) {
                return new ResponseEntity<>(t, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }

    @CrossOrigin("http://127.0.0.1:5500")
    @GetMapping("/buscarTrabajador")
        public ResponseEntity<Trabajador> getTrabajador(@RequestBody String jsonData) {
        try {
            JSONObject data = new JSONObject(jsonData);
            Trabajador t = trabajadorRepository.findByDni(data.getString("dni"));

            if(t != null) {
                if(t.getPassword() == data.getString("password") && t.getGmail() == data.getString("gmail")) {
                    return new ResponseEntity<>(t, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
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
            return new ResponseEntity<>("trabajador ha sido guardado correctamente", HttpStatus.OK);
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


    // FUNCIONES CLIENTE
    @CrossOrigin("http://127.0.0.1:5500")
    @GetMapping("/buscarCliente/{gmail}/{password}")
        public ResponseEntity<Cliente> getCliente(@PathVariable String gmail, @PathVariable String password) {
        
        
        try {
            Cliente c = clienteRepository.findByGmail(gmail);
            if (c != null && c.getPassword().equals(password) ) {
                return new ResponseEntity<>(c, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }

    @CrossOrigin("http://127.0.0.1:5500")
    @GetMapping("/buscarCliente/{gmail}")
        public ResponseEntity<Cliente> getCliente(@PathVariable String gmail) {
        Cliente c = clienteRepository.findByGmail(gmail);
        
        try {
            if (c != null && c.getGmail().equals(gmail) ) {
                return new ResponseEntity<>(c, HttpStatus.OK);
            }else {
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
            t.setPassword(data.getString("contrasena"));
        
            clienteRepository.save(t);
            return new ResponseEntity<>("cliente ha sido guardado correctamente", HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error al crear el cliente -> %s", e.getMessage()));
        }
    }


    // FUNCIONES VIAJE
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
            try {
                Viaje v = viajeRepository.findById(id).orElse(null);
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
    public ResponseEntity<List<Viaje>> getViajesTodos() {
        try {
            List<Viaje> viajes = viajeRepository.findAll();
            return new ResponseEntity<>(viajes, HttpStatus.OK);
            
         } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // FUNCIONES RESERVA
    @CrossOrigin("http://127.0.0.1:5500")
    @GetMapping("/buscarReserva/{dni}")
    public ResponseEntity<Viaje> getReserva(@PathVariable String dni) {
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
    @GetMapping("/viajesCliente/{dni}")
    public ResponseEntity<List<Reserva>> getViajesCliente(@PathVariable String dni) {
        try {
            List<Reserva> reservaCliente = reservaRepository.findByCliente(clienteRepository.findByDni(dni));
            return new ResponseEntity<>(reservaCliente, HttpStatus.OK);
            
         } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // FUNCIONES FAVORITO
    @CrossOrigin("http://127.0.0.1:5500")
    @GetMapping("/buscarFavorito/{dni}")
        public ResponseEntity<List<Favorito>> getFavoritoDni(@PathVariable String dni) {
        try {
            List<Favorito> favorito = favoritoRepository.findByClienteDni(dni);

            if(favorito != null) {
                return new ResponseEntity<>(favorito, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @CrossOrigin("http://127.0.0.1:5500")
    @PostMapping("/anadirFavorito/{dni}/{id}")
        public ResponseEntity<String> anadirFavorito(@PathVariable String dni, @PathVariable Integer id) {
        try {
            Favorito f = new Favorito();
            Cliente c = clienteRepository.findByDni(dni);
            Viaje v = viajeRepository.findById(id).orElse(null);
            if ( c != null) {
                f.setCliente(c);
                f.setViaje(v);
                int randomNum = 0;
                for(int i = 0; i< 10; i++){
                randomNum = (int)(Math.random() * 1000 + 1);
            }
            f.setId(randomNum);

            favoritoRepository.save(f);

            return new ResponseEntity<>("Favorito añadido correctamente", HttpStatus.OK); 
            }else{
                return new ResponseEntity<>("No eres cliente debes de registrarte", HttpStatus.NOT_FOUND); 
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }

    @CrossOrigin("http://127.0.0.1:5500")
    @DeleteMapping("/eliminarFavorito")
    public ResponseEntity<String> eliminarFavorito(@RequestBody String jsonData) {
        try {
            JSONObject data = new JSONObject(jsonData);
            Favorito f = favoritoRepository.findById(data.getInt("id"));
            if (f != null) {
                favoritoRepository.delete(f);
                return new ResponseEntity<>("Reserva ha sido eliminado correctamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No se ha encontrado la reserva", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el reserva -> " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin("http://127.0.0.1:5500")
    @GetMapping("/buscarDNI/{gmail}")
        public ResponseEntity<String> getDNIporGmail(@PathVariable String gmail) {
        try {
            String dni = clienteRepository.findByGmail(gmail).getDni();

            if(dni != null) {
                return new ResponseEntity<>(dni, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
