package ru.popovichia.eshop.controllers;

import java.math.BigDecimal;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.popovichia.eshop.entities.Product;
import ru.popovichia.eshop.services.DataService;

/**
 *
 * @author Igor Popovich, email: iapopo17@mts.ru, phone: +7 913 902 36 36,
 * company: mts.ru
 */
@Controller
@RequestMapping(path = "/")
public class ProductsController {
    
    @Autowired
    private DataService dataService;
    
    @GetMapping(path = "getAllProducts")
    public String getAllProducts(
            Model model,
            @RequestParam(name = "id", required = false) Long id
    ) {
        model.addAttribute("product", dataService.getProductById(id));
        model.addAttribute("listProducts", dataService.getAllProducts());
        return "products";
    }

    @PostMapping(path = "/saveProduct")
    public String createProduct(
            @Valid Product product,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "products";
        }
        dataService.saveProduct(product);
        return "redirect:./getAllProducts";
    }
    
    @PostMapping(path = "/setEditingProduct")
    public String setEditingProduct(
            @RequestParam(required = true) Long id
    ) {
        dataService.setEditingProduct(id);
        return "redirect:./";
    }

    @GetMapping(path = "/getEditingProduct")
    public Product getEditingProduct() {
        return dataService.getEditingProduct();
    }
    
    @PutMapping(path = "/updateProductById")
    public String updateProductById(
            @RequestParam(name = "id", required = true) Long id,
            @RequestParam(name = "productNewTitle", required = true) String productNewTitle,
            @RequestParam(name = "productNewPrice", required = true) BigDecimal productNewPrice
    ) {
        dataService.updateProductById(id, productNewTitle, productNewPrice);
        dataService.setEditingProduct(null);
        return "redirect:./";
    }

    @DeleteMapping(path = "/deleteProductById")
    public String deleteProductById(
            @RequestParam(name = "id", required = true) Long id
    ) {
        dataService.deleteProductById(id);
        return "redirect:./";
    }

}
