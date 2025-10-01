package com.btgpactual.gestionfondos.repository;

import com.btgpactual.gestionfondos.entity.Fondo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FondoRepository extends JpaRepository<Fondo, Long> {
}