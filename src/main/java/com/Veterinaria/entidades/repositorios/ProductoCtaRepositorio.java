/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Veterinaria.entidades.repositorios;

import com.Veterinaria.entidades.Cliente;
import com.Veterinaria.entidades.Producto;
import com.Veterinaria.entidades.ProductoCta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author German
 */

@Repository
public interface ProductoCtaRepositorio extends JpaRepository<ProductoCta, String>{
    
    @Query("SELECT p FROM ProductoCta p WHERE p.cliente = :cliente AND p.cancelado = false")
    public List<ProductoCta> listarProductosNoCancelados(@Param("cliente") Cliente cliente);
    
    @Query("SELECT p FROM ProductoCta p WHERE p.producto = :producto AND p.cancelado = false")
    public List<ProductoCta> listarProductosCtaPorProducto(@Param("producto") Producto producto);
}
