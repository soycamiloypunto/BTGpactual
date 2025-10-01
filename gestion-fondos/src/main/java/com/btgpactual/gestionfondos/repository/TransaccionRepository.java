package com.btgpactual.gestionfondos.repository;

import com.btgpactual.gestionfondos.entity.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, String> {
    List<Transaccion> findByIdClienteOrderByFechaDesc(String idCliente);
}