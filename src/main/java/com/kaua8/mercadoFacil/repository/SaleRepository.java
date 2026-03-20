package com.kaua8.mercadoFacil.repository;

import com.kaua8.mercadoFacil.model.Sale;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

}

