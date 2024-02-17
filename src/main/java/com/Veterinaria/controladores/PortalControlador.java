/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Veterinaria.controladores;

import com.Veterinaria.entidades.Producto;
import com.Veterinaria.entidades.Usuario;
import com.Veterinaria.excepciones.MiException;
import com.Veterinaria.servicios.ProductoServicio;
import com.Veterinaria.servicios.UsuarioServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
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
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;
    
    
    @PreAuthorize("hasAnyRole('ROLE_LEO', 'ROLE_EMPLEADO')")
    @GetMapping("/")
    public String index(HttpSession session, ModelMap modelo) {
        
        List<Producto> productos = productoServicio.listarProductos();
        
        modelo.put("productos", productos);     
        
        return "index";
    }  
    
    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo){      
        
        if (error != null) {
            modelo.put("error", "Usuario o Contraseña inválidos");
        }
        
        return "login";
        
    }   
    
    @PreAuthorize("hasAnyRole('ROLE_LEO', 'ROLE_EMPLEADO')")
    @PostMapping("/vender/{id}")
    public String vender(@PathVariable String id, @RequestParam Integer cantidad, ModelMap modelo){
        
        try {
            productoServicio.vender(id, cantidad);
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
        }
        
        return "redirect:/";
    }
    
    @PreAuthorize("hasAnyRole('ROLE_LEO')")
    @GetMapping("/ctacte")
    public String ctacte(){
        return "ctacte";
    }

}
