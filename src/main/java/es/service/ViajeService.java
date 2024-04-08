package es.service;

import es.dao.*;
import es.model.*;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class ViajeService {

    private ViajeRepository viajeRepository;

    public void crearViaje(Viaje viaje) {
        try {
            viajeRepository.save(viaje);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error al crear el viaje -> %s", e.getMessage()));
        }
    }

    public void borrarViaje(Integer id) {
        try {
            viajeRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error al borrar el viaje -> %s", e.getMessage()));
        }
    }
    
    public void modificarViaje(Viaje viaje) {
        try {
            Viaje viajeDB = viajeRepository.findById(viaje.getId());
            if(viajeDB != null) {
                viajeRepository.save(viaje);
            } else {
                throw new RuntimeException("Error al buscar el viaje");
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error al modificar el viaje -> %s", e.getMessage()));
        }
    }

    public List<Viaje> listarViajes() {
        List<Viaje> viajes = null;

        try {
            viajes = viajeRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error al listar los viajes -> %s", e.getMessage()));
        }
        
        return viajes;
    }

    public Viaje buscarPorId(Integer id) {
        try {
            Viaje viaje = viajeRepository.findById(id).orElse(null);
            if(viaje != null) {
                return viaje;
            } else {
                throw new RuntimeException("No se encontró el viaje");
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error al buscar el viaje -> %s", e.getMessage()));
        }
    }

    public List<Viaje> buscarPorDestino(String destino) {
        List<Viaje> viajes = listarViajes();
        List<Viaje> viajesDestino = new ArrayList<>();

        for (Viaje viaje : viajes) {
            if (viaje.getDestino().equals(destino)) {
                viajesDestino.add(viaje);
            }
        }

        return viajesDestino;
    }
    
    public List<Viaje> buscarPorFecha(LocalDateTime fecha) {
        List<Viaje> viajes = listarViajes();
        List<Viaje> resultado = new ArrayList<>();

        for (Viaje viaje : viajes) {
            if (viaje.getFecha().equals(fecha)) {
                resultado.add(viaje);
            }
        }

        return resultado;
    }
    
    public List<Viaje> buscarPorPrecio(double precio) {
        List<Viaje> viajes = listarViajes();
        List<Viaje> resultado = new ArrayList<>();

        for (Viaje viaje : viajes) {
            if (viaje.getPrecio().equals(precio)) {
                resultado.add(viaje);
            }
        }

        return resultado;
    }

    public List<Viaje> ordenarPorFecha() {
        List<Viaje> viajes = listarViajes();
        viajes.sort((v1, v2) -> v1.getFecha().compareTo(v2.getFecha()));
        return viajes;
    }  

    public List<Viaje> ordenarPorPrecio() {
        List<Viaje> viajes = listarViajes();
        viajes.sort((v1, v2) -> v1.getPrecio().compareTo(v2.getPrecio()));
        return viajes;
    }
}
