package com.btgpactual.gestionfondos.entity;

import com.btgpactual.gestionfondos.enums.TipoTransaccion;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoTransaccion tipo;

    @Column(nullable = false)
    private Long idFondo;

    @Column(nullable = false)
    private String idCliente;

    @Column(nullable = false)
    private BigDecimal monto;

    @Column(nullable = false)
    private LocalDateTime fecha;

    public Transaccion(TipoTransaccion tipo, Long idFondo, String idCliente, BigDecimal monto) {
        this.tipo = tipo;
        this.idFondo = idFondo;
        this.idCliente = idCliente;
        this.monto = monto;
        this.fecha = LocalDateTime.now();
    }
}