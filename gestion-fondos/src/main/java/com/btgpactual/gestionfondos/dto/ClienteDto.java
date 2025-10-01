package com.btgpactual.gestionfondos.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
public class ClienteDto {
    private String id;
    private BigDecimal saldo;
    private Set<Long> fondosSuscritos;
}