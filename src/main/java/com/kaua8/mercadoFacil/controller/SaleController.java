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

    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId, @RequestParam Integer quantity, HttpSession session) {
        Map<Long, Integer> cart = getCart(session);
        cart.merge(productId, quantity, Integer::sum);
        session.setAttribute("cart", cart);
        return "redirect:/pdv";
    }

    @GetMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable Long productId, HttpSession session) {
        Map<Long, Integer> cart = getCart(session);
        cart.remove(productId);
        session.setAttribute("cart", cart);
        return "redirect:/pdv";
    }

    @PostMapping("/finish")
    public String finishSale(@RequestParam String paymentMethod, HttpSession session) {
        Map<Long, Integer> cart = getCart(session);
        if (!cart.isEmpty()) {
            saleService.finalizeSale(cart, paymentMethod);
            session.removeAttribute("cart");
        }
        return "redirect:/pdv?success";
    }

    @GetMapping("/clear")
    public String clearCart(HttpSession session) {
        session.removeAttribute("cart");
        return "redirect:/pdv";
    }

    @SuppressWarnings("unchecked")
    private Map<Long, Integer> getCart(HttpSession session) {
        Map<Long, Integer> cart = (Map<Long, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    private Map<Long, Object[]> buildCartDetails(Map<Long, Integer> cart) {
        Map<Long, Object[]> details = new HashMap<>();
        cart.forEach((productId, quantity) -> {
            var product = productService.findById(productId);
            details.put(productId, new Object[]{product, quantity});
        });
        return details;
    }
}
