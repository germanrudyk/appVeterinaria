/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Veterinaria.servicios;

import com.Veterinaria.entidades.Cliente;
import com.Veterinaria.entidades.CtaCte;
import com.Veterinaria.entidades.Producto;
import com.Veterinaria.entidades.ProductoCta;
import com.Veterinaria.entidades.repositorios.ProductoCtaRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author German
 */
@Service
public class ProductoCtaServicio {

    @Autowired
    private ProductoCtaRepositorio productoCtaRepositorio;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private ClienteServicio clienteServicio;

    public List<ProductoCta> listarProductos(CtaCte ctaCte) {

        return ctaCte.getProductos();

    }

    public Boolean productoEnCtaCte(List<ProductoCta> productos, Producto producto) {

        for (ProductoCta aux : productos) {
            if (aux.getProducto() == producto) {
                return true;
            }
        }

        return false;

    }

    public ProductoCta agregarProductoCta(Producto producto, Cliente cliente, Integer cantidad) {

        ProductoCta productoCta = new ProductoCta();

        productoCta.setProducto(producto);

        productoCta.setCliente(cliente);

        productoCta.setCantidad(cantidad);

        productoCta.setPrecioUnitario(producto.getPrecio());

        productoCta.setPrecioTotal(productoCta.getPrecioUnitario() * cantidad);

        productoCta.setMontoACancelar(productoCta.getPrecioTotal());

        productoCta.setCancelado(Boolean.FALSE);

        producto.setStock(producto.getStock() - cantidad);

        productoCtaRepositorio.save(productoCta);

        productoServicio.save(producto);

        return productoCta;

    }

    public ProductoCta agregarCantidad(List<ProductoCta> productos, Producto producto, Integer cantidad) {

        for (ProductoCta aux : productos) {

            if (aux.getProducto() == producto) {

                aux.setCantidad(aux.getCantidad() + cantidad);

                aux.setPrecioUnitario(producto.getPrecio());

                aux.setPrecioTotal(aux.getPrecioTotal() + (aux.getPrecioUnitario() * cantidad));

                aux.setMontoACancelar(aux.getMontoACancelar() + (aux.getPrecioUnitario() * cantidad));

                producto.setStock(producto.getStock() - cantidad);

                productoCtaRepositorio.save(aux);

                productoServicio.save(producto);

            }

        }

        return null;

    }

    public ProductoCta buscarProductoCtaPorId(String id) {

        Optional<ProductoCta> respuesta = productoCtaRepositorio.findById(id);

        if (respuesta.isPresent()) {

            ProductoCta productoCta = respuesta.get();

            return productoCta;

        }

        return null;

    }

    public List<ProductoCta> listarProductosNoCancelados(Cliente cliente) {

        return productoCtaRepositorio.listarProductosNoCancelados(cliente);

    }

    public List<ProductoCta> listarProductosCtaPorProducto(Producto producto) {

        return productoCtaRepositorio.listarProductosCtaPorProducto(producto);

    }

    public void actualizarPrecios(Producto producto, Double porcentaje) {

        List<ProductoCta> productos = listarProductosCtaPorProducto(producto);

        for (ProductoCta aux : productos) {

            aux.setPrecioUnitario(producto.getPrecio());

            aux.setPrecioTotal(aux.getPrecioUnitario() * aux.getCantidad());

            // Agregar porcentaje al monto adeudado
            Double porcentajeAplicado = aux.getMontoACancelar() + (aux.getMontoACancelar() * porcentaje / 100);

            // Redondear a centena
            Integer precioFinal = (int) (Math.ceil(porcentajeAplicado / 100)) * 100;

            aux.setMontoACancelar(precioFinal); 

            productoCtaRepositorio.save(aux);

        }

    }

    public void cancelarProductoCta(String id, String pago, Integer entregado) {

        ProductoCta productoCta = buscarProductoCtaPorId(id);

        if (productoCta != null) {

            if ("total".equals(pago)) {

                productoCta.setMontoACancelar(0);

                productoCta.setCancelado(Boolean.TRUE);

            } else {

                productoCta.setMontoACancelar(productoCta.getMontoACancelar() - entregado);

                if (productoCta.getMontoACancelar() == 0) {

                    productoCta.setCancelado(Boolean.TRUE);

                }

            }

            productoCtaRepositorio.save(productoCta);

        }

    }

    public void cancelarTodo(String id) {

        Cliente cliente = clienteServicio.buscarClientePorId(id);

        List<ProductoCta> productos = cliente.getCtaCte().getProductos();

        System.out.println(productos);

        for (ProductoCta producto : productos) {

            producto.setCancelado(Boolean.TRUE);

            productoCtaRepositorio.save(producto);

        }

    }

}
