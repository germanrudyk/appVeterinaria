/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Veterinaria.entidades.repositorios;

import com.Veterinaria.entidades.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author German
 */

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, String>{
    
    @Query("SELECT p FROM Producto p WHERE p.stock > 0")
    public List<Producto> listarProductosEnStock();
}
