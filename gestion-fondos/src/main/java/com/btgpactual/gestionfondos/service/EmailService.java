package com.btgpactual.gestionfondos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;

    public void enviarNotificacionSuscripcion(String correoDestinatario, String nombreFondo, String categoria) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("exanet2jun22@outlook.com");
            message.setTo(correoDestinatario);
            message.setSubject("Suscripción Exitosa al Fondo " + nombreFondo);
            message.setText("¡Felicitaciones!\n\nTe has suscrito exitosamente al fondo '"
                    + nombreFondo + "' de la categoría " + categoria + ".\n\n"
                    + "Gracias por confiar en BTG Pactual.");
            emailSender.send(message);
        } catch (Exception e) {
            System.err.println("Error al enviar el correo de notificación: " + e.getMessage());
        }
    }
}