/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Veterinaria.servicios;

import com.Veterinaria.entidades.Cliente;
import com.Veterinaria.entidades.CtaCte;
import com.Veterinaria.entidades.repositorios.ClienteRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author German
 */
@Service
public class ClienteServicio {

    @Autowired
    private ClienteRepositorio clienteRepositorio;
    
    @Autowired
    private CtaCteServicio ctaCteServicio;

    public void agregar(String nombre, String telefono) {

        Cliente cliente = new Cliente();

        cliente.setNombre(nombre);
        cliente.setTelefono(telefono);

        CtaCte ctaCte = ctaCteServicio.agregarCtaCte();
        
        cliente.setCtaCte(ctaCte);

        clienteRepositorio.save(cliente);
        
        ctaCteServicio.asignarCtaCteACliente(ctaCte, cliente);

    }

    public Cliente buscarClientePorId(String id) {

        Optional<Cliente> respuesta = clienteRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Cliente cliente = respuesta.get();

            return cliente;

        }

        return null;

    }

    public void modificar(String id, String nombre, String telefono) {

        Cliente cliente = buscarClientePorId(id);

        if (cliente != null) {

            cliente.setNombre(nombre);
            cliente.setTelefono(telefono);

            clienteRepositorio.save(cliente);
        } 

    }

    public void eliminar(String id) {

        Cliente cliente = buscarClientePorId(id);
        
        clienteRepositorio.delete(cliente);

    }

    public List<Cliente> listarClientes(){
        
        return clienteRepositorio.findAll();
        
    }
    
}
