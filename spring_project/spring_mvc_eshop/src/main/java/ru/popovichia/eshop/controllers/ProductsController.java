package ru.popovichia.eshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
            @RequestParam(required = true) float productPrice
    ) {
        serviceImpl.createProduct(productTitle, productPrice);
        return "redirect:./";
    }
    
}
