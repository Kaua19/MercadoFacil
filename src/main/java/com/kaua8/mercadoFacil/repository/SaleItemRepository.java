package com.kaua8.mercadoFacil.repository;

import com.kaua8.mercadoFacil.model.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {

    boolean existsByProductId(Long productId);
}
