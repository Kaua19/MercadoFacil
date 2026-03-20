package com.kaua8.mercadoFacil.service;

import com.kaua8.mercadoFacil.model.Product;
import com.kaua8.mercadoFacil.model.Sale;
import com.kaua8.mercadoFacil.model.SaleItem;
import com.kaua8.mercadoFacil.repository.SaleRepository;
import com.kaua8.mercadoFacil.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

@Service
public class SaleService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleRepository saleRepository;

    public Sale finalizeSale(Map<Long, Integer> cartItem, String paymenMethod) {

        Sale sale = new Sale();
        sale.setPaymentMethod(paymenMethod);

        List<SaleItem> items = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (Map.Entry<Long, Integer> entry : cartItem.entrySet()) {

            Long productId = entry.getKey();
            Integer quantity = entry.getValue();

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product Not Found"));

            BigDecimal subTotal = product.getPrice()
                    .multiply(BigDecimal.valueOf(quantity));

            SaleItem item = new SaleItem();
            item.setSale(sale);
            item.setProduct(product);
            item.setQuantity(quantity);
            item.setUnitPrice(product.getPrice());
            item.setSubtotal(subTotal);

            items.add(item);
            total.add(subTotal);

            product.setStockQuantity(product.getStockQuantity() - quantity);
            productRepository.save(product);
        }

        sale.setItems(items);
        sale.setTotal(total);

        return saleRepository.save(sale);
    }

    public List<Sale> findTodaySale() {

        LocalDateTime start = LocalDateTime.now().withHour(0).withMinute(0);
        LocalDateTime end = LocalDateTime.now().withHour(23).withMinute(59);

        return saleRepository.findByCreatedAtBetween(start, end);
    }

    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

}
