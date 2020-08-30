/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.popovichia.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.popovichia.springmvc.entities.Product;
import ru.popovichia.springmvc.repositories.ProductsRepository;

@Controller
public class ProductController {
    
    private ProductsRepository productsRepository = new ProductsRepository();
    
    @GetMapping("/")
    public String getAllProducts(Model model) {
        model.addAttribute("listProducts", productsRepository.getAllProducts());
        return "index";
    }
    @PostMapping("/")
    public String getProductById(Model model, @RequestParam(required = false) Long id) {
        model.addAttribute("listProducts", productsRepository.getProductById(id));
        return "index";
    }
    @PostMapping("/addProductToRepository")
    public String addProductToRepository(Model model,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) float cost) {
        productsRepository.addProductToRepository(new Product(title, cost));
        model.addAttribute("listProducts", productsRepository.getAllProducts());
        return "index";
    }
}
