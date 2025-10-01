package com.btgpactual.gestionfondos.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Cliente {

    @Id
    private String id;

    @Column(nullable = false)
    private BigDecimal saldo;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "cliente_fondos", joinColumns = @JoinColumn(name = "cliente_id"))
    @Column(name = "fondo_id")
    private Set<Long> fondosSuscritos = new HashSet<>();

    public Cliente(BigDecimal saldo) {
        this.saldo = saldo;
    }
    private String email;
}