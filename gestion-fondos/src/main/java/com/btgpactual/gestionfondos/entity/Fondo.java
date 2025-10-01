package com.btgpactual.gestionfondos.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Data
public class Fondo {

    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false)
    private BigDecimal montoMinimo;

    @Column(nullable = false)
    private String categoria;
}