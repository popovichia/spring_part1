package ru.popovichia.eshop.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    private final static String PRODUCTS_VIEWS_DIR = "products/";
    
    @Autowired
    private DataService dataService;
    
    @GetMapping(path = "getAllProducts")
    public String getAllProducts(
            Model model,
            @RequestParam(name = "id", required = false) Long id
    ) {
        Product product = null;
        if (id != null) {
            product = dataService.getProductById(id);
        }
        if (product == null) {
            product = new Product();
        }
        model.addAttribute("product", product);
        model.addAttribute("listProducts", dataService.getAllProducts());
        return PRODUCTS_VIEWS_DIR + "products";
    }

    @PostMapping(path = "/saveProductById")
    public String saveProductById(
            @Valid Product product,
            @RequestParam(name = "id", required = false) Long id,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return PRODUCTS_VIEWS_DIR + "products";
        }
        dataService.saveProductById(id, product);
        return "redirect:./getAllProducts";
    }
    
    @DeleteMapping(path = "/deleteProductById")
    public String deleteProductById(
            @RequestParam(name = "id", required = true) Long id
    ) {
        dataService.deleteProductById(id);
        return "redirect:./getAllProducts";
    }

}
