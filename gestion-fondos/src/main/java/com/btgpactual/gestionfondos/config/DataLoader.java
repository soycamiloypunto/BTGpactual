package com.btgpactual.gestionfondos.config;

import com.btgpactual.gestionfondos.entity.Cliente;
import com.btgpactual.gestionfondos.entity.Fondo;
import com.btgpactual.gestionfondos.repository.ClienteRepository;
import com.btgpactual.gestionfondos.repository.FondoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final FondoRepository fondoRepository;
    private final ClienteRepository clienteRepository;

    public DataLoader(FondoRepository fondoRepository, ClienteRepository clienteRepository) {
        this.fondoRepository = fondoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (fondoRepository.count() == 0) {
            Fondo f1 = new Fondo();
            f1.setId(1L);
            f1.setNombre("FPV_BTG_PACTUAL_RECAUDADORA");
            f1.setMontoMinimo(new BigDecimal("75000"));
            f1.setCategoria("FPV");

            Fondo f2 = new Fondo();
            f2.setId(2L);
            f2.setNombre("FPV_BTG_PACTUAL_ECOPETROL");
            f2.setMontoMinimo(new BigDecimal("125000"));
            f2.setCategoria("FPV");

            Fondo f3 = new Fondo();
            f3.setId(3L);
            f3.setNombre("DEUDAPRIVADA");
            f3.setMontoMinimo(new BigDecimal("50000"));
            f3.setCategoria("FIC");

            Fondo f4 = new Fondo();
            f4.setId(4L);
            f4.setNombre("FDO-ACCIONES");
            f4.setMontoMinimo(new BigDecimal("250000"));
            f4.setCategoria("FIC");

            Fondo f5 = new Fondo();
            f5.setId(5L);
            f5.setNombre("FPV BTG PACTUAL DINAMICA");
            f5.setMontoMinimo(new BigDecimal("100000"));
            f5.setCategoria("FPV");

            fondoRepository.saveAll(List.of(f1, f2, f3, f4, f5));
        }

        if (clienteRepository.count() == 0) {
            Cliente cliente = new Cliente(new BigDecimal("500000"));
            cliente.setId("UNICO-CLIENTE");
            clienteRepository.save(cliente);
        }
    }
}