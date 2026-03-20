package com.kaua8.mercadoFacil.controller;

import com.kaua8.mercadoFacil.model.Sale;
import com.kaua8.mercadoFacil.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    private SaleService saleService;

    @GetMapping
    public String sales(Model model) {
        List<Sale> todaySales = saleService.findTodaySale();

        BigDecimal todayDay = todaySales.stream()
                .map(Sale::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("sales", todaySales);
        model.addAttribute("todayDay", todaySales);
        return "sale/list";
    }
}
