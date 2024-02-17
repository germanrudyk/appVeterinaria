/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Veterinaria.controladores;

import com.Veterinaria.entidades.Producto;
import com.Veterinaria.servicios.ProductoServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/productos")
public class ProductoControlador {

    @Autowired
    private ProductoServicio productoServicio;

    @PostMapping("/agregar")
    public String agregar(String nombre, Integer stock, Integer precio) {

        productoServicio.crear(nombre, stock, precio);

        return "redirect:../";

    }   
    
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, @RequestParam String nombre,
            @RequestParam Integer stock, Double porcentaje) {

        productoServicio.modificar(id, nombre, stock, porcentaje);

        return "redirect:../../";

    }

}
