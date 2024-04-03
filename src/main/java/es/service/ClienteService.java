package es.service;

import es.dao.ClienteRepository;
import es.model.Cliente;

import java.util.List;
import java.util.ArrayList;

public class ClienteService {

    private ClienteRepository clienteRepository;

    public void crearCliente(Cliente cliente) {
        try {
            clienteRepository.save(cliente);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error al crear el cliente -> %s", e.getMessage()));
        }
    }

    public void borrarCliente(Cliente cliente) {
        try {
            clienteRepository.delete(cliente);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error al borrar el cliente -> %s", e.getMessage()));
        }
    }

    public void actualizarCliente(Cliente cliente) {
        try {
            clienteRepository.save(cliente);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error al actualizar el cliente -> %s", e.getMessage()));
        }
    }

    public List<Cliente> obtenerTodosLosClientes() {
        try {
            List<Cliente> clientes = clienteRepository.findAll();
            if(clientes != null) {
                return clientes;
            } else {
                throw new RuntimeException("Error al buscar las reservas");
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error al obtener las reservas -> %s", e.getMessage()));
        }
    }
    public Cliente obtenerClientePorId(String dni) {
        try {
            Cliente cliente = clienteRepository.findById(dni).orElse(null);
            if(cliente != null) {
                return cliente;
            } else {
                throw new RuntimeException("Error al buscar la reserva");
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error al obtener la reserva -> %s", e.getMessage()));
        }
    }
    public Cliente obtenerClientePorDNI(String dni) {
        List<Cliente> clientes = obtenerTodosLosClientes();
        Cliente clienteFiltrado = null;

        for(Cliente cliente : clientes) {
            if(cliente.getDni() == dni) {
                clienteFiltrado = cliente;
            }
        }

        return clienteFiltrado;
    }

    public List<Cliente> buscarClientesPorNombre(String nombre) {
        List<Cliente> clientes = obtenerTodosLosClientes();
        List<Cliente> clientesFiltrados = new ArrayList<>();

        for(Cliente cliente : clientes) {
            if(cliente.getNombre() == nombre) {
                clientesFiltrados.add(cliente);
            }
        }

        return clientesFiltrados;
    }

    public Cliente buscarClientePorCorreo(String gmail) {
        List<Cliente> clientes = obtenerTodosLosClientes();
        Cliente clienteFiltrado = null;

        for(Cliente cliente : clientes) {
            if(cliente.getGmail() == gmail) {
                clienteFiltrado = cliente;
            }
        }

        return clienteFiltrado;
    }

}
