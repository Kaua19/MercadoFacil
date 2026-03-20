package com.kaua8.mercadoFacil.service;

import com.kaua8.mercadoFacil.model.Product;
import com.kaua8.mercadoFacil.repository.ProductRepository;
import com.kaua8.mercadoFacil.repository.SaleItemRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final SaleItemRepository saleItemRepository;


    public ProductService(ProductRepository productRepository, SaleItemRepository saleItemRepository) {
        this.productRepository = productRepository;
        this.saleItemRepository = saleItemRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void delete(Long id) {
        try {
            if(saleItemRepository.existsByProductId(id)){
                throw new RuntimeException("Cannot delete this product because it has sales records!");
            }
            productRepository.deleteById(id);
            productRepository.flush();
        } catch (Exception e) {
            throw new RuntimeException("Cannot delete this product because it has sales records!");
        }
    }

    public List<Product> findByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Product> findLowStock() {
        return productRepository.findByStockQuantityLessThanEqual(5);
    }

    public Product findByBarCode(String barCode) {
        return productRepository.findByBarcode(barCode);
    }

}