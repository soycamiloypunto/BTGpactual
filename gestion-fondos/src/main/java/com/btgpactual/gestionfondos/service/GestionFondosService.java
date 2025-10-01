package com.btgpactual.gestionfondos.service;

import com.btgpactual.gestionfondos.dto.ClienteDto;
import com.btgpactual.gestionfondos.dto.SuscripcionRequest;
import com.btgpactual.gestionfondos.dto.TransaccionDto;
import com.btgpactual.gestionfondos.entity.Cliente;
import com.btgpactual.gestionfondos.entity.Fondo;
import com.btgpactual.gestionfondos.entity.Transaccion;
import com.btgpactual.gestionfondos.enums.TipoTransaccion;
import com.btgpactual.gestionfondos.exception.FondoNoEncontradoException;
import com.btgpactual.gestionfondos.exception.SaldoInsuficienteException;
import com.btgpactual.gestionfondos.repository.ClienteRepository;
import com.btgpactual.gestionfondos.repository.FondoRepository;
import com.btgpactual.gestionfondos.repository.TransaccionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GestionFondosService {

    private final ClienteRepository clienteRepository;
    private final FondoRepository fondoRepository;
    private final TransaccionRepository transaccionRepository;
    private final EmailService emailService;


    @Transactional
    public void suscribir(SuscripcionRequest request) {
        Cliente cliente = getCliente(request.getIdCliente());
        Fondo fondo = getFondo(request.getIdFondo());

        if (cliente.getSaldo().compareTo(fondo.getMontoMinimo()) < 0) {
            throw new SaldoInsuficienteException("No tiene saldo disponible para vincularse al fondo " + fondo.getNombre());
        }

        cliente.setSaldo(cliente.getSaldo().subtract(fondo.getMontoMinimo()));
        cliente.getFondosSuscritos().add(fondo.getId());
        clienteRepository.save(cliente);

        Transaccion transaccion = new Transaccion(TipoTransaccion.APERTURA, fondo.getId(), cliente.getId(), fondo.getMontoMinimo());
        transaccionRepository.save(transaccion);

        try {
            if ("email".equalsIgnoreCase(request.getTipoNotificacion()) && request.getEmail() != null && !request.getEmail().isEmpty()) {
                emailService.enviarNotificacionSuscripcion(request.getEmail(), fondo.getNombre(), fondo.getCategoria());
            }
        } catch (Exception e) {
            System.err.println("ALERTA: La suscripción al fondo '" + fondo.getNombre() + "' fue exitosa, pero falló el envío del correo de notificación.");
            System.err.println("Causa del error de correo: " + e.getMessage());
        }
    }

    @Transactional
    public void updateClienteEmail(String idCliente, String nuevoEmail) {
        if (nuevoEmail == null || nuevoEmail.trim().isEmpty()) {
            throw new IllegalArgumentException("El email no puede estar vacío.");
        }
        Cliente cliente = getCliente(idCliente);
        cliente.setEmail(nuevoEmail);
        clienteRepository.save(cliente);
    }


    @Transactional
    public void cancelarTodasSuscripciones(String idCliente) {
        Cliente cliente = getCliente(idCliente);
        if (cliente.getFondosSuscritos().isEmpty()) {
            return;
        }

        BigDecimal montoTotalRetornado = BigDecimal.ZERO;

        for (Long fondoId : new java.util.HashSet<>(cliente.getFondosSuscritos())) {
            Fondo fondo = getFondo(fondoId);
            montoTotalRetornado = montoTotalRetornado.add(fondo.getMontoMinimo());

            Transaccion transaccion = new Transaccion(TipoTransaccion.CANCELACION, fondo.getId(), cliente.getId(), fondo.getMontoMinimo());
            transaccionRepository.save(transaccion);
        }

        cliente.setSaldo(cliente.getSaldo().add(montoTotalRetornado));
        cliente.getFondosSuscritos().clear(); // Limpiamos todas las suscripciones
        clienteRepository.save(cliente);
    }

    @Transactional
    public void cancelarSuscripcion(SuscripcionRequest request) {
        Cliente cliente = getCliente(request.getIdCliente());
        Fondo fondo = getFondo(request.getIdFondo());

        if (!cliente.getFondosSuscritos().contains(fondo.getId())) {
            throw new FondoNoEncontradoException("El cliente no está suscrito al fondo " + fondo.getNombre());
        }

        cliente.setSaldo(cliente.getSaldo().add(fondo.getMontoMinimo()));
        cliente.getFondosSuscritos().remove(fondo.getId());
        clienteRepository.save(cliente);

        Transaccion transaccion = new Transaccion(TipoTransaccion.CANCELACION, fondo.getId(), cliente.getId(), fondo.getMontoMinimo());
        transaccionRepository.save(transaccion);
    }

    public ClienteDto getEstadoCliente(String idCliente) {
        Cliente cliente = getCliente(idCliente);
        return ClienteDto.builder()
                .id(cliente.getId())
                .saldo(cliente.getSaldo())
                .fondosSuscritos(cliente.getFondosSuscritos())
                .build();
    }

    public List<TransaccionDto> getHistorialTransacciones(String idCliente) {
        getCliente(idCliente);
        List<Transaccion> transacciones = transaccionRepository.findByIdClienteOrderByFechaDesc(idCliente);

        return transacciones.stream().map(t -> TransaccionDto.builder()
                .id(t.getId())
                .tipo(t.getTipo())
                .idFondo(t.getIdFondo())
                .nombreFondo(getFondo(t.getIdFondo()).getNombre())
                .monto(t.getMonto())
                .fecha(t.getFecha())
                .build()
        ).collect(Collectors.toList());
    }

    public List<Fondo> getTodosLosFondos() {
        return fondoRepository.findAll();
    }

    private Cliente getCliente(String idCliente) {
        return clienteRepository.findById(idCliente)
                .orElseThrow(() -> new FondoNoEncontradoException("Cliente no encontrado con id: " + idCliente));
    }

    private Fondo getFondo(Long idFondo) {
        return fondoRepository.findById(idFondo)
                .orElseThrow(() -> new FondoNoEncontradoException("Fondo no encontrado con id: " + idFondo));
    }
}