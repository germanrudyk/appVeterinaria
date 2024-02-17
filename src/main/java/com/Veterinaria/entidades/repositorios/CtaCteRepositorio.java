/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Veterinaria.entidades.repositorios;

import com.Veterinaria.entidades.CtaCte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author German
 */

@Repository
public interface CtaCteRepositorio extends JpaRepository<CtaCte, String>{
    
}
