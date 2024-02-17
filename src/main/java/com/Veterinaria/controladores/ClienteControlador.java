/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Veterinaria.controladores;

import com.Veterinaria.entidades.Cliente;
import com.Veterinaria.entidades.Producto;
import com.Veterinaria.entidades.ProductoCta;
import com.Veterinaria.servicios.ClienteServicio;
import com.Veterinaria.servicios.CtaCteServicio;
import com.Veterinaria.servicios.ProductoCtaServicio;
import com.Veterinaria.servicios.ProductoServicio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author German
 */

@Controller
@RequestMapping("/clientes")
public class ClienteControlador {
    
    @Autowired
    private ClienteServicio clienteServicio;
    
    @Autowired
    private ProductoServicio productoServicio;
    
    @Autowired
    private ProductoCtaServicio productoCtaServicio;
    
    @Autowired
    private CtaCteServicio ctaCteServicio;
    
    @GetMapping("")
    public String clientes(ModelMap modelo){
        
        if (clienteServicio.listarClientes() != null){
            
            List<Cliente> clientes = clienteServicio.listarClientes();
            
            modelo.put("clientes", clientes);
            
        }
        
        return "clientes";
    }
    
    @PostMapping("/agregar")
    public String agregar(@RequestParam String nombre, @RequestParam String telefono){
        
        clienteServicio.agregar(nombre, telefono);
        
        return "redirect:";
        
    }
    
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, @RequestParam String nombre,
            @RequestParam String telefono) {
        
        clienteServicio.modificar(id, nombre, telefono);
        
        return "redirect:";
        
    }
    
    @GetMapping("/ctacte/{idCliente}")
    public String ctaCte(@PathVariable String idCliente, ModelMap modelo){
        
        Cliente cliente = clienteServicio.buscarClientePorId(idCliente);
        
        List<ProductoCta> productos = productoCtaServicio.listarProductosNoCancelados(cliente);
        
        modelo.put("cliente", cliente);
        
        modelo.put("productos", productos);
        
        return "ctacte";
        
    }
    
    @GetMapping("/vender/{id}")
    public String vender(@PathVariable String id, ModelMap modelo){
        
        Cliente cliente = clienteServicio.buscarClientePorId(id);
        
        List<Producto> productos = productoServicio.listarProductosEnStock();
        
        modelo.put("cliente", cliente);
        
        modelo.put("productos", productos);
        
        return "vender";
        
    }
    
    @PostMapping("/vender/{idCliente}")
    public String vender(@PathVariable String idCliente, String[] idProducto, Integer[] cantidad,
            @RequestParam("pago") String pago){
        
        ctaCteServicio.agregarProductos(idCliente, idProducto, cantidad);
        
        return "redirect:../..";
        
    }
    
    @PostMapping("/ctacte/{idCliente}/{idProducto}")
    public String cancelarProducto(@PathVariable String idCliente, 
            @PathVariable String idProducto, @RequestParam String pago,
            @RequestParam(required = false) Integer entregado){
        
        productoCtaServicio.cancelarProductoCta(idProducto, pago, entregado);
        
        return "redirect:../../../clientes/ctacte/{idCliente}";
        
    }
    
    @PostMapping("/ctacte/{idCliente}")
    public String cancelarTodo(@PathVariable String idCliente){
        
        productoCtaServicio.cancelarTodo(idCliente);
        
        return "redirect:../../clientes/ctacte/{idCliente}";
        
    }
    
    
}
