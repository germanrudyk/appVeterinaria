/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Veterinaria.servicios;

import com.Veterinaria.entidades.Producto;
import com.Veterinaria.entidades.repositorios.ProductoRepositorio;
import com.Veterinaria.excepciones.MiException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author German
 */
@Service
public class ProductoServicio {

    @Autowired
    private ProductoRepositorio productoRepositorio;
    
    @Autowired
    private ProductoCtaServicio productoCtaServicio;

    public void crear(String nombre, Integer stock, Integer precio) {

        Producto producto = new Producto();

        producto.setNombre(nombre);
        producto.setStock(stock);
        producto.setPrecio(precio);

        productoRepositorio.save(producto);

    }

    public Producto buscarProductoPorId(String id) {

        Optional<Producto> respuesta = productoRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Producto producto = respuesta.get();

            return producto;

        }

        return null;

    }

    public void modificar(String id, String nombre, Integer stock, Double porcentaje) {

        Producto producto = buscarProductoPorId(id);

        if (producto != null) {

            if (porcentaje != null) {
                // Agregar porcentaje directo
                Double porcentajeAplicado = producto.getPrecio() + (producto.getPrecio() * porcentaje / 100);

                // Redondear a centena
                Integer precioFinal = (int) (Math.ceil(porcentajeAplicado / 100)) * 100;

                producto.setPrecio(precioFinal);
            }

            producto.setNombre(nombre);

            producto.setStock(stock);

            productoRepositorio.save(producto);
            
            productoCtaServicio.actualizarPrecios(producto, porcentaje);

        }

    }

    public List<Producto> listarProductos() {

        return productoRepositorio.findAll();

    }
    
    public List<Producto> listarProductosEnStock(){
        
        return productoRepositorio.listarProductosEnStock();
        
    }

    public void eliminar(String id) {

        Producto producto = buscarProductoPorId(id);

        if (producto != null) {

            productoRepositorio.delete(producto);

        }

    }

    public void vender(String id, Integer cantidad) throws MiException {

        Producto producto = buscarProductoPorId(id);

        if (cantidad <= producto.getStock()) {

            producto.setStock(producto.getStock() - cantidad);

            productoRepositorio.save(producto);

        } else {

            throw new MiException("No hay esa cantidad disponible");

        }

    }
    
    public void save(Producto producto){
        
        productoRepositorio.save(producto);
        
    }

}
