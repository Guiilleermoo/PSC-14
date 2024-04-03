package es.service;

import es.dao.TrabajadorRepository;
import es.model.Trabajador;
import java.util.List;
import java.util.ArrayList;

public class TrabajadorService {

    private TrabajadorRepository trabajadorRepository;

    public void crearTrabajador(Trabajador trabajador) {
        try {
            trabajadorRepository.save(trabajador);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error al crear el trabajador -> %s", e.getMessage()));
        }
    }
    public void eliminarTrabajador(String dni) {
        try {
            trabajadorRepository.deleteById(dni);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error al borrar el trabajador -> %s", e.getMessage()));
        }
    }

    public void actualizarTrabajador(Trabajador trabajador) {
        try {
            Trabajador trabajadorDB = trabajadorRepository.findByDni(trabajador.getDNI());

            if(trabajadorDB != null) {
                trabajadorRepository.save(trabajador);
            } else {
                throw new RuntimeException("Error al buscar el trabajador");
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error al actualizar el trabajador -> %s", e.getMessage()));
        }
    }
    public List<Trabajador> obtenerTodosLosTrabajadores() {
        try {
            List<Trabajador> trabajadores = trabajadorRepository.findAll();
            if(trabajadores != null) {
                return trabajadores;
            } else {
                throw new RuntimeException("Error al buscar los trabajadores");
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error al obtener los trabajadores -> %s", e.getMessage()));
        }
    }

    public Trabajador obtenerTrabajadorPorId(String dni) {
        try {
            Trabajador trabajador = trabajadorRepository.findById(dni).orElse(null);
            if(trabajador != null) {
                return trabajador;
            } else {
                throw new RuntimeException("Error al buscar el trabajador");
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error al obtener el trabajador -> %s", e.getMessage()));
        }
    }
    public List<Trabajador> buscarTrabajadoresPorNombre(String nombre) {
        try {
            List<Trabajador> trabajadores = obtenerTodosLosTrabajadores();
            List<Trabajador> trabajadoresFiltrados = new ArrayList<>();

            for(Trabajador trabajador : trabajadores) {
                if(trabajador.getNombre().equals(nombre)) {
                    trabajadoresFiltrados.add(trabajador);
                }
            }
            return trabajadoresFiltrados;
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error al buscar los trabajadores -> %s", e.getMessage()));
        }
    }

}
