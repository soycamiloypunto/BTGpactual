package com.btgpactual.gestionfondos.controller;

import com.btgpactual.gestionfondos.dto.*;
import com.btgpactual.gestionfondos.entity.Fondo;
import com.btgpactual.gestionfondos.service.GestionFondosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Permitir peticiones desde cualquier origen (para Angular)
public class FondosController {

    private final GestionFondosService gestionFondosService;
    private static final String CLIENTE_ID_HARCODED = "UNICO-CLIENTE";

    @GetMapping("/fondos")
    public ResponseEntity<ApiResponse<List<Fondo>>> listarFondos() {
        List<Fondo> fondos = gestionFondosService.getTodosLosFondos();
        return ResponseEntity.ok(ApiResponse.success(fondos));
    }

    @GetMapping("/cliente")
    public ResponseEntity<ApiResponse<ClienteDto>> getEstadoCliente() {
        ClienteDto clienteDto = gestionFondosService.getEstadoCliente(CLIENTE_ID_HARCODED);
        return ResponseEntity.ok(ApiResponse.success(clienteDto));
    }

    @GetMapping("/transacciones")
    public ResponseEntity<ApiResponse<List<TransaccionDto>>> getHistorialTransacciones() {
        List<TransaccionDto> historial = gestionFondosService.getHistorialTransacciones(CLIENTE_ID_HARCODED);
        return ResponseEntity.ok(ApiResponse.success(historial));
    }

    @PostMapping("/suscripciones")
    public ResponseEntity<ApiResponse<Object>> suscribir(@RequestBody SuscripcionRequest request) {
        request.setIdCliente(CLIENTE_ID_HARCODED);
        gestionFondosService.suscribir(request);
        return ResponseEntity.ok(ApiResponse.success(null, "Suscripción al fondo realizada con éxito."));
    }

    @PostMapping("/cancelaciones")
    public ResponseEntity<ApiResponse<Object>> cancelar(@RequestBody SuscripcionRequest request) {
        request.setIdCliente(CLIENTE_ID_HARCODED);
        gestionFondosService.cancelarSuscripcion(request);
        return ResponseEntity.ok(ApiResponse.success(null, "Cancelación de suscripción realizada con éxito."));
    }

    @PostMapping("/cancelaciones/todas")
    public ResponseEntity<ApiResponse<Object>> cancelarTodas() {
        gestionFondosService.cancelarTodasSuscripciones(CLIENTE_ID_HARCODED);
        return ResponseEntity.ok(ApiResponse.success(null, "Todas las suscripciones han sido canceladas."));
    }

    @PutMapping("/cliente/email")
    public ResponseEntity<ApiResponse<Object>> updateEmail(@RequestBody UpdateEmailRequest request) {
        gestionFondosService.updateClienteEmail(CLIENTE_ID_HARCODED, request.getEmail());
        return ResponseEntity.ok(ApiResponse.success(null, "Email actualizado correctamente."));
    }
}