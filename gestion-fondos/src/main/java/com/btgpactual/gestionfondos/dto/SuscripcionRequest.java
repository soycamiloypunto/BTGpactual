package com.btgpactual.gestionfondos.dto;

import lombok.Data;

@Data
public class SuscripcionRequest {
    private String idCliente;
    private Long idFondo;
    private String tipoNotificacion; // "email" o "sms"
    private String email;
}