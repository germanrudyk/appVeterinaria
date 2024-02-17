/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Veterinaria.servicios;

import com.Veterinaria.entidades.Cliente;
import com.Veterinaria.entidades.CtaCte;
import com.Veterinaria.entidades.Producto;
import com.Veterinaria.entidades.ProductoCta;
import com.Veterinaria.entidades.repositorios.CtaCteRepositorio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author German
 */
@Service
public class CtaCteServicio {

    @Autowired
    private CtaCteRepositorio ctaCteRepositorio;

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private ProductoCtaServicio productoCtaServicio;

    @Autowired
    private ProductoServicio productoServicio;

    public CtaCte agregarCtaCte() {

        CtaCte ctaCte = new CtaCte();

        ctaCteRepositorio.save(ctaCte);

        return ctaCte;

    }

    public void asignarCtaCteACliente(CtaCte ctaCte, Cliente cliente) {

        ctaCte.setCliente(cliente);

        ctaCteRepositorio.save(ctaCte);

    }

    public void agregarProductos(String idCliente, String[] idProducto, Integer[] cantidad) {

        Cliente cliente = clienteServicio.buscarClientePorId(idCliente);

        if (cliente != null) {

            // Si el cliente tiene productos en su cuenta            
            if (cliente.getCtaCte().getProductos() != null) {

                List<ProductoCta> productosClientes = productoCtaServicio.listarProductosNoCancelados(cliente);

                for (int i = 0; i < idProducto.length; i++) { // Agregar productos a la cuenta corriente

                    Producto producto = productoServicio.buscarProductoPorId(idProducto[i]);

                    // Verificar si existe una venta anterior de ese producto
                    if (productoCtaServicio.productoEnCtaCte(productosClientes, producto)) {

                        productoCtaServicio.agregarCantidad(productosClientes, producto, cantidad[i]);

                    } else {

                        ProductoCta productoAAgregar = productoCtaServicio.agregarProductoCta(producto, cliente, cantidad[i]);

                        productosClientes.add(productoAAgregar);

                    }

                }

                cliente.getCtaCte().setProductos(productosClientes);

                ctaCteRepositorio.save(cliente.getCtaCte());

            } else {

                // Si no tiene productos
                List<ProductoCta> nuevosProductos = new ArrayList();

                for (int i = 0; i < idProducto.length; i++) {

                    Producto producto = productoServicio.buscarProductoPorId(idProducto[i]);

                    ProductoCta productoAAgregar = productoCtaServicio.agregarProductoCta(producto, cliente, cantidad[i]);

                    nuevosProductos.add(productoAAgregar);

                }

                cliente.getCtaCte().setProductos(nuevosProductos);

                ctaCteRepositorio.save(cliente.getCtaCte());

            }

        }

    }

}
