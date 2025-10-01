package com.btgpactual.gestionfondos.dto;

import com.btgpactual.gestionfondos.enums.TipoTransaccion;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TransaccionDto {
    private String id;
    private TipoTransaccion tipo;
    private Long idFondo;
    private String nombreFondo;
    private BigDecimal monto;
    private LocalDateTime fecha;
}