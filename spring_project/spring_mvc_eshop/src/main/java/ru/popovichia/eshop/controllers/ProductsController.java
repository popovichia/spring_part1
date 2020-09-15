package ru.popovichia.eshop.controllers;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.popovichia.eshop.entities.Product;
import ru.popovichia.eshop.services.ServiceImpl;

/**
 *
 * @author Igor Popovich, email: iapopo17@mts.ru, phone: +7 913 902 36 36,
 * company: mts.ru
 */
@Controller
@RequestMapping(path = "/")
public class ProductsController {
    
    @Autowired
    private ServiceImpl serviceImpl;
    
    @PostMapping(path = "/createProduct")
    public String createProduct(
            @RequestParam(required = true) String productTitle,
            @RequestParam(required = true) BigDecimal productPrice
    ) {
        serviceImpl.createProduct(productTitle, productPrice);
        return "redirect:./";
    }
    
    @PostMapping(path = "/setEditingProduct")
    public String setEditingProduct(
            @RequestParam(required = true) Long id
    ) {
        serviceImpl.setEditingProduct(id);
        return "redirect:./";
    }

    @GetMapping(path = "/getEditingProduct")
    public Product getEditingProduct() {
        return serviceImpl.getEditingProduct();
    }
    
    @PutMapping(path = "/updateProductById")
    public String updateProductById(
            @RequestParam(name = "id", required = true) Long id,
            @RequestParam(name = "productNewTitle", required = true) String productNewTitle,
            @RequestParam(name = "productNewPrice", required = true) BigDecimal productNewPrice
    ) {
        serviceImpl.updateProductById(id, productNewTitle, productNewPrice);
        serviceImpl.setEditingProduct(null);
        return "redirect:./";
    }

    @DeleteMapping(path = "/deleteProductById")
    public String deleteProductById(
            @RequestParam(name = "id", required = true) Long id
    ) {
        serviceImpl.deleteProductById(id);
        return "redirect:./";
    }

}
