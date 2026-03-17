package com.kaua8.mercadoFacil.controller;
import com.kaua8.mercadoFacil.service.SaleService;
import com.kaua8.mercadoFacil.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/pdv")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String pdv(Model model, HttpSession session){
        Map<Long, Integer> cart = getCart(session);
        model.addAttribute("products", productService.findAll());
        model.addAttribute("cartItems", buildCartDetails(cart));
        model.addAttribute("cart", cart);
        return "sale/pdv";
    }


}
