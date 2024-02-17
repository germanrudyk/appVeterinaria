/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Veterinaria.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author German
 */

@Entity
@Table(name = "productocta")
@Getter
@Setter
@NoArgsConstructor
public class ProductoCta {
    
    @Column
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @OneToOne
    private Producto producto;
    @OneToOne
    private Cliente cliente;
    @Column
    private Integer cantidad;
    @Column
    private Integer precioUnitario;
    @Column
    private Integer precioTotal;
    @Column
    private Integer montoACancelar;
    @Column
    private Boolean cancelado;
    
}
